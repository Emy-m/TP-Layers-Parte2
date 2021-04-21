package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empleado {
	private String nombre;
	private String apellido;
	private FechaDeNacimiento fechaNacimiento;
	private DireccionEmail email;
	
	public Empleado(String nombre, String apellido, String fechaNacimiento, String email) throws DatosInvalidosEmpleadoException {
		if (stringVacio(nombre) || esNulo(nombre)) {
			throw new DatosInvalidosEmpleadoException("Nombre");
		}
		if (stringVacio(apellido) || esNulo(apellido)) {
			throw new DatosInvalidosEmpleadoException("Apellido");
		}
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = new FechaDeNacimiento(fechaNacimiento);
		this.email = new DireccionEmail(email);
		
	}
	
	public static Empleado empleadoPorString(String empleado) throws DatosInvalidosEmpleadoException {
		String[] empleadoSeparado = empleado.split(",");
		String apellido = empleadoSeparado[0].trim();
		String nombre = empleadoSeparado[1].trim();
		String fechaNacimiento = empleadoSeparado[2].trim();
		String email = empleadoSeparado[3].trim();
		
		return new Empleado(apellido, nombre, fechaNacimiento, email);
	}
	
	private boolean stringVacio(String string) {
		return string.isEmpty();
	}

	private boolean esNulo(String string) {
		return string == null;
	}
	
	public boolean empleadoCumpleAñosEnFecha(LocalDate fecha) {
		return fechaNacimiento.fechaEsCumpleaños(fecha);
	}
	
	public boolean empleadoCumpleAñosEnFecha(FechaDeNacimiento fecha) {
		return fechaNacimiento.fechaEsCumpleaños(fecha);
	}

	@Override
	public String toString() {
		return apellido + ", " + nombre + ", " + fechaNacimiento + ", " + email.toString();
	}

	public String obtenerDireccionEmail() {
		return email.toString();
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String obtenerApellido() {
		return apellido;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
