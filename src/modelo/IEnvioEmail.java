package modelo;

import serviciomail.SmtpException;

public interface IEnvioEmail {
	public abstract void conectarConElProtocolo() throws SmtpException;
	public abstract void enviar(String direccionEmail, String tituloEmail, String mensaje) throws SmtpException;
}
