/*
Guillermo Moreno Rivera
21-05-2021
Se implementa el método de cuadrados aleatorios en una sola ejecución
debido a que es más práctico para el programa generar un solo número cada
que sea necesario
*/

import java.util.Scanner;

public class NumAleatorios {
    int semillas[] = {4287, 9999, 4521, 1625, 6655, 2178};
    int cont = 0;
    public  NumAleatorios(){
    }
    public double numero(){
        //Las semillas deben ser siempre de 4 dígitos
        double semillaCuad = Math.pow(semillas[cont],2);
        String preNum = Double.toString(semillaCuad);
        String num = preNum.substring(1,preNum.length());
        double numfinal = Double.parseDouble(num);
        cont++;
        return numfinal;
    }
    public void getSemillas(){

    }
}