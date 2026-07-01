package notificaciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import envios.Direccion;
import envios.Registro;
import envios.Sucursal;
import pedido.Cliente;
import pedido.Pedido;
import reportes.ItemVisitor;

class NotificacionesTest {

    static class ItemStub extends ItemCatalogo {

        public ItemStub(Sucursal sucursal) {
            super("Item", "descripcion", sucursal);
        }

        @Override
        public float getPrecioBase() {
            return 1000;
        }

        @Override
        public float getPeso() {
            return 1;
        }

        @Override
        public boolean validar() {
            return true;
        }

        @Override
        public boolean tieneCategoria(String categoria) {
            return false;
        }

        @Override
        public void accept(ItemVisitor v) {
        }
    }

    static class MailSenderStub implements MailSender {

        String destinatario;
        String asunto;
        String mensaje;
        int cantidadMails;

        @Override
        public void enviarMail(String destinatario, String titulo, String mensaje) {
            this.destinatario = destinatario;
            this.asunto = titulo;
            this.mensaje = mensaje;
            cantidadMails++;
        }
    }

    private Pedido pedido;
    private MailSenderStub mail;

    @BeforeEach
    void setUp() {
        Direccion direccion = new Direccion(
                "Montevideo",
                "100",
                "Bernal",
                "Buenos Aires",
                "1876",
                5f);

        Cliente cliente = new Cliente("Marcos", "marcos@mail.com", direccion);

        pedido = new Pedido("ORD-99", cliente);
        mail = new MailSenderStub();

        Sucursal sucursal = new Sucursal();
        ItemStub item = new ItemStub(sucursal);

        Registro registro = new Registro();
        registro.setItem(item);
        registro.setCantidad(10);

        sucursal.agregarRegistro(registro);

        pedido.agregarItem(item, 1);
    }

    @Test
    void seEnviaMailAlConfirmar() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.confirmar();

        assertEquals(1, mail.cantidadMails);
        assertEquals("marcos@mail.com", mail.destinatario);
        assertTrue(mail.asunto.contains("Confirmado"));
    }

    @Test
    void seEnviaMailAlEnviar() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.confirmar();
        pedido.preparar();
        pedido.enviar();

        assertTrue(mail.asunto.contains("camino"));
    }

    @Test
    void seEnviaMailAlEntregar() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.confirmar();
        pedido.preparar();
        pedido.enviar();
        pedido.entregar();

        assertTrue(mail.asunto.contains("Entregado"));
    }

    @Test
    void seEnviaMailAlCancelar() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.cancelar();

        assertTrue(mail.asunto.contains("Cancelado"));
    }

    @Test
    void elMailMencionaLaNotaDeCredito() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.confirmar();
        pedido.cancelar();

        assertTrue(mail.mensaje.contains("nota de crédito"));
    }

    @Test
    void elMailTieneElNombreDelCliente() {
        pedido.suscribir(new NotificadorEmail(mail));

        pedido.confirmar();

        assertTrue(mail.mensaje.contains("Marcos"));
    }

    @Test
    void sePuedeUsarGeneradorFactura() {
        pedido.suscribir(new GeneradorFactura());

        pedido.confirmar();
        pedido.preparar();
        pedido.enviar();
        pedido.entregar();
    }

    @Test
    void sePuedeUsarFidelizacion() {
        pedido.suscribir(new Fidelizacion());

        pedido.cancelar();
    }
}