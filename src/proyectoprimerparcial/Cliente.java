package proyectoprimerparcial;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author josue
 */
public class Cliente {

    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String correoElectronico;
    protected String direccion;
    protected Calendar fecha;

    public Cliente(String cedula, String nombres, String apellidos, String telefono, String correoElectronico, String direccion, Calendar c1) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.fecha = c1;
    }

    public int diaSuscripcion() {
        return this.fecha.get(Calendar.DATE);

    }

    public int mesSuscripcion() {
        return this.fecha.get(Calendar.MONTH);
    }

    public int annioSuscripcion() {
        return this.fecha.get(Calendar.YEAR);
    }
    
    public String getCorreoElectronico(){
        return this.correoElectronico;
    }

    public boolean validarSuscripcion() {
        Date fechaC = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaC);
        int mesAc = c.get(Calendar.MONTH);
        int diaAc = c.get(Calendar.DATE);
        int diferenciaMes = (mesAc - mesSuscripcion());
        if (diferenciaMes > 0 && diaAc > diaSuscripcion()) {
            return false;
        } else if (diferenciaMes > 0 && diaAc <= diaSuscripcion()) {
            return true;
        } else if (diferenciaMes == 0 && diaSuscripcion() <= diaAc) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.nombres + " " + this.apellidos + " Con numero de Cedula: " + this.cedula;
    }

}
