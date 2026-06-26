package notificaciones;

import pedido.*;

public class NotificadorEmail implements Observador {

    private MailSender mailSender;

    public NotificadorEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void actualizar(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
        String asunto = "Pedido #" + pedido.getId();
        String mensaje = "Hola " + pedido.getCliente().getNombre() + ",\n\n";

        if (nuevo instanceof EstadoConfirmado) {
            asunto += " - Confirmado";
            mensaje += "Tu pedido fue confirmado.\n";
            mensaje += "Total: $" + pedido.getTotal() + "\n";
        } else if (nuevo instanceof EstadoEnviado) {
            asunto += " - En camino";
            mensaje += "Tu pedido está en camino.\n";
            mensaje += "Fecha estimada: " + pedido.getFechaEntrega() + "\n";
        } else if (nuevo instanceof EstadoEntregado) {
            asunto += " - Entregado";
            mensaje += "Tu pedido fue entregado.\n";
        } else if (nuevo instanceof EstadoCancelado) {
            asunto += " - Cancelado";
            mensaje += "Tu pedido fue cancelado.\n";

            if (!pedido.getNotasCredito().isEmpty()) {
                mensaje += "Se generó una nota de crédito.\n";
            }
        } else {
            asunto += " - Actualización";
            mensaje += "El estado de tu pedido cambió.\n";
        }

        mensaje += "\nGracias por tu compra.";

        mailSender.enviarMail(
                pedido.getCliente().getEmail(),
                asunto,
                mensaje
        );
    }
}