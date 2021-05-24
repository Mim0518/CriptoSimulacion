/*
Guillermo Moreno Rivera
21-05-2021
Se implementa el método de cuadrados aleatorios en una sola ejecución
debido a que es más práctico para el programa generar un solo número cada
que sea necesario
*/

public class NumAleatorios {
    public  NumAleatorios(){
    }
    public double numero(double semilla){
        //Las semillas deben ser siempre de 4 dígitos
        double semillaCuad = Math.pow(semilla,2);
        String preNum = Double.toString(semillaCuad);
        String num = preNum.substring(1,preNum.length());
        double numfinal = Double.parseDouble(num);
        return numfinal;
    }
}