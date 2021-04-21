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
}
