package ar.edu.unju.escmi.tp7.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombre de la columna en la base de datos
    private Long id; // Autoincremental

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false) // Clave foránea que referencia a Producto
    private Producto producto;

    @Column(name = "cantidad", nullable = false) // Nombre de la columna en la base de datos
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false) // Clave foránea que referencia a Factura
    private Factura factura;

    @Transient // Este campo no se persiste en la base de datos
    private double subtotal;
	    
	    public DetalleFactura() {
	    	
	    }

		public DetalleFactura(Long id, Producto producto, int cantidad, double subtotal) {
			super();
			this.id = id;
			this.producto = producto;
			this.cantidad = cantidad;
			this.subtotal = subtotal;
		}

		
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
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public double getSubtotal() {
			return subtotal;
		}

		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}

		public Factura getFactura() {
			return factura;
		}

		public void setFactura(Factura factura) {
			this.factura = factura;
		}

		@Override
		public String toString() {
			return "DetalleFactura [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + ", factura="
					+ factura + ", subtotal=" + subtotal + "]";
		}
	    
		
	    
}