
public class NumAleatorios {
    int[] semillas = {4287, 9999, 4521, 1625, 6655, 2178};
    int cont = 0;

    public double numero(){
        //Las semillas deben ser siempre de 4 dígitos
        double semillaCuad = semillas[cont] * semillas[cont];
        String preNum = Double.toString(semillaCuad);
        String num = preNum.substring(1,preNum.length());
        //Número aleatorio genearado con semillas
        double numfinal = Double.parseDouble(num);
        cont++;
        return numfinal;
    }
}