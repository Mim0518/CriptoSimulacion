import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double tiempoInicial = System.currentTimeMillis();
        double tiempoTecnico = Math.floor(((Math.random() * 120) + 55)*100)/100;
        new Simulacion();
        System.out.println("\n \nEl tiempo de ejecución el técnico fue de "+ tiempoTecnico +" minutos que son " + (tiempoTecnico*60) +" segundos");
        System.out.println("\n \nEl tiempo de ejecución de el sistema fue de " + ((System.currentTimeMillis() - tiempoInicial) / 1000) + " segundos");
    }
}