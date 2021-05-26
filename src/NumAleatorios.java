/*
Guillermo Moreno Rivera
21-05-2021
Se implementa el método de cuadrados aleatorios en una sola ejecución
debido a que es más práctico para el programa generar un solo número cada
que sea necesario
*/

import java.util.Scanner;

public class NumAleatorios {
    int semillas[] = new int[6];
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese 6 semillas para la generación aleatoria: ");
        for(int x = 0; x <= 6; x++){
            semillas[x] = sc.nextInt();
        }
        sc.close();
    }
}