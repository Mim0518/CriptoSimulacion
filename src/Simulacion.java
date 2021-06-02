import org.apache.xmlbeans.impl.schema.StscChecker;

import java.io.IOException;
import java.math.BigDecimal;
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
   public static final double COSTO_KILOWATT = .085, PRECIO_DOLAR = 20;
   public static Scanner sc = new Scanner(System.in);
   public static double presupuesto;
   public static ArrayList<Algoritmo> algort = new FileAlgoritmo().obtenerDeArchivo();
   public static ArrayList<Grafica> graf = new FileGrafica().obtenerDeArchivo();
   public static ArrayList<RendimientoGraficas> rendGraf = new FileRendimientoGraficas().obtenerDeArchivo();

   public Simulacion() throws IOException {
      leerPre();
      llenar();
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
      for (Grafica g: graf) {
         System.out.println(g.getPrecio());
         for (RendimientoGraficas ren: rendGraf) {
            if(ren.getNom().equals(g.getNombre())) ren.setProporcionPrecio(ren.getGananciaEstimadaPesos()/g.getPrecio());
         }
      }

   }
   static double change(double value, int decimalpoint)
   {

      // Using the pow() method
      value = value * Math.pow(10, decimalpoint);
      value = Math.floor(value);
      value = value / Math.pow(10, decimalpoint);

      System.out.println(value);

      return value;
   }

   public static void getOptima(){

      ArrayList<Grafica> comprables = new ArrayList<>();
      for (Grafica grafica : graf) {
         if (grafica.getPrecio() < presupuesto) {
            comprables.add(grafica);
         }
      }
      System.out.println(rendGraf.size());
      ArrayList<RendimientoGraficas> graficasComprables = new ArrayList<>();

      for (Grafica grafica : comprables) {
         grafica.optimoPre(presupuesto);
         for (RendimientoGraficas ren: rendGraf) {
            if(grafica.getNombre().equals(ren.getNom())) graficasComprables.add(ren);
         }
      }

      System.out.println(graficasComprables.size());

      graficasComprables.sort(Comparator.comparingDouble(RendimientoGraficas::getProporcionPrecio).reversed());
      System.out.println(graficasComprables.size());
      System.out.println("\nLAS GRAFICAS COMPRABLES CON ESE PRESUPUESTO SON");
      for (Grafica grafica : comprables) {
         System.out.println("La gráfica " + grafica.getNombre() + " es comprable por una cantidad de " + grafica.getComprablesPresupuesto() + " gráficas" + " sobrando un presupuesto de $" + grafica.sobrantePresupuesto);
         for (RendimientoGraficas g: graficasComprables) {
            if(grafica.getNombre().equals(g.getNom())){
               System.out.println(" Algoritmo usado: "+g.getAlgoritmo());
               System.out.println("     Proporción ganancia/precio de grafica: "+g.getProporcionPrecio());
               System.out.println("     Ganancia de gráfica en pesos: "+g.getGananciaEstimadaPesos());
            }
         }
         System.out.println("----------------------------------------------------------");
      }
      System.out.println("La gráfica más optima fue " + graficasComprables.get(0).getNom() + " comprando " + graf.get(getIndexGrafica(graficasComprables.get(0))).getComprablesPresupuesto() + " gráficas con el algoritmo de "+ graficasComprables.get(0).getAlgoritmo() + " sobrando un presupuesto de $" + graf.get(getIndexGrafica(graficasComprables.get(0))).getSobrantePresupuesto());

   }
   public static int getIndexGrafica(RendimientoGraficas r){
      for (int i = 0; i < graf.size(); i++) {
         if(graf.get(i).getNombre().equals(r.getNom())){
            return i;
         }
      }
      return 0;
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