package ar.edu.unju.escmi.tp7.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp7.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.tp7.dao.imp.FacturaDaoImp;
import ar.edu.unju.escmi.tp7.dao.imp.ProductoDaoImp;
import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Producto;


public class Main  {

    private static Scanner scanner = new Scanner(System.in);
    private static ClienteDaoImp clienteService = new ClienteDaoImp();
    private static ProductoDaoImp productoService = new ProductoDaoImp();
    private static FacturaDaoImp facturaService = new FacturaDaoImp();

    public static void main(String[] args) {
        int opcion;

        do {
        	try {
        		
	            System.out.println("Menú de Opciones:");
	            System.out.println("1 - Alta de cliente");
	            System.out.println("2 - Alta de producto");
	            System.out.println("3 - Realizar la venta de productos (Alta de una nueva factura)");
	            System.out.println("4 - Buscar una factura ingresando su número de factura");
	            System.out.println("5 - Eliminar una factura (eliminación lógica)");
	            System.out.println("7 - Modificar datos de cliente");
	            System.out.println("8 - Modificar precio de producto");
	            System.out.println("9 - Eliminar producto (eliminación lógica)");
	            System.out.println("10 - Mostrar todas las facturas");
	            System.out.println("11 - Mostrar todos los clientes");
	            System.out.println("12 - Mostrar las facturas que superen el total de $500.000");
	            System.out.println("0 - Salir");
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine();
            
        	}catch(Exception e) {
        		opcion = 999;
        		scanner.nextLine();
        	}
            switch (opcion) {
                case 1:
                	
                	Cliente cliente = clienteService.ingresoDatos();
                	clienteService.guardarCliente(cliente);
                    break;
                    
                case 2:
                	Producto producto = productoService.ingresoDatos();
                    productoService.altaProducto(producto);
                    break;
                case 3:
                    facturaService.realizarVenta();
                    break;
                case 4:
                	
                	System.out.println("Ingrese el id de la factura buscada: ");
                	long idFactura = 0;
                	while (true) {
                        try {
                            idFactura = Long.parseLong(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Por favor, ingrese un valor numérico válido: ");
                        }
                	 }
                    Factura facturaBuscada = facturaService.buscarFactura(idFactura);
                    if (facturaBuscada != null) {
                    //System.out.println("Aquí está la factura buscada: \n" + facturaBuscada);
                    System.out.println("ID: " + facturaBuscada.getId() + 
                            "\nFecha: " + facturaBuscada.getFecha() + 
                            "\nCliente: " + (facturaBuscada.getCliente() != null ? facturaBuscada.getCliente().getNombre() : "N/A") + 
                            " " + (facturaBuscada.getCliente() != null ? facturaBuscada.getCliente().getApellido() : "N/A") + 
                            "\nDomicilio: " + facturaBuscada.getDomicilio() + 
                            "\nTotal: " + facturaBuscada.getTotal() + 
                            "\nEstado: " + facturaBuscada.isEstado());
                    System.out.println("Detalles: ");
                    facturaBuscada.mostrarDetallesFactura();
                    }else {
                    	System.out.println("Factura no encontrada");
                    }
                    break;
                    
                case 5:
                	long idFacturaEliminar = 0;
                	System.out.println("Ingrese el id de la factura que busca eliminar: ");
                	while (true) {
                        try {
                            idFacturaEliminar = Long.parseLong(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Por favor, ingrese un valor numérico válido para el precio: ");
                        }
                    }
                	Factura facturaEliminar = facturaService.buscarFactura(idFacturaEliminar);
                    facturaService.eliminarFactura(facturaEliminar);
                    break;
                case 6:
                	System.out.println("Nada que hacer aqui...");
                    break;
                case 7:
                	try {
                		int id = 0;
	                	System.out.println("Ingrese el id del cliente buscado: ");
	                	id = scanner.nextInt();
	                	scanner.nextLine();
	                	if (id != 0) {
	                	Cliente clienteBuscado = clienteService.buscarCliente(id);
	                    clienteService.modificarCliente(clienteBuscado);
	                	}else {
	                		System.out.println("ID no encontrado.");
	                	}
                	}catch(Exception e) {
                		System.out.println("Ha ocurrido un error. Intentelo de nuevo.");
                	}
                    break;
                case 8:
                	try {
	                	System.out.println("Ingrese el id del producto a modificar: ");
	                	Long idProducto = scanner.nextLong();
	                	scanner.nextLine();
	                	
	                	Producto productoAModificar = productoService.buscarProducto(idProducto);
	                	System.out.println("El precio actual de ese producto es de: " + productoAModificar.getPrecioUnitario());
	                	System.out.println("Ingrese el nuevo precio del producto: ");
	                	double precioNuevo = scanner.nextDouble();
	                	scanner.nextLine();
	                	
	                	productoService.modificarPrecio(productoAModificar, precioNuevo);
	                	
	                	}catch(InputMismatchException e) {
		                		System.out.println("Ingrese un numero por favor.\nError: " + e.getMessage());
	                	}catch(Exception e) {
	                		System.out.println("Ha ocurrido un error: " + e.getMessage() + "\nIntentelo de nuevo.");
	                		opcion = 999;
	    	        		scanner.nextLine();
	                	}
                    break;
                case 9:
                	try {
	                	System.out.println("Ingrese el id del producto a eliminar: ");
	                	Long idProducto = scanner.nextLong();
	                	scanner.nextLine();
	                	Producto productoAEliminar = productoService.buscarProducto(idProducto);
	                	productoService.eliminarProducto(productoAEliminar);
	                	}catch(InputMismatchException e) {
	                		System.out.println("Ingrese un numero por favor.\nError: " + e.getMessage());
            			}catch(Exception e) {
	                		System.out.println("Ha ocurrido un error: " + e.getMessage() + "\nIntentelo de nuevo.");
	                		opcion = 999;
	    	        		scanner.nextLine();
	                	}
                   // eliminarProductoLogico();
                    break;
                case 10:
                    facturaService.mostrarFacturas();
                    break;
                case 11:
                    clienteService.mostrarClientes();
                    break;
                case 12:
	                   facturaService.mostrarFacturasFiltradas();
	                    break;
	                case 0:
	                    System.out.println("Saliendo...");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        } while (opcion != 0);
	    }
}
	    

}
