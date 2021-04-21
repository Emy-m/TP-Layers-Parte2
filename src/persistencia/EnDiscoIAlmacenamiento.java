package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.DatosInvalidosEmpleadoException;
import modelo.Empleado;
import modelo.IAlmacenamiento;

public class EnDiscoIAlmacenamiento implements IAlmacenamiento {
	public static String DIRECCION = "C:\\Users\\Emy_m\\Desktop\\EmpleadosTest.txt";
	private String direccion;
	
	public EnDiscoIAlmacenamiento(String direccion) {
		this.direccion = direccion;
	}
	
	public void copiarEmpleado(Empleado empleado) {
		try {
			FileWriter archivo = new FileWriter(direccion, true);
			archivo.write(empleado.toString() + "\n");
			archivo.close();
		} catch (IOException e) {
			throw new RuntimeException("Error de entrada/salida", e);
		}
	}

	@Override
	public boolean empleadoGuardado(Empleado empleado) {
		try {
			return Files.lines(Paths.get(direccion)).anyMatch(linea -> linea.contains(empleado.toString()));
		} catch (IOException e) {
			throw new RuntimeException("Error de entrada/salida", e);
		}
	}

	@Override
	public ArrayList<Empleado> devolverEmpleadosCumpleañeros() throws DatosInvalidosEmpleadoException {
		try {
			LocalDate hoy = LocalDate.now();
			ArrayList<Empleado> empleadosCumpleañeros = new ArrayList<Empleado>();
			Empleado empleadoCreado;
			
			Iterator<String> iteradorEmpleados = Files.lines(Paths.get(direccion)).iterator();
			
			while(iteradorEmpleados.hasNext()) {
				String empleado = iteradorEmpleados.next();
				try {
					empleadoCreado = Empleado.empleadoPorString(empleado);
					
					if(empleadoCreado.empleadoCumpleAñosEnFecha(hoy)) {
						empleadosCumpleañeros.add(empleadoCreado);
					}
				} catch (DatosInvalidosEmpleadoException e) {
					throw e;
				}
			}
			
			return empleadosCumpleañeros;
		} catch (IOException e) {
			throw new RuntimeException("Error de entrada/salida", e);
		}
	}
}
