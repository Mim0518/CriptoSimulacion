public class RendimientoGraficas {
    String nom;
    String algoritmo;
    double hashes;
    double watts;
    double rateCorrespondiente;
    double ganaciaBloqueDia;
    double gananciaEstimada;
    double gananciaEstimadaDLS;
    double gananciaEstimadaNeta;
    double gananciaEstimadaPesos;

    public RendimientoGraficas(String nom, String algoritmo, double hashes, double watts) {
        this.nom = nom;
        this.algoritmo = algoritmo;
        this.hashes = hashes;
        this.watts = watts;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getHashes() {
        return hashes;
    }

    public void setHashes(double hashes) {
        this.hashes = hashes;
    }

    public double getWatts() {
        return watts;
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }

    public double getRateCorrespondiente() {
        return rateCorrespondiente;
    }

    public void setRateCorrespondiente(double rateCorrespondiente) {
        this.rateCorrespondiente = rateCorrespondiente;
    }

    public double getGanaciaBloqueDia() {
        return ganaciaBloqueDia;
    }

    public void setGanaciaBloqueDia(double ganaciaBloqueDia) {
        this.ganaciaBloqueDia = ganaciaBloqueDia;
    }

    public double getGananciaEstimada() {
        return gananciaEstimada;
    }

    public void setGananciaEstimada(double gananciaEstimada) {
        this.gananciaEstimada = gananciaEstimada;
    }

    public double getGananciaEstimadaDLS() {
        return gananciaEstimadaDLS;
    }

    public void setGananciaEstimadaDLS(double gananciaEstimadaDLS) {
        this.gananciaEstimadaDLS = gananciaEstimadaDLS;
    }

    public double getGananciaEstimadaNeta() {
        return gananciaEstimadaNeta;
    }

    public void setGananciaEstimadaNeta(double gananciaEstimadaNeta) {
        this.gananciaEstimadaNeta = gananciaEstimadaNeta;
    }

    public double getGananciaEstimadaPesos() {
        return gananciaEstimadaPesos;
    }

    public void setGananciaEstimadaPesos(double gananciaEstimadaPesos) {
        this.gananciaEstimadaPesos = gananciaEstimadaPesos;
    }
}
