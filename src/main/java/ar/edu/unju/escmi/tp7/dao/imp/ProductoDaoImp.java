package ar.edu.unju.escmi.tp7.dao.imp;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tp7.config.EmfSingleton;
import ar.edu.unju.escmi.tp7.dao.IProductoDao;
import ar.edu.unju.escmi.tp7.dominio.Producto;

public class ProductoDaoImp implements IProductoDao{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	private static Scanner scanner = new Scanner(System.in);
	
	@Override
	public Producto ingresoDatos() {
		
		try {
		Producto producto = new Producto();

        System.out.print("Ingrese la descripción del producto: ");
        producto.setDescripcion(scanner.nextLine());

        System.out.print("Ingrese el precio unitario del producto: ");
        
        while (true) {
            try {
                producto.setPrecioUnitario(Double.parseDouble(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un valor numérico válido para el precio: ");
            }
        }

		/*
		 * System.out.
		 * print("Ingrese el estado del producto (true para activo, false para inactivo): "
		 * ); while (true) { try {
		 * producto.setEstado(Boolean.parseBoolean(scanner.nextLine())); break; } catch
		 * (Exception e) { System.out.print("Por favor, ingrese 'true' o 'false': "); }
		 * }
		 */
        producto.setEstado(true);
        return producto;
        
		}catch(Exception e) {
			System.out.println("Ha ocurrido un error inesperado. Intentelo de nuevo mas tarde.");
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}

	@Override
	public void altaProducto(Producto producto) {
		// TODO Auto-generated method stub
		try {
			manager.getTransaction().begin();
			manager.persist(producto);
			manager.getTransaction().commit();
			System.out.println("Producto guardado con éxito.");
			
		}catch(Exception e) {
			
			if (manager.getTransaction() != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			
			System.out.println("No se pudo guardar el Producto. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
			//manager.close();
		}
	}

	@Override
	public void eliminarProducto(Producto producto) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Producto productoAEliminar = manager.find(Producto.class, producto.getId());
			productoAEliminar.setEstado(false);
			transaction.commit();
			System.out.println("Producto dado de baja.");
		}catch (Exception e){
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el producto. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
            System.out.println("---------");

			//manager.close();
		}
	}

	@Override
	public Producto buscarProducto(Long idProducto) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
        Producto producto = null;

        try {
            transaction.begin();
            
            producto = manager.find(Producto.class, idProducto);
            
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); 
            }
            System.out.println("No se pudo buscar el producto. Error: " + e.getMessage());
        } finally {
            System.out.println("---------");
        }

        return producto;
	}

	@Override
	public void modificarPrecio(Producto producto, double precioNuevo) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		try {
			
			transaction.begin();
			Producto productoAModificar = manager.find(Producto.class, producto.getId());
			productoAModificar.setPrecioUnitario(precioNuevo);
			
			transaction.commit();
		}catch (Exception e){
			
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el producto. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
            System.out.println("---------");

			//manager.close();
		}
	}

	@Override
	public void mostrarProductos() {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
        try {
        	transaction.begin();
        	TypedQuery<Producto> query = manager.createQuery("SELECT p FROM Producto p", Producto.class);
        	List<Producto> productos = query.getResultList();
        	if(productos != null) {
	        	for (Producto producto : productos) {
	        		System.out.println(producto);
	        		}
        	}else {
        		System.out.println("No hay facturas registrados.");
        		transaction.rollback();
        	}
        	transaction.commit();
        }catch(Exception e) {
        	if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo encontrar el cliente asociado. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
        }finally {
        	//manager.close();
        }
	}

	
	
}
