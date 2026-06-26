package pedido;

import java.time.LocalDate;

/*
   Representa un reembolso emitido cuando un pedido se cancela.
  
   El monto varía según desde qué estado se cancela:
   - Desde CONFIRMADO: reembolso total (productos + envío)
   - Desde ENVIADO:    reembolso parcial (solo productos)
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
