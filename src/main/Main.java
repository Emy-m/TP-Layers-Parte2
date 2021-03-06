package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.DatosInvalidosEmpleadoException;
import modelo.Empleado;
import modelo.IAlmacenamiento;
import modelo.IEnvioEmail;
import modelo.ReconocimientoCumpleaņos;
import persistencia.EnMemoriaIAlmacenamiento;
import serviciomail.ProtocoloEnvioEmail;
import serviciomail.SmtpException;

public class Main {
	public static void main(String[] args) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		Empleado empleadoEmilio;
		Empleado empleadoOtro;
		
		try {
			empleadoEmilio = new Empleado("Emilio", "Martin", LocalDate.of(1996, 05, 20).format(formato), "emilio@gmail.com");
			empleadoOtro = new Empleado("Otro", "Empleado", LocalDate.of(1990, 05, 20).format(formato), "otroE@gmail.com");
			
			IAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
			IEnvioEmail envioDeEmails = new ProtocoloEnvioEmail();
			almacenamiento.copiarEmpleado(empleadoOtro);
			almacenamiento.copiarEmpleado(empleadoEmilio);
			
			ReconocimientoCumpleaņos reconocimiento = new ReconocimientoCumpleaņos(almacenamiento, envioDeEmails);
			reconocimiento.enviarReconocimientoDeCumpleaņosAEmpleados();
		} catch (DatosInvalidosEmpleadoException e) {
			System.out.println("Error creando los empleados " + e.getMessage());
		} catch (SmtpException e) {
			System.out.println("Error enviando el reconocimiento de cumpleaņos " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado " + e.getMessage());
		}
	}
}