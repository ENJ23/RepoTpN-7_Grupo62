package ar.edu.unju.escmi.tp7.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id") // Nombre de la columna en la base de datos
	    private Long id; // Autoincremental
	
	    @Column(name = "descripcion", nullable = false) // Nombre de la columna en la base de datos
	    private String descripcion;
	
	    @Column(name = "precio_unitario", nullable = false) // Nombre de la columna en la base de datos
	    private double precioUnitario;
	
	    @Column(name = "estado") // Nombre de la columna en la base de datos
	    private boolean estado;

	    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<DetalleFactura> detalles; // Lista de detalles de factura asociados al producto

	    public Producto() {
	    	
	    }

		public Producto(String descripcion, double precioUnitario, boolean estado) {
			super();
			this.descripcion = descripcion;
			this.precioUnitario = precioUnitario;
			this.estado = estado;
		}
		
		

		@Override
		public String toString() {
			return "Producto [id=" + id + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario
					+ ", estado=" + estado + "]";
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public double getPrecioUnitario() {
			return precioUnitario;
		}

		public void setPrecioUnitario(double precioUnitario) {
			this.precioUnitario = precioUnitario;
		}

		public boolean isEstado() {
			return estado;
		}

		public void setEstado(boolean estado) {
			this.estado = estado;
		}
	    
	    
}