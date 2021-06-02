public class Grafica {
    String nombre;
    double precio;
    int comprablesPresupuesto;
    double sobrantePresupuesto;
    public Grafica(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
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

    public void optimoPre(double presupuesto){
        this.comprablesPresupuesto = (int) Math.floor(presupuesto / precio);
        this.sobrantePresupuesto = presupuesto - comprablesPresupuesto * precio;

    }
}
