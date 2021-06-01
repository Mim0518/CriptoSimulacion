public class Grafica {
    String nombre;
    double precio;
    double gananciaMedia;
    int comprablesPresupuesto;
    double sobrantePresupuesto;
    double ganananciaFinal;


    public Grafica(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getGananciaMedia() {
        return gananciaMedia;
    }

    public void setGananciaMedia(double gananciaMedia) {
        this.gananciaMedia = gananciaMedia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getComprablesPresupuesto() {
        return comprablesPresupuesto;
    }

    public void setComprablesPresupuesto(int comprablesPresupuesto) {
        this.comprablesPresupuesto = comprablesPresupuesto;
    }

    public double getSobrantePresupuesto() {
        return sobrantePresupuesto;
    }

    public void setSobrantePresupuesto(double sobrantePresupuesto) {
        this.sobrantePresupuesto = sobrantePresupuesto;
    }

    public double getGanananciaFinal() {
        return ganananciaFinal;
    }

    public void setGanananciaFinal(double ganananciaFinal) {
        this.ganananciaFinal = ganananciaFinal;
    }
    public void optimoPre(double presupuesto){
        this.comprablesPresupuesto = (int) Math.floor(presupuesto / precio);
        this.sobrantePresupuesto = presupuesto - comprablesPresupuesto * precio;
        this.ganananciaFinal = gananciaMedia * comprablesPresupuesto;
    }
}
