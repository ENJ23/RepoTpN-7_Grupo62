package ar.edu.unju.escmi.tp7.dao;

import ar.edu.unju.escmi.tp7.dominio.Cliente;

public interface IClienteDao {
	
	public Cliente ingresoDatos();
	public void guardarCliente(Cliente cliente);
	public void eliminarCliente(Cliente cliente);
	public void modificarCliente(Cliente cliente);
	public void mostrarClientes();
	public Cliente buscarCliente(long id);
}