package modelo;

public interface IEnvioEmail {
	public abstract void conectarConElProtocolo() throws Exception;
	public abstract void enviar(String direccionEmail, String tituloEmail, String mensaje) throws Exception;
}
