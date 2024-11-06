package ar.edu.unju.escmi.tp7.dominio;

public class DetalleFactura {
    private Long id; // Autoincremental
    private Producto producto;
    private int cantidad;
    private double subtotal; // Representa cantidad * precioUnitario

    // Constructor
    public DetalleFactura(Long id, Producto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = cantidad * producto.getPrecioUnitario(); // Calcular subtotal
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.subtotal = cantidad * producto.getPrecioUnitario(); // Recalcular subtotal
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = cantidad * producto.getPrecioUnitario(); // Recalcular subtotal
    }

    public double getSubtotal() {
        return subtotal;
    }
}