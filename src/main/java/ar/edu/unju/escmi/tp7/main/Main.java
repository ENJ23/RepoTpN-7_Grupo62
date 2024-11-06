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
	    

}
