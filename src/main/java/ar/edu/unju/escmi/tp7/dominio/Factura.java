package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "facturas")
public class Factura {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fac") // Nombre de la columna en la base de datos
    private Long id; // Autoincremental

    @Column(name = "fecha_fac", nullable = false) // Nombre de la columna en la base de datos
    private LocalDate fecha; // Fecha actual

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false) // Clave foránea que referencia a Cliente
    private Cliente cliente;

    @Column(name = "domicilio_fac") // Nombre de la columna en la base de datos
    private String domicilio; // Domicilio del cliente

    @Column(name = "total_fac", nullable = false) // Nombre de la columna en la base de datos
    private double total; // Total de todos los productos vendidos

    @Column(name = "estado_fac") // Nombre de la columna en la base de datos
    private boolean estado;

    
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles; // Lista de detalles de la factura
    
    public Factura() {
    	
    }
    
	public Factura(LocalDate fecha, Cliente cliente, String domicilio, double total, boolean estado,
			List<DetalleFactura> detalles) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.domicilio = domicilio;
		this.total = total;
		this.estado = estado;
		this.detalles = detalles;
	}
	
	@Override
		public String toString() {
			return "Factura [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", domicilio=" + domicilio
					+ ", total=" + total + ", estado=" + estado + "]";
		}
	
	public void mostrarDetallesFactura() {
        if (detalles == null || detalles.isEmpty()) {
            System.out.println("No hay detalles para esta factura.");
            return;
        }

        System.out.println("Detalles de la Factura ID: " + id);
        for (DetalleFactura detalle : detalles) {
            System.out.println(detalle); // Asumiendo que DetalleFactura tiene un método toString() bien definido
        }
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

    
}