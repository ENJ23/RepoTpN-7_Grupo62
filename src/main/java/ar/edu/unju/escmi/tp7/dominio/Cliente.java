package ar.edu.unju.escmi.tp7.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id") // Nombre de la columna en la base de datos
    private Long id;

    @Column(name = "cliente_nombre") // Nombre de la columna en la base de datos
    private String nombre;

    @Column(name = "cliente_apellido") // Nombre de la columna en la base de datos
    private String apellido;

    @Column(name = "cliente_domicilio") // Nombre de la columna en la base de datos
    private String domicilio;

    @Column(name = "cliente_dni") // Nombre de la columna en la base de datos
    private int dni;

    @Column(name = "cliente_estado") // Nombre de la columna en la base de datos
    private boolean estado;
    
    @OneToMany(mappedBy="cliente")
    private List<Factura> facturas;
    
    public Cliente() {
    	
    }


	public Cliente(String nombre, String apellido, String domicilio, int dni, boolean estado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.dni = dni;
		this.estado = estado;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + domicilio
				+ ", dni=" + dni + ", estado=" + estado + "]";
	}
    
    
}