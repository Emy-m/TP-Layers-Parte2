package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaDeNacimiento {

	private LocalDate fecha;
	private static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	public FechaDeNacimiento(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public FechaDeNacimiento(String fecha) throws DatosInvalidosEmpleadoException {
		if (stringVacio(fecha) || esNulo(fecha)) {
			throw new DatosInvalidosEmpleadoException("Fecha Nacimiento");
		}
		
		this.fecha = LocalDate.parse(fecha, FORMATO);
	}
	
	public boolean fechaEsCumpleaños(LocalDate fecha) {
		int diaDelMes = this.fecha.getDayOfMonth();
		int mes = this.fecha.getMonthValue();
		
		return fecha.getDayOfMonth() == diaDelMes && fecha.getMonthValue() == mes;
	}
	
	public boolean fechaEsCumpleaños(FechaDeNacimiento fecha) {
		return this.equals(fecha);
	}
	
	private boolean stringVacio(String string) {
		return string.isEmpty();
	}

	private boolean esNulo(String string) {
		return string == null;
	}

	@Override
	public String toString() {
		return fecha.format(FORMATO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FechaDeNacimiento other = (FechaDeNacimiento) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}
}
