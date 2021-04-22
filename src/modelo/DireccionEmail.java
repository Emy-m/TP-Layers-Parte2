package modelo;

public class DireccionEmail {

	private String direccionEmail;
	
	public DireccionEmail(String direccionEmail) throws DatosInvalidosEmpleadoException {
		if(!validarDireccionEmail(direccionEmail)) {
			throw new DatosInvalidosEmpleadoException("Direccion Email");
		}
		
		this.direccionEmail = direccionEmail;
	}
	
	private boolean validarDireccionEmail(String direccion) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return direccion.matches(regex);
	}

	@Override
	public String toString() {
		return direccionEmail;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DireccionEmail other = (DireccionEmail) obj;
		if (direccionEmail == null) {
			if (other.direccionEmail != null)
				return false;
		} else if (!direccionEmail.equals(other.direccionEmail))
			return false;
		return true;
	}
}
