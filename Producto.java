package proyectoprimerparcial;

/**
 *
 * @author josue
 */
public class Producto {

    protected String nombreProducto;
    protected String calorias;
    protected String hidratos;
    protected String proteinas;
    protected String grasas;
    protected String fibras;

    public Producto(String nombreProducto, String calorias, String hidratos, String proteinas, String grasas, String fibras) {
        this.nombreProducto = nombreProducto;
        this.calorias = calorias;
        this.hidratos = hidratos;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.fibras = fibras;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public double getCalorias() {
        return Double.parseDouble(this.calorias);
    }

    public double getHidratos() {
        return Double.parseDouble(this.hidratos);
    }

    public double getProteinas() {
        return Double.parseDouble(this.proteinas);
    }

    public double getGrasas() {
        return Double.parseDouble(this.grasas);
    }

    public double getFibras() {
        return Double.parseDouble(this.fibras);
    }

    @Override
    public String toString() {
        return this.nombreProducto;
    }
}
