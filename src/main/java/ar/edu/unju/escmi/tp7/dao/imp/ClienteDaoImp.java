package ar.edu.unju.escmi.tp7.dao.imp;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tp7.config.EmfSingleton;
import ar.edu.unju.escmi.tp7.dao.IClienteDao;
import ar.edu.unju.escmi.tp7.dominio.Cliente;

public class ClienteDaoImp implements IClienteDao {

	Scanner scanner = new Scanner(System.in);
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public Cliente ingresoDatos() {
		int dni = 0;
		
		System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el domicilio del cliente: ");
        String domicilio = scanner.nextLine();

        do {
        	try {
        System.out.print("Ingrese el DNI del cliente: ( Utilizar el siguiente formato: '99999999') ");
        dni = scanner.nextInt();
        scanner.nextLine();
        	}catch(Exception e){
        		System.out.println("Error al ingresar el DNI. Intentelo de nuevo");
        		dni = 999999999;
        		scanner.nextLine();
        	}
        } while (dni > 99999999 || dni < 10000000);
        boolean estado = true; 

        return new Cliente(nombre, apellido, domicilio, dni, estado);
	}

	@Override
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
			System.out.println("Cliente guardado con éxito.");
			
		}catch(Exception e) {
			
			if (manager.getTransaction() != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			
			System.out.println("No se pudo guardar el cliente. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
			//manager.close();
		}
	}
	
	@Override
	public void eliminarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Cliente clienteAEliminar = manager.find(Cliente.class, cliente.getId());
			clienteAEliminar.setEstado(false);
			transaction.commit();
		}catch (Exception e){
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el cliente. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
			//manager.close();
		}
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Cliente clienteAModificar = manager.find(Cliente.class, cliente.getId());

			if (clienteAModificar != null) {	
				
				Cliente datosActualizados = ingresoDatos();
				clienteAModificar.setNombre(datosActualizados.getNombre());
	            clienteAModificar.setApellido(datosActualizados.getApellido());
	            clienteAModificar.setDomicilio(datosActualizados.getDomicilio());
	            clienteAModificar.setDni(datosActualizados.getDni());
	            
			transaction.commit();
			
			}else {
				System.out.println("Cliente no encontrado");
				transaction.rollback();
			}
		}catch (Exception e){
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el cliente. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
			//manager.close();
		}
	}

	@Override
	public void mostrarClientes() {
		// TODO Auto-generated method stub
        EntityTransaction transaction = manager.getTransaction();
        try {
        	transaction.begin();
        	TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        	List<Cliente> clientes = query.getResultList();
        	if(clientes != null) {
        	for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() +
                                   ", Apellido: " + cliente.getApellido() +
                                   ", Domicilio: " + cliente.getDomicilio() +
                                   ", DNI: " + cliente.getDni() +
                                   ", Estado: " + cliente.isEstado());
            	}
        	}else {
        		System.out.println("No hay clientes registrados.");
        		transaction.rollback();
        	}
        	transaction.commit();
        }catch(Exception e) {
        	if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el cliente. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
        }finally {
        	//manager.close();
        }
	}

	@Override
	public Cliente buscarCliente(long id) {
			
			EntityTransaction transaction = manager.getTransaction();
	        Cliente cliente = null;

	        try {
	            transaction.begin();
	            
	            cliente = manager.find(Cliente.class, id);
	            
	            transaction.commit(); 
	        } catch (Exception e) {
	            if (transaction != null && transaction.isActive()) {
	                transaction.rollback(); 
	            }
	            System.out.println("No se pudo buscar el cliente. Error: " + e.getMessage());
	        } finally {
	           // manager.close(); 
	        }

	        return cliente;
	    }
}