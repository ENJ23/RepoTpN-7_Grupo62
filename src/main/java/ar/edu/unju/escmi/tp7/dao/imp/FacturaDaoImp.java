package ar.edu.unju.escmi.tp7.dao.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tp7.config.EmfSingleton;
import ar.edu.unju.escmi.tp7.dao.IFacturaDao;
import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.DetalleFactura;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Producto;

public class FacturaDaoImp implements IFacturaDao {

	Scanner scanner = new Scanner(System.in);
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void realizarVenta() {
		// TODO Auto-generated method stub
		EntityTransaction transaction = manager.getTransaction();
		ProductoDaoImp productoService = new ProductoDaoImp();
        try {
            transaction.begin();

            // Ingresar datos de la factura
            Factura factura = new Factura();

            //Fecha actual para la factura
            factura.setFecha(LocalDate.now());

            // Ingresar cliente
            System.out.print("Ingrese el ID del cliente: ");
            Long clienteId = Long.parseLong(scanner.nextLine());
            Cliente cliente = manager.find(Cliente.class, clienteId);
            if (cliente == null) {
                System.out.println("Cliente no encontrado. La factura no se puede crear.");
                return;
            }
            factura.setCliente(cliente);

            //Domicilio del cliente para la factura
            factura.setDomicilio(cliente.getDomicilio());


            // Ingresar detalles de la factura
            List<DetalleFactura> detalles = new ArrayList<>();
            String continuar = "s";
            do {
                DetalleFactura detalle = new DetalleFactura();
                // Detalles de la factura
                System.out.print("Ingrese el ID del producto: ");
                Long productoId = Long.parseLong(scanner.nextLine());
                Producto producto = productoService.buscarProducto(productoId);
                if (producto == null) {
                    System.out.println("Producto no encontrado. No se puede agregar al detalle.");
                    
                    System.out.println("Si desea salir al menu principal, ingrese 's'.");
                    if (scanner.nextLine().equals("s")) {
                    	return;
                    }
                    continue;
                }
                detalle.setProducto(producto);

                System.out.print("Ingrese la cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                detalle.setCantidad(cantidad);

                detalles.add(detalle);
                detalle.setFactura(factura);
                cliente.getFacturas().add(factura);
                System.out.print("¿Desea agregar otro detalle? (s/n): ");
                continuar = scanner.nextLine();
            } while (continuar.equalsIgnoreCase("s"));
            
            double total = 0.0;
            for (DetalleFactura detalle : detalles) {
                total += detalle.getProducto().getPrecioUnitario() * detalle.getCantidad();
            }
            factura.setTotal(total);
            factura.setDetalles(detalles);
            
            factura.setEstado(true);
            
            manager.persist(factura);
            transaction.commit();
            System.out.println("Factura creada exitosamente.");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al crear la factura.");
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
                        ", Estado: " + factura.isEstado());
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
