public class Simulacion{
    Menu m = new Menu();
    public double tiempoBloque, bloquesDiarios, recompensaBloque, hashrateRed, hashrateEquipo, precioMoneda, wattsConsumo, gananciaEstimada, rateCorrespondiente,
            ganaciaBloqueDia, gananciaEstimadaDLS, gananciaEstimadaNeta, gananciaEstimadaPesos, precioMinero, presupuesto;
    public boolean imposible = false;
    public static String algoritmo, minero, comparativa[] = new String [3];
    public static final double COSTO_KILOWATT = .085, PRECIO_DOLAR = 20.87;
    NumAleatorios n = new NumAleatorios();
    public Simulacion(){
        presupuesto = m.pres;
    }
    public void establecer(int[] subseleccion){
        //Opción para minero GPU
        if(subseleccion[0] == 1){
            if(subseleccion[1] == 1){
                algoritmo = "Zhash";
                tiempoBloque = 9.46;
                bloquesDiarios = 152.112676;
                recompensaBloque = 6.25;
                hashrateRed = 2510000;
                precioMoneda = 91.92;
                minero = "GTX 1050ti";
                wattsConsumo = 80;
                precioMinero = 25000;
                hashrateEquipo = n.numero()/10010;
                comparativa[0] = cadena();
                minero = "RX 5700";
                hashrateEquipo = n.numero()/1000;
                wattsConsumo = 140;
                precioMinero = 42000;
                comparativa[1] = cadena();
                minero = "RTX 3080";
                hashrateEquipo = n.numero()/1000;
                wattsConsumo = 250;
                precioMinero = 85000;
                comparativa[2] = cadena();
            }else if(subseleccion[1] == 2){
                algoritmo = "Ethash";
                tiempoBloque = .22416667;
                bloquesDiarios = 6423.79182;
                recompensaBloque = 3.79;
                hashrateRed = 511980000;
                precioMoneda = 2312.35;
                minero = "GTX 1050ti";
                wattsConsumo = 0;
                precioMinero = 24800;
                comparativa[0] = "Esta configuracion no es posible: Ethash + GTX 1050ti";
                minero = "RX 5700";
                wattsConsumo = 130;
                hashrateEquipo = n.numero()/1000;
                precioMinero = 40875;
                comparativa[1] = cadena();
                minero = "RTX 3080";
                wattsConsumo = 230;
                hashrateEquipo = n.numero()/1000;
                precioMinero = 85300;
                comparativa[2] = cadena();
            }
            else if(subseleccion[1] == 3){
                algoritmo = "CryptoNightGPU";
                tiempoBloque = 1.95;
                bloquesDiarios = 738.46;
                recompensaBloque = 12;
                hashrateRed = 1500000;
                precioMoneda = .491;
                minero = "GTX 1050ti";
                wattsConsumo = 60;
                hashrateEquipo = n.numero()/1000;
                precioMinero = 26000;
                comparativa[0] = cadena();
                minero = "RX 5700";
                hashrateEquipo = n.numero()/1000;
                precioMinero = 43570;
                wattsConsumo = 140;
                comparativa[1] = cadena();
                hashrateEquipo = n.numero()/1000;
                minero = "RTX 3080";
                wattsConsumo = 250;
                precioMinero = 83250;
                comparativa[2] = cadena();
            }
        }else if(subseleccion[0] == 2){
            //Opción para minero ASIC
            if(subseleccion[1] == 1){
                algoritmo = "X11";
                tiempoBloque = 2.333333;
                bloquesDiarios = 617.14286;
                recompensaBloque = 1.5;
                hashrateRed = 542000000;
                precioMoneda = 400;
            }else if(subseleccion[1] == 2){
                algoritmo = "Ethash";
                tiempoBloque = .2241667;
                bloquesDiarios = 6423.7918;
                recompensaBloque = 3.41;
                hashrateRed = 511980000;
                precioMoneda = 2312.35;
            }else if(subseleccion[1] == 3){
                algoritmo = "SHA-256";
                tiempoBloque = 9.75;
                bloquesDiarios = 147.69231;
                recompensaBloque = 7.25;
                hashrateRed = 1731347700;
                precioMoneda = 55173;
            }
            if(subseleccion[2] == 1){
                minero = "ANTMINER D3";
                wattsConsumo = 800;
                precioMinero = 18050;
            }else if(subseleccion[2] == 2){
                minero = "INNOSILICON A11";
                wattsConsumo = 2600;
                precioMinero = 68974;
            }else if(subseleccion[2] == 3){
                minero = "ANTMINER S9";
                wattsConsumo = 1372;
                precioMinero = 100000;
            }
        }
        if(subseleccion[0] == 2 && subseleccion[1] == 1 && subseleccion[2] != 1){
            imposible = true;
        }
        if(subseleccion[0] == 2 && subseleccion[1] == 2 && subseleccion[2] != 2){
            imposible = true;
        }
        if(subseleccion[0] == 2 && subseleccion[1] == 3 && subseleccion[2] != 3){
            imposible = true;
        }
    }
    public void ejecutar(){
        if (imposible){
            System.out.println("El minero no puede funcionar con la configuración seleccionada, por favor elija otra");
        }else{
            hashrateEquipo = n.numero()/1000;
            rateCorrespondiente = hashrateEquipo/hashrateRed;
            ganaciaBloqueDia = bloquesDiarios * recompensaBloque;
            gananciaEstimada = rateCorrespondiente * ganaciaBloqueDia;
            gananciaEstimadaDLS = gananciaEstimada * precioMoneda;
            gananciaEstimadaNeta = gananciaEstimadaDLS - (((wattsConsumo*COSTO_KILOWATT)/1000)*24);
            gananciaEstimadaPesos = gananciaEstimadaDLS * PRECIO_DOLAR;
            System.out.print("La ganancia estimada de un minero modelo "+minero+" a un rendimiento aleatorio de "+hashrateEquipo+" hashes por segundo es de $"+gananciaEstimadaPesos+" MXN"
                    + ", este minero tiene un precio de "+precioMinero+" en comparación al presupuesto de "+presupuesto);
        }
    }
    public void ejecutarGPU(){ ;
        for (int x = 0; x < comparativa.length; x++){
            System.out.println(comparativa[x]);
        }
    }
    public String cadena(){
        rateCorrespondiente = hashrateEquipo/hashrateRed;
        ganaciaBloqueDia = bloquesDiarios * recompensaBloque;
        gananciaEstimada = rateCorrespondiente * ganaciaBloqueDia;
        gananciaEstimadaDLS = gananciaEstimada * precioMoneda;
        gananciaEstimadaNeta = gananciaEstimadaDLS - (((wattsConsumo*COSTO_KILOWATT)/1000)*24);
        gananciaEstimadaPesos = gananciaEstimadaDLS * PRECIO_DOLAR;
        return "La ganancia estimada de un minero modelo "+minero+" a un rendimiento aleatorio de "+hashrateEquipo+" hashes por segundo es de $"+gananciaEstimadaPesos+" MXN"
                + ", este minero tiene un precio de "+precioMinero+" en comparación al presupuesto de "+presupuesto;
    }
}