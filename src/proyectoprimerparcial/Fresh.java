package proyectoprimerparcial;

import java.util.Calendar;

/**
 *
 * @author josue
 */

public class Fresh extends Cliente {

    protected Calendar fecha;

    public Fresh(String cedula, String nombres, String apellidos, String telefono, String correoElectronico, String direccion, Calendar c1) {
        super(cedula, nombres, apellidos, telefono, correoElectronico, direccion, c1);

    }
}
