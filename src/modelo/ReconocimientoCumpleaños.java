package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import serviciomail.SmtpException;

public class ReconocimientoCumplea�os {
	
	private IAlmacenamiento almacenamientoEmpleados;
	private IEnvioEmail envioDeEmails;
	
	public ReconocimientoCumplea�os(IAlmacenamiento almacenamientoEmpleados, IEnvioEmail envioDeEmails) {
		this.almacenamientoEmpleados = almacenamientoEmpleados;
		this.envioDeEmails = envioDeEmails;
	}
	
	public boolean empleadoGuardado(Empleado empleado) throws DatosInvalidosEmpleadoException {
		return almacenamientoEmpleados.empleadoGuardado(empleado);
	}
	
	public boolean empleadoCumplea�ero(Empleado empleado, String fechaDeCumplea�os) throws DatosInvalidosEmpleadoException {
		return empleado.empleadoCumpleA�osEnFecha(new FechaDeNacimiento(fechaDeCumplea�os));
	}
	
	public void enviarReconocimientoDeCumplea�osAEmpleados() throws DatosInvalidosEmpleadoException, SmtpException {
		ArrayList<Empleado> empleados = almacenamientoEmpleados.devolverEmpleadosCumplea�eros();
		LocalDate hoy = LocalDate.now();
		
		for(Empleado empleado : empleados) {
			if(empleado.empleadoCumpleA�osEnFecha(hoy)) {
				envioDeEmails.enviar(empleado.obtenerDireccionEmail(), "Email cumplea�os", "Feliz cumple" + empleado.obtenerNombre() + " " + empleado.obtenerApellido());
			}
		}
	}
}