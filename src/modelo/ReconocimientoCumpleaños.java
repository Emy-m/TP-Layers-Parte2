package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import serviciomail.SmtpException;

public class ReconocimientoCumpleaņos {
	
	private IAlmacenamiento almacenamientoEmpleados;
	private IEnvioEmail envioDeEmails;
	
	public ReconocimientoCumpleaņos(IAlmacenamiento almacenamientoEmpleados, IEnvioEmail envioDeEmails) {
		this.almacenamientoEmpleados = almacenamientoEmpleados;
		this.envioDeEmails = envioDeEmails;
	}
	
	public boolean empleadoGuardado(Empleado empleado) throws DatosInvalidosEmpleadoException {
		return almacenamientoEmpleados.empleadoGuardado(empleado);
	}
	
	public boolean empleadoCumpleaņero(Empleado empleado, String fechaDeCumpleaņos) throws DatosInvalidosEmpleadoException {
		return empleado.empleadoCumpleAņosEnFecha(new FechaDeNacimiento(fechaDeCumpleaņos));
	}
	
	public void enviarReconocimientoDeCumpleaņosAEmpleados() throws Exception {
		ArrayList<Empleado> empleados = almacenamientoEmpleados.devolverEmpleadosCumpleaņeros();
		LocalDate hoy = LocalDate.now();
		
		for(Empleado empleado : empleados) {
			if(empleado.empleadoCumpleAņosEnFecha(hoy)) {
				envioDeEmails.enviar(empleado.obtenerDireccionEmail(), "Email cumpleaņos", "Feliz cumple" + empleado.obtenerNombre() + " " + empleado.obtenerApellido());
			}
		}
	}
}