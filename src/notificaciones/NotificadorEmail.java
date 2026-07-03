package notificaciones;

import pedido.*;

public class NotificadorEmail implements Observador {

    private MailSender mailSender;

    public NotificadorEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onEntregado(Pedido pedido) {
    	String asunto = "Pedido #" + pedido.getId() + " - Entregado";
    	String mensaje = "Hola " + pedido.getCliente().getNombre() + ",\n\n" + "Tu pedido fue entregado.\n";
    	enviarPorCorreo(pedido, asunto, mensaje);
    }
    
    @Override
    public void onConfirmado(Pedido pedido) {
    	String asunto = "Pedido #" + pedido.getId() + " - Confirmado";
        String mensaje = "Hola " + pedido.getCliente().getNombre() + ",\n\n" + "Tu pedido fue confirmado.\n" + 
    	                 "Total: $" + pedido.getTotal() + "\n";
        enviarPorCorreo(pedido, asunto, mensaje);
    }
    
    @Override
    public void onEnviado(Pedido pedido) {
    	String asunto = "Pedido #" + pedido.getId() + " - En camino";
    	String mensaje = "Hola " + pedido.getCliente().getNombre() + ",\n\n" + "Tu pedido está en camino.\n";
    	enviarPorCorreo(pedido, asunto, mensaje);
    }
    
    private void enviarPorCorreo(Pedido pedido, String asunto, String mensaje) {
        mailSender.enviarMail(
                pedido.getCliente().getEmail(),
                asunto,
                mensaje
        );
    }
}