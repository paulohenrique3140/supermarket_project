package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Locale;

import entities.Product;
import entities.Client;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);

		List<Product> product = new ArrayList<>();
		List<Client> client = new ArrayList<>();

		product.add(new Product(1, "Sucrilhos", "Cereais", 37.90, 30));
		product.add(new Product(2, "Amaciante", "Produtos de Limpeza", 27.90, 15));
		client.add(new Client(1, "Paulo Henrique de Souza", 450.00));
		client.add(new Client(2, "Sara Araujo", 690.00));
		
		// REGISTRAR NOVO PRODUTO
		System.out.print("Product code: ");
		Integer productCode = input.nextInt();
		while (hasId(product, productCode)) {
			System.out.print("Product code already taken. Try again: ");
			productCode = input.nextInt();
		}
		input.nextLine();
		System.out.print("Product name: ");
		String productName = input.nextLine();
		System.out.print("Department: ");
		String department = input.nextLine();
		System.out.print("Price: U$ ");
		Double price = input.nextDouble();
		System.out.print("Quantity in stock: ");
		Integer stock = input.nextInt();
		product.add(new Product(productCode, productName, department, price, stock));
		
		// REGISTRAR NOVO CLIENTE
		Integer id = client.size() + 1;
		input.nextLine();
		System.out.print("Client name: ");
		String name = input.nextLine();
		System.out.print("Account balance: U$ ");
		Double balance = input.nextDouble();
		client.add(new Client(id, name, balance));
		
		//MOSTRAR LISTA TODOS OS PRODUTOS
		System.out.println("\nLISTA DE PRODUTOS");
		showList(product, client, 1);
		//MOSTRAR LISTA TODOS OS CLIENTE
		System.out.println("\nLISTA DE CLIENTES");
		showList(product, client, 2);
		//MOSTAR PRODUTOS POR DEPARTAMENTO
		System.out.println("\nPRODUTOS POR DEPARTAMENTO");
		showByDepartment(product, "Cereais");
		//MOSTRAR PRODUTO POR NOME
		System.out.println("\nPRODUTOS POR NOME");
		showByName(product, "Amaciante");
		
		//ADD STOCK
		addStock(product, 1, 6);
		//RM STOCK
		rmStock(product, 2, 16);
		
		//UPDATE PRICE
		updatePrice(product, 1, 42.50);
		
		//REMOVER PRODUTO PELO PRODUCT CODE
		Integer productCodeRm = 3;
		product.removeIf(x -> x.getProductCode() == productCodeRm);
				
		showList(product, client, 1);
				
		input.close();
	}

	public static void showList(List<Product> product, List<Client> client, Integer option) {
		if (option == 1) {
			for (Product x : product) {
				System.out.println(x);
			}
		} else {
			for (Client x : client) {
				System.out.println(x);
			}
		}
	}
	
	public static void showByDepartment(List<Product> product, String department) {
		List<Product> result = product.stream().filter(x -> x.getDepartment() == department).collect(Collectors.toList());
		for (Product x : result) {
			System.out.println(x);
		}
	}
	
	public static void showByName(List<Product> product, String name) {
		List<Product> result = product.stream().filter(x -> x.getProductName() == name).collect(Collectors.toList());
		System.out.println(result);
	}
	
	public static void addStock(List<Product> product, Integer id, Integer quantity) {
		for (Product x : product) {
			if (id == x.getProductCode()) {
				x.addStock(quantity);
				System.out.printf("\nProduct name: %s%nUpdated stock: %d\n", x.getProductName(), x.getStock());
			}
		}
	}
	
	public static void rmStock(List<Product> product, Integer id, Integer quantity) {
		for (Product x : product) {
			if (id == x.getProductCode()) {
				if (quantity <= x.getStock()) {
					x.rmStock(quantity);
				} else {
					System.out.printf("\nInsuficient quantity in stock!");
				}
				System.out.printf("\nProduct name: %s%nUpdated stock: %d\n\n", x.getProductName(), x.getStock());
			}
			
		}
	}
	
	public static boolean hasId(List<Product> product, Integer productCode) {
		Product list = product.stream().filter(x -> x.getProductCode() == productCode).findFirst().orElse(null);
		return list != null;
	}
	
	public static void updatePrice(List<Product> product, Integer id, Double price) {
		for (Product x : product) {
			if (id == x.getProductCode()) {
				x.updatePrice(price);
				System.out.printf("\nProduct name: %s%nUpdatd price: %.2f\n\n", x.getProductName(), x.getPrice());
			}
		}
	}
}
