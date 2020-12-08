package proyectoprimerparcial;

import java.util.Calendar;

/**
 *
 * @author josue
 */
public class Vip extends Cliente {

    protected double peso;
    protected double estatura;
    protected double horasEjercicioXsemana;
    protected String profesion;
    protected double indiceMasa;

    public Vip(String cedula, String nombres, String apellidos, String telefono, String correo, String direccion, Calendar c1, double peso, double estatura, double horasEjercicioXsemana, String profesion) {
        super(cedula, nombres, apellidos, telefono, correo, direccion, c1);
        this.peso = peso;
        this.estatura = estatura;
        this.horasEjercicioXsemana = horasEjercicioXsemana;
        this.profesion = profesion;
    }

    public double getIndiceMasa() {
        indiceMasa = (this.peso * (this.estatura * this.estatura));
        return indiceMasa;
    }
}
