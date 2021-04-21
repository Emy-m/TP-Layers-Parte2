package serviciomail;

import modelo.IEnvioEmail;

public class ProtocoloEnvioEmail implements IEnvioEmail {

	@Override
	public void conectarConElProtocolo() throws SmtpException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enviar(String direccionEmail, String tituloEmail, String mensaje) throws SmtpException {
		new Email(direccionEmail, tituloEmail, mensaje).enviar();
	}

}
