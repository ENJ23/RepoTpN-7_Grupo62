package ar.edu.unju.escmi.tp7.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tp7.config.EmfSingleton;
import ar.edu.unju.escmi.tp7.dao.IFacturaDao;
import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.Factura;

public class FacturaDaoImp implements IFacturaDao {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarFactura(Factura factura) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		
        try {
            transaction.begin();
            manager.persist(factura);
            transaction.commit();
            System.out.println("Factura creada exitosamente.");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error al crear la factura.");
            System.out.println("Error: " + e.getMessage());
        } finally {
            //scanner.close();
            //manager.close();
        }
    }
	

	@Override
	public Factura buscarFactura(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Factura.class, id);
	}

	@Override
	public void eliminarFactura(Factura factura) {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			
			transaction.begin();
			Factura facturaAEliminar = manager.find(Factura.class, factura.getId());
			facturaAEliminar.setEstado(false);
			System.out.println("Factura dada de baja exitosamente.");
			transaction.commit();
			
		}catch (Exception e){
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			
			System.out.println("No se pudo guardar el factura. Intentelo nuevamente más tarde.");
			System.out.println("Error: " + e.getMessage());
		}finally {
			//manager.close();
		}
	}

	@Override
	public void mostrarFacturas() {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
        try {
        	transaction.begin();
        	TypedQuery<Factura> query = manager.createQuery("SELECT f FROM Factura f", Factura.class);
        	List<Factura> facturas = query.getResultList();
        	if(facturas != null) {
        	for (Factura factura : facturas) {
        		Cliente cliente = factura.getCliente();
        		System.out.println("ID: " + factura.getId() + 
                        ", Fecha: " + factura.getFecha() + 
                        ", Cliente: " + (cliente != null ? cliente.getNombre() : "N/A") + 
                        " " + (cliente != null ? cliente.getApellido() : "N/A") + 
                        ", Domicilio: " + factura.getDomicilio() + 
                        ", Total: " + factura.getTotal() + 
                        ", Estado: " + (factura.isEstado() == true ? "Disponible" : "No Disponible"));
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

	@Override
	public void mostrarFacturasFiltradas() {
	    // Consulta para obtener facturas con estado verdadero y total mayor a 500.000
	    List<Factura> facturasFiltradas = manager.createQuery(
	            "SELECT f FROM Factura f WHERE f.estado = :estado AND f.total > :totalMinimo", Factura.class)
	            .setParameter("estado", true)
	            .setParameter("totalMinimo", 500000.0) // Establecer el valor mínimo del total
	            .getResultList();
	    
	    // Mostrar las facturas filtradas
	    for (Factura factura : facturasFiltradas) {
	        System.out.println(factura);
	    }
	}

	/*
	 * @Override public void realizarVenta(Factura factura) { // TODO Auto-generated
	 * method stub try { manager.getTransaction().begin(); manager.persist(factura);
	 * manager.getTransaction().commit();
	 * System.out.println("factura guardado con éxito.");
	 * 
	 * }catch(Exception e) {
	 * 
	 * if (manager.getTransaction() != null && manager.getTransaction().isActive())
	 * { manager.getTransaction().rollback(); }
	 * 
	 * System.out.
	 * println("No se pudo guardar el factura. Intentelo nuevamente más tarde.");
	 * System.out.println("Error: " + e.getMessage()); }finally { manager.close(); }
	 * }
	 */

	
}
