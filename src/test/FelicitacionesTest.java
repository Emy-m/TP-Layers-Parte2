package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Test;

import modelo.DatosInvalidosEmpleadoException;
import modelo.Empleado;
import modelo.IAlmacenamiento;
import modelo.IEnvioEmail;
import modelo.ReconocimientoCumplea�os;
import persistencia.EnMemoriaIAlmacenamiento;
import serviciomail.ProtocoloEnvioEmail;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FelicitacionesTest {
	@Test
	public void testCreacionEmpleado() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate hoy = LocalDate.now();
		String fechaDeHoy = hoy.format(formato);
		String fechaDeAyer = hoy.minusDays(1).format(formato);
		
		assertDoesNotThrow(() -> {
			Empleado empleadoEmilio = new Empleado("Emilio", "Martin", fechaDeHoy, "emilio@gmail.com");
			Empleado empleadoOtro = new Empleado("Otro", "Empleado", fechaDeAyer, "otroE@gmail.com");
		});
	}
	
	//testear que el empleado que se retorna sea el que cumple a�os
	@Test
	public void testRetornoDeEmpleadoCumplea�ero() throws DatosInvalidosEmpleadoException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate hoy = LocalDate.now();
		String fechaDeHoy = hoy.format(formato);
		String fechaDeAyer = hoy.minusDays(1).format(formato);
		
		Empleado empleadoEmilio = new Empleado("Emilio", "Martin", fechaDeHoy, "emilio@gmail.com");
		Empleado empleadoOtro = new Empleado("Otro", "Empleado", fechaDeAyer, "otroE@gmail.com");
		
		IAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		almacenamiento.copiarEmpleado(empleadoOtro);
		almacenamiento.copiarEmpleado(empleadoEmilio);
		ArrayList<Empleado> empleadosCumplea�eros = almacenamiento.devolverEmpleadosCumplea�eros();
		
		assertTrue(empleadosCumplea�eros.contains(empleadoEmilio));
		assertFalse(empleadosCumplea�eros.contains(empleadoOtro));
	}
	
	@Test
	public void testFelicitacionDeCumplea�os() throws DatosInvalidosEmpleadoException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
		Empleado empleadoEmilio = new Empleado("Emilio", "Martin", LocalDate.of(1996, 05, 20).format(formato), "emilio@gmail.com");
		Empleado empleadoOtro = new Empleado("Otro", "Empleado", LocalDate.of(1990, 05, 20).format(formato), "otroE@gmail.com");
		
		IAlmacenamiento almacenamiento = new EnMemoriaIAlmacenamiento();
		IEnvioEmail envioDeEmails = new ProtocoloEnvioEmail();
		almacenamiento.copiarEmpleado(empleadoOtro);
		almacenamiento.copiarEmpleado(empleadoEmilio);
		
		ReconocimientoCumplea�os reconocimiento = new ReconocimientoCumplea�os(almacenamiento, envioDeEmails);
		assertDoesNotThrow(() -> reconocimiento.enviarReconocimientoDeCumplea�osAEmpleados());
	}
}
