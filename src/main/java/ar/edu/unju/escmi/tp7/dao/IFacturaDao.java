package ar.edu.unju.escmi.tp7.dao;

import ar.edu.unju.escmi.tp7.dominio.Factura;

public interface IFacturaDao {
	
	public void guardarFactura(Factura factura);
	public Factura buscarFactura(Long id);
	public void eliminarFactura(Factura factura);
	public void mostrarFacturas();
	public void mostrarFacturasFiltradas();
	
}
