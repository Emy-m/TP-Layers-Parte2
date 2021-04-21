package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.DatosInvalidosEmpleadoException;
import modelo.Empleado;
import modelo.IAlmacenamiento;

public class EnMemoriaIAlmacenamiento implements IAlmacenamiento {

	private ArrayList<String> empleados = new ArrayList<String>();
	
	@Override
	public void copiarEmpleado(Empleado empleado) {
		empleados.add(empleado.toString());
	}

	@Override
	public boolean empleadoGuardado(Empleado empleado) {
		return empleados.contains(empleado.toString());
	}

	@Override
	public ArrayList<Empleado> devolverEmpleadosCumplea�eros() throws DatosInvalidosEmpleadoException {
		ArrayList<Empleado> empleadosCumplea�eros = new ArrayList<Empleado>();
		LocalDate hoy = LocalDate.now();
		Empleado empleadoCreado;
		
		for(String empleado : empleados) {
			empleadoCreado = Empleado.empleadoPorString(empleado);
			
			if(empleadoCreado.empleadoCumpleA�osEnFecha(hoy)) {
				empleadosCumplea�eros.add(empleadoCreado);
			}
		}
		
		return empleadosCumplea�eros;
	}

}
