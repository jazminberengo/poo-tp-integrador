package pedido;

import envio.Direccion;

/*
 * Representa al cliente que realiza un pedido.
 * Su dirección es usada por para calcular el costo.
 */
public class Cliente {

    private final String nombre;
    private final String email;
    private final Direccion direccion;

    public Cliente(String nombre, String email, Direccion direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre()      { return nombre; }
    public String getEmail()       { return email; }
    public Direccion getDireccion(){ return direccion; }
}
