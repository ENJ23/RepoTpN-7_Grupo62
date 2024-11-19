package ar.edu.unju.escmi.tp7.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Producto;

public class Util {

	public static Scanner scanner = new Scanner(System.in);
	
	public Cliente crearCliente() {
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
        List<Factura> facturas = new ArrayList<>();
        return new Cliente(nombre, apellido, domicilio, dni, estado,facturas);
	}
	
	public Producto crearProducto() {
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

	        producto.setEstado(true);
	        return producto;
	        
			}catch(Exception e) {
				System.out.println("Ha ocurrido un error inesperado. Intentelo de nuevo mas tarde.");
				System.out.println("Error: " + e.getMessage());
				return null;
			}
		
	}
}
