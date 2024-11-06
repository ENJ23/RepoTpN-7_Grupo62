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
        System.out.print("Ingrese el DNI del cliente: ");
        dni = scanner.nextInt();
        scanner.nextLine();
        } while (dni > 99999999);
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
}