public class Simulacion{
    public double tiempoBloque, bloquesDiarios, recompensaBloque, hashrateRed, hashrateEquipo, precioMoneda, wattsConsumo, gananciaEstimada, rateCorrespondiente,
                  ganaciaBloqueDia, gananciaEstimadaDLS, gananciaEstimadaNeta, gananciaEstimadaPesos;
    public boolean imposible = false;
    public static String algoritmo, minero;
    public static final double COSTO_KILOWATT = .085, PRECIO_DOLAR = 20.87;
    NumAleatorios n = new NumAleatorios();
    public Simulacion(){}
    public void establecer(int[] subseleccion){
        if(subseleccion[0] == 1){
            //Opción para minero GPU
            if(subseleccion[1] == 1){
                algoritmo = "Zhash";
                tiempoBloque = 9.46;
                bloquesDiarios = 152.112676;
                recompensaBloque = 6.25;
                hashrateRed = 2510000;
                precioMoneda = 91.92;
                if(subseleccion[2] == 1){
                    minero = "GTX 1050ti";
                    wattsConsumo = 80;
                }else if(subseleccion[2] == 2){
                    minero = "RX 5700";
                    wattsConsumo = 140;
                }else if(subseleccion[2] == 3){
                    minero = "RTX 3080";
                    wattsConsumo = 250;
                }
            }
            else if(subseleccion[1] == 2){
                algoritmo = "Ethash";
                tiempoBloque = .22416667;
                bloquesDiarios = 6423.79182;
                recompensaBloque = 3.79;
                hashrateRed = 511980000;
                precioMoneda = 2312.35;
                if(subseleccion[2] == 1){
                    minero = "GTX 1050ti";
                    wattsConsumo = 0;
                }else if(subseleccion[2] == 2){
                    minero = "RX 5700";
                    wattsConsumo = 130;
                }else if(subseleccion[2] == 3){
                    minero = "RTX 3080";
                    wattsConsumo = 230;
                }
            }
            else if(subseleccion[1] == 3){
                algoritmo = "CryptoNightGPU";
                tiempoBloque = 1.95;
                bloquesDiarios = 738.46;
                recompensaBloque = 12;
                hashrateRed = 1500000;
                precioMoneda = .491;
                if(subseleccion[2] == 1){
                    minero = "GTX 1050ti";
                    wattsConsumo = 60;
                }else if(subseleccion[2] == 2){
                    minero = "RX 5700";
                    wattsConsumo = 140;
                }else if(subseleccion[2] == 3){
                    minero = "RTX 3080";
                    wattsConsumo = 250;
                }
            }
        }else if(subseleccion[0] == 2){
            //Opción para minero ASIC
            if(subseleccion[1] == 1){
                algoritmo = "X11";
                // se para por 7 ceros, añadir una multiplicacion de x10000000 y ver que pasa
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
                //multiplicacion x100 y ver que pasa
                tiempoBloque = 9.75;
                bloquesDiarios = 147.69231;
                recompensaBloque = 7.25;
                hashrateRed = 1731347700;
                precioMoneda = 55173;
            }
            if(subseleccion[2] == 1){
                minero = "ANTMINER D3";
                wattsConsumo = 800;
            }else if(subseleccion[2] == 2){
                minero = "INNOSILICON A11";
                wattsConsumo = 2600;
            }else if(subseleccion[2] == 3){
                minero = "ANTMINER S9";
                wattsConsumo = 1372;
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
            hashrateEquipo = n.numero(4519)/100;
            rateCorrespondiente = hashrateEquipo/hashrateRed;
            ganaciaBloqueDia = bloquesDiarios * recompensaBloque;
            gananciaEstimada = rateCorrespondiente * ganaciaBloqueDia;
            gananciaEstimadaDLS = gananciaEstimada * precioMoneda;
            gananciaEstimadaNeta = gananciaEstimadaDLS - (((wattsConsumo*COSTO_KILOWATT)/1000)*24);
            gananciaEstimadaPesos = gananciaEstimadaDLS * PRECIO_DOLAR;
            System.out.print("La ganancia estimada de un minero modelo "+minero+" a un rendimiento aleatorio de "+hashrateEquipo+" hashes por segundo es de $"+gananciaEstimadaPesos+" MXN");
        }
    }
}
