package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;
import java.util.List;

public class Factura {
    private Long id; // Autoincremental
    private LocalDate fecha; // Fecha actual
    private Cliente cliente;
    private String domicilio; // Domicilio del cliente
    private double total; // Total de todos los productos vendidos
    private boolean estado;
    private List<DetalleFactura> detalles;
	public Factura(Long id, LocalDate fecha, Cliente cliente, String domicilio, double total, boolean estado,
			List<DetalleFactura> detalles) {
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.domicilio = domicilio;
		this.total = total;
		this.estado = estado;
		this.detalles = detalles;
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
    
    