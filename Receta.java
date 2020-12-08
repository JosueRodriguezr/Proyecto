package proyectoprimerparcial;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author josue
 */
public class Receta {

    protected static List<Producto> listaProductos = new ArrayList<>();
    protected List<Integer> cantidadProductos = new ArrayList<>();
    protected String preparacion;
    protected double calorias;
    protected double hidratos;
    protected double proteinas;
    protected double grasas;
    protected double fibras;
    protected String nombre;

    public Receta(List<Producto> ListaProductos, List<Integer> cantidad, String preparacion, String nombre) {
        Receta.listaProductos = ListaProductos;
        this.cantidadProductos = cantidad;
        this.preparacion = preparacion;
        this.nombre= nombre;
    }
    
    public Receta(List<Producto> ListaProductos) {
        Receta.listaProductos = ListaProductos;
        
    }
    
    public List<Producto> getListaProductos(){
        return Receta.listaProductos;
    }

    public void informacionNutricional() {
        for (int i = 0; i <= listaProductos.size(); i++) {
            if (i == 0) {
                i += 1;
            }
            this.calorias += (listaProductos.get(i - 1).getCalorias() * cantidadProductos.get(i - 1)) / 100;
            this.hidratos += (listaProductos.get(i - 1).getHidratos() * cantidadProductos.get(i - 1)) / 100;
            this.proteinas += (listaProductos.get(i - 1).getProteinas() * cantidadProductos.get(i - 1)) / 100;
            this.grasas += (listaProductos.get(i - 1).getGrasas() * cantidadProductos.get(i - 1)) / 100;
            this.fibras += (listaProductos.get(i - 1).getFibras() * cantidadProductos.get(i - 1)) / 100;
        }
        System.out.println("La receta contiene: " + this.calorias + " de calorias, " + this.hidratos + " de hidratos, " + this.proteinas + " de proteinas, " + this.grasas + " de grasas, " + this.fibras + " de fibras.");
    }

    @Override
    public String toString(){
        return this.nombre;
    }
    
    public void crearArchivo(){
        try {
            String ruta = this.nombre+".txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.nombre+","+this.preparacion+"\n");
            for (int i = 0; i <= listaProductos.size(); i++) {
            if (i == 0) {
                i += 1;
            }
                bw.write(listaProductos.get(i-1).getNombreProducto()+","+Integer.toString(cantidadProductos.get(i - 1))+"\n");
            }
            
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
