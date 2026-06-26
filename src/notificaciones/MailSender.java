package notificaciones;

/*
   Abstracción de la infraestructura de correo electrónico.
   Permite desacoplar NotificadorEmail de cualquier proveedor concreto.
 */
public interface MailSender {

    /*
       Envía un correo electrónico.
      
       destinatario  =  dirección de correo del receptor
       titulo        =  asunto del mensaje
       mensaje       =  cuerpo del mensaje 
     */
    void enviarMail(String destinatario, String titulo, String mensaje);
}
