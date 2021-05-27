import java.util.Scanner;

public class Menu {
    public double pres;
    public Menu(){}
    public void correr(){
        Scanner sc = new Scanner(System.in);
        System.out.println("CriptoMX");
        System.out.println("Seleccione el tipo de minero: ");
        System.out.println("1.- GPU");
        System.out.println("2.- ASIC");
        int[] subseleccion = new int[3];
        int opc = sc.nextInt();
        System.out.println("Ingrese el presupuesto del cliente: ");
        pres = sc.nextDouble();
        NumAleatorios n = new NumAleatorios();
        Simulacion s = new Simulacion();
        switch (opc){
            case 1:
                subseleccion[0] = 1;
                System.out.println("Seleccione el algoritmo: ");
                System.out.println("1.- Zhash");
                System.out.println("2.- Ethash");
                System.out.println("3.- CryptoNight GPU");
                subseleccion[1] = sc.nextInt();
                System.out.println("Seleccione una tarjeta gráfica: ");
                System.out.println("1.- GTX 1050ti");
                System.out.println("2.- RX 5700");
                System.out.println("3.- RTX 3080");
                subseleccion[2] = sc.nextInt();
                s.establecer(subseleccion);
                s.ejecutarGPU();
                break;
            case 2:
                subseleccion[0] = 2;
                System.out.println("Seleccione el algoritmo: ");
                System.out.println("1.- X11");
                System.out.println("2.- Ethash");
                System.out.println("3.- SHA-256");
                subseleccion[1] = sc.nextInt();
                System.out.println("Seleccione un minero: ");
                System.out.println("1.- ANTMINER D3");
                System.out.println("2.- INNOSILICON A11");
                System.out.println("3.- ANTMINER S9");
                subseleccion[2] = sc.nextInt();
                s.establecer(subseleccion);
                s.ejecutar();
                break;
            default:
                System.out.println("Ingrese una opción válida por favor");
                break;
        }
        sc.close();
    }
}
