package ar.edu.unju.escmi.tp7.dao;

import ar.edu.unju.escmi.tp7.dominio.Producto;

public interface IProductoDao {
	
	public Producto ingresoDatos();
	public void altaProducto(Producto producto);
	public void eliminarProducto(Producto producto);
	public Producto buscarProducto(Long id);
	public void modificarPrecio(Producto producto, double precioNuevo);
	public void mostrarProductos();
}