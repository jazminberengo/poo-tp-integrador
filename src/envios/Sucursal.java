package envios;

import catalogo.ItemCatalogo;

/*
 * Representa una sucursal física donde el cliente puede retirar su pedido.
 */
public class Sucursal {

    private final String nombre;
    private final Direccion direccion;

    public Sucursal(String nombre, Direccion direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public boolean tieneStock(ItemCatalogo item) {
    	// IMPLEMENTAR: Busqueda mediante Disponible de package busqueda 
    }

    public String getNombre()      { return nombre; }
    public Direccion getDireccion(){ return direccion; }
}
