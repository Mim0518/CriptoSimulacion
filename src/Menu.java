import java.util.Scanner;

public class Menu {
    public double pres;
    public Menu(){}
    public static Scanner sc = new Scanner(System.in);
    public void correr(){
        //Declaración de clase Simulacion para su uso posterior
        Simulacion s = new Simulacion();
        //Ingreso de datos para la ejecución de la simulación
        System.out.println("CriptoMX");
        System.out.println("Seleccione el tipo de minero: ");
        System.out.println("1.- GPU");
        System.out.println("2.- ASIC");
        imp(2);
        //Selección de minero
        int[] subseleccion = new int[3];
        int opc = sc.nextInt();
        imp(1);
        //Ingreso de presupuesto
        s.getPres();
        switch (opc) {
            case 1 -> {

                //Seleccion de algoritmo para GPU
                subseleccion[0] = 1;
                sc.next();
                System.out.println("Seleccione el algoritmo: ");
                System.out.println("1.- Zhash");
                System.out.println("2.- Ethash");
                System.out.println("3.- CryptoNight GPU");
                imp(2);
                subseleccion[1] = sc.nextInt();
                imp(1);
                System.out.println("Seleccione una tarjeta gráfica: ");
                System.out.println("1.- GTX 1050ti");
                System.out.println("2.- RX 5700");
                System.out.println("3.- RTX 3080");
                imp(2);
                subseleccion[2] = sc.nextInt();
                s.establecer(subseleccion);
                //Ejecución de la simulación de GPU
                s.ejecutarGPU();
            }
            case 2 -> {
                //Seleccion de algoritmo para ASIC
                subseleccion[0] = 2;
                System.out.println("Seleccione el algoritmo: ");
                System.out.println("1.- X11");
                System.out.println("2.- Ethash");
                System.out.println("3.- SHA-256");
                imp(2);
                subseleccion[1] = sc.nextInt();
                imp(1);
                System.out.println("Seleccione un minero: ");
                System.out.println("1.- ANTMINER D3");
                System.out.println("2.- INNOSILICON A11");
                System.out.println("3.- ANTMINER S9");
                imp(1);
                subseleccion[2] = sc.nextInt();
                s.establecer(subseleccion);
                //Ejecución de la simulación de ASIC
                s.ejecutar();
            }
            default -> System.out.println("Ingrese una opción válida por favor");
        }
    }
    //método para impresión de sentencias mas utilizadas de manera rápida
    public void imp(int a){
        switch (a){
            case 1 -> System.out.println("-----------------------------------------------------------------");
            case 2 -> System.out.print("Ingrese su respuesta: ");
        }
    }
}