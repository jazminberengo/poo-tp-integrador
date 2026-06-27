package pedido;

import java.time.LocalDate;

/*
 * Representa un reembolso emitido cuando un pedido se cancela.
 * El monto se pasa como parámetro porque es responsabilidad 
 * del estado desde el cual se cancele. 
 * - Desde CONFIRMADO: Se reembolsan productos + envío.
 * - Desde ENVIADO:    Se reembolsan solo los productos.
 */
public class NotaCredito {

    private final float monto;
    private final LocalDate fecha;
    private final String motivo;

    public NotaCredito(float monto, String motivo) {
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = LocalDate.now();
    }

    public float getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }
}
