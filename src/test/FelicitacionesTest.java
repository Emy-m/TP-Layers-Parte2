package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import modelo.DatosInvalidosEmpleadoException;
import modelo.Empleado;
import modelo.IAlmacenamiento;
import modelo.IEnvioEmail;
import modelo.ReconocimientoCumpleaños;
import persistencia.EnMemoriaIAlmacenamiento;
import serviciomail.ProtocoloEnvioEmail;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FelicitacionesTest {
	@Test
	public void testFelicitacionDeCumpleaños() throws DatosInvalidosEmpleadoException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		Empleado empleadoEmilio = new Empleado("Emilio", "Martin", LocalDate.of(1996, 05, 20).format(formato), "emilio@gmail.com");
		Empleado empleadoOtro = new Empleado("Otro", "Empleado", LocalDate.of(1990, 05, 20).format(formato), "otroE@gmail.com");
		
		IAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		IEnvioEmail envioDeEmails = new ProtocoloEnvioEmail();
		almacenamiento.copiarEmpleado(empleadoOtro);
		almacenamiento.copiarEmpleado(empleadoEmilio);
		
		ReconocimientoCumpleaños reconocimiento = new ReconocimientoCumpleaños(almacenamiento, envioDeEmails);
		assertDoesNotThrow(() -> reconocimiento.enviarReconocimientoDeCumpleañosAEmpleados());
	}
	
	//testear que el empleado que se retorna sea el que cumple años
	@Test
	public void testCreacionYRetornoDeEmpleadoCumpleañero() throws DatosInvalidosEmpleadoException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String fechaDeHoy = LocalDate.now().format(formato);
		Empleado empleadoEmilio = new Empleado("Emilio", "Martin", fechaDeHoy, "emilio@gmail.com");
		Empleado empleadoOtro = new Empleado("Otro", "Empleado", LocalDate.of(1990, 05, 20).format(formato), "otroE@gmail.com");
		
		IAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		IEnvioEmail envioDeEmails = new ProtocoloEnvioEmail();
		almacenamiento.copiarEmpleado(empleadoOtro);
		almacenamiento.copiarEmpleado(empleadoEmilio);
		
		ReconocimientoCumpleaños reconocimiento = new ReconocimientoCumpleaños(almacenamiento, envioDeEmails);
		assertTrue(reconocimiento.empleadoGuardado(empleadoEmilio) && reconocimiento.empleadoCumpleañero(empleadoEmilio, fechaDeHoy));
	}
}
