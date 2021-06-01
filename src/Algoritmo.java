public class Algoritmo {
    String algoritmo;
    double tiempoBloque;
    double bloqueDiarios;
    double recompensaBloque;
    double hashrateRed;
    double precioMoneda;
    double gananciaBloqueDia;

    public Algoritmo(String algoritmo, double tiempoBloque, double bloqueDiarios, double recompensaBloque, double hashrateRed, double precioMoneda) {
        this.algoritmo = algoritmo;
        this.tiempoBloque = tiempoBloque;
        this.bloqueDiarios = bloqueDiarios;
        this.recompensaBloque = recompensaBloque;
        this.hashrateRed = hashrateRed;
        this.precioMoneda = precioMoneda;
    }

    public double getGananciaBloqueDia() {
        return gananciaBloqueDia;
    }

    public void setGananciaBloqueDia(double gananciaBloqueDia) {
        this.gananciaBloqueDia = gananciaBloqueDia;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getTiempoBloque() {
        return tiempoBloque;
    }

    public void setTiempoBloque(double tiempoBloque) {
        this.tiempoBloque = tiempoBloque;
    }

    public double getBloqueDiarios() {
        return bloqueDiarios;
    }

    public void setBloqueDiarios(double bloqueDiarios) {
        this.bloqueDiarios = bloqueDiarios;
    }

    public double getRecompensaBloque() {
        return recompensaBloque;
    }

    public void setRecompensaBloque(double recompensaBloque) {
        this.recompensaBloque = recompensaBloque;
    }

    public double getHashrateRed() {
        return hashrateRed;
    }

    public void setHashrateRed(double hashrateRed) {
        this.hashrateRed = hashrateRed;
    }

    public double getPrecioMoneda() {
        return precioMoneda;
    }

    public void setPrecioMoneda(double precioMoneda) {
        this.precioMoneda = precioMoneda;
    }
}
