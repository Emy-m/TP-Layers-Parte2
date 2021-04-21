package modelo;

import java.util.ArrayList;

public interface IAlmacenamiento {
	abstract void copiarEmpleado(Empleado empleado);
	abstract ArrayList<Empleado> devolverEmpleadosCumpleañeros() throws DatosInvalidosEmpleadoException;
	abstract boolean empleadoGuardado(Empleado empleado);
}
