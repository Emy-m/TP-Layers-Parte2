package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import serviciomail.SmtpException;

public class ReconocimientoCumpleaños {
	
	private IAlmacenamiento almacenamientoEmpleados;
	private IEnvioEmail envioDeEmails;
	
	public ReconocimientoCumpleaños(IAlmacenamiento almacenamientoEmpleados, IEnvioEmail envioDeEmails) {
		this.almacenamientoEmpleados = almacenamientoEmpleados;
		this.envioDeEmails = envioDeEmails;
	}
	
	public boolean empleadoGuardado(Empleado empleado) throws DatosInvalidosEmpleadoException {
		return almacenamientoEmpleados.empleadoGuardado(empleado);
	}
	
	public boolean empleadoCumpleañero(Empleado empleado, String fechaDeCumpleaños) throws DatosInvalidosEmpleadoException {
		return empleado.empleadoCumpleAñosEnFecha(new FechaDeNacimiento(fechaDeCumpleaños));
	}
	
	public void enviarReconocimientoDeCumpleañosAEmpleados() throws DatosInvalidosEmpleadoException, SmtpException {
		ArrayList<Empleado> empleados = almacenamientoEmpleados.devolverEmpleadosCumpleañeros();
		LocalDate hoy = LocalDate.now();
		
		for(Empleado empleado : empleados) {
			if(empleado.empleadoCumpleAñosEnFecha(hoy)) {
				envioDeEmails.enviar(empleado.obtenerDireccionEmail(), "Email cumpleaños", "Feliz cumple" + empleado.obtenerNombre() + " " + empleado.obtenerApellido());
			}
		}
	}
}