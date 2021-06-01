import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Simulacion extends IOException {
   /* Lo ideal seria que para este programa, no se utilicen números aleatorios, debido a que si bien es cierto que los rendimientos de los
    * equipos de mineria pueden diferir bastante entre si; esta diferencia no es aleatoria, depnde enteramente de las condiciones del ambiente
    * en el que se opere el equipo, sin emargo, al no tener conocimiento ni de las condiciones del entorno y mucho menos de las diferencias en la
    * construcción de los controladores del minero, un número aleatorio puede dannos una ídea cercana al rendimiento que PODRIA tener el equipo
    */
   public static final double COSTO_KILOWATT = .085, PRECIO_DOLAR = 20.05;
   public static Scanner sc = new Scanner(System.in);
   public static double presupuesto;
   public static ArrayList<Algoritmo> algort = new FileAlgoritmo().obtenerDeArchivo();
   public static ArrayList<Grafica> graf = new FileGrafica().obtenerDeArchivo();
   public static ArrayList<RendimientoGraficas> rendGraf = new FileRendimientoGraficas().obtenerDeArchivo();

   public Simulacion() throws IOException {
      leerPre();
      llenar();
      calculaMedia();
      getOptima();
   }

   public static void leerPre(){
      do {
         try {
            System.out.print("Ingrese su presupuesto: ");
            presupuesto = sc.nextDouble();
         } catch (IllegalArgumentException e){
            System.out.println("Ingrese valores numericos");
         }
      }while (presupuesto < 5000);
   }
   public static void llenar(){
      for (Algoritmo algoritmo : algort)
         algoritmo.setGananciaBloqueDia(algoritmo.getBloqueDiarios() * algoritmo.getRecompensaBloque()); //ganaciaBloqueDia = bloquesDiarios * recompensaBloque

      for (int i = 0; i < algort.size(); i++) {
         for (int k = 0; k < rendGraf.size(); k++) {
            if(algort.get(i).getAlgoritmo().equals(rendGraf.get(k).getAlgoritmo())){
               rendGraf.get(k).setRateCorrespondiente(rendGraf.get(k).getHashes()/algort.get(getIndexAlgoritmo(rendGraf.get(k).getAlgoritmo())).getHashrateRed()); // ateCorrespondiente = hashrateEquipo/hashrateRed;
               rendGraf.get(k).setGananciaEstimada(rendGraf.get(k).getRateCorrespondiente() * algort.get(getIndexAlgoritmo(rendGraf.get(k).getAlgoritmo())).getGananciaBloqueDia()); //gananciaEstimada = rateCorrespondiente * ganaciaBloqueDia
               rendGraf.get(k).setGananciaEstimadaDLS(rendGraf.get(k).getGananciaEstimada()*algort.get(getIndexAlgoritmo(rendGraf.get(k).getAlgoritmo())).getPrecioMoneda()); //gananciaEstimadaDLS = gananciaEstimada * precioMoneda
               rendGraf.get(k).setGananciaEstimadaNeta(rendGraf.get(k).getGananciaEstimadaDLS() - (((rendGraf.get(k).watts) * COSTO_KILOWATT)/1000)*24); //gananciaEstimadaNeta = gananciaEstimadaDLS - (((wattsConsumo*COSTO_KILOWATT)/1000)*24)
               rendGraf.get(k).setGananciaEstimadaPesos(rendGraf.get(k).getGananciaEstimadaDLS() * PRECIO_DOLAR); //gananciaEstimadaPesos = gananciaEstimadaDLS * PRECIO_DOLAR
            }
         }
      }

   }
   public static void calculaMedia(){
      double media, sum;
      int contNoNull = 0;
      media = sum = 0;
      for (int i = 0; i < algort.size(); i++) {
         for (Grafica grafica : graf) {
            for (RendimientoGraficas rendimientoGraficas : rendGraf) {
               if (grafica.getNombre().equals(rendimientoGraficas.getNom())) {
                  if (rendimientoGraficas.getRateCorrespondiente() != 0) {
                     sum = rendimientoGraficas.getGananciaEstimadaPesos();
                     contNoNull++;
                  }
               }
            }
            grafica.setGananciaMedia(sum / contNoNull);
            media = sum = contNoNull = 0;
         }
      }
   }
   public static void getOptima(){

      ArrayList<Grafica> comprables = new ArrayList<>();
      int id = 0, temp = 0, mayorCont = 0;
      double mayor = 0;
      for (Grafica grafica : graf) {
         if (grafica.getPrecio() < presupuesto) {
            comprables.add(grafica);
         }
      }
      for (Grafica grafica : comprables) {
         grafica.optimoPre(presupuesto);
      }
      comprables.sort(Comparator.comparingDouble(Grafica::getGananciaMedia));
      System.out.println("\nLAS GRAFICAS COMPRABLES CON ESE PRESUPUESTO SON");
      for (Grafica comprable : comprables) {
         System.out.println("La gráfica " + comprable.getNombre() + " es comprable por una cantidad de " + comprable.getComprablesPresupuesto() + " gráficas" + " sobrando un presupuesto de $" + comprable.sobrantePresupuesto);
         System.out.println("Detalles de grafica");
         for (RendimientoGraficas rendimiento: rendGraf) {
            if(rendimiento.getHashes() != 0){
               if(rendimiento.getNom().equals(comprable.getNombre())){
                  System.out.println("  Algoritmo usado: " + rendimiento.getAlgoritmo() );
                  System.out.println("     Ganancia en pesos: $" + rendimiento.getGananciaEstimadaPesos() );
               }

            }
         }
         System.out.println("----------------------------------------------------------");
      }
      System.out.println("La gráfica " + comprables.get(0).getNombre() + " comprando " + comprables.get(0).getComprablesPresupuesto() + " gráficas " + " sobrando un presupuesto de $" + comprables.get(0).sobrantePresupuesto);

   }
   public static int getMayorGanancia(ArrayList<Grafica> op){
      double mayor, mayorCont;
      mayor = mayorCont = 0;
      for (int i = 0; i < op.size(); i++) {
         if(op.get(i).getGanananciaFinal() > mayor){
            mayor = op.get(i).getGanananciaFinal();
            mayorCont = i;
         }
      }
      return (int) mayorCont;
   }


   public static void printRendimientoGrafica(RendimientoGraficas rendimientoGraficas){
         System.out.print("\n " + rendimientoGraficas.getNom());
         System.out.print("  " +rendimientoGraficas.getAlgoritmo());
         System.out.print("  " +rendimientoGraficas.getHashes());
         System.out.print("  " +rendimientoGraficas.getGananciaEstimada());
         System.out.print("  " +rendimientoGraficas.getGananciaEstimadaDLS());
         System.out.print("  " +rendimientoGraficas.getGananciaEstimadaPesos());
      
   }
   public static int getIndexAlgoritmo(String a){
      int index = 0;
      for (int i = 0; i < algort.size(); i++) {
            for (int k = 0; k < rendGraf.size(); k++) {
               if (a.equals(algort.get(i).getAlgoritmo())) {
                  index = i;
                  break;
               }
            }
      }
      return index;
   }

}