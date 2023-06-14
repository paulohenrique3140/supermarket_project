package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Locale;

import entities.Product;
import entities.Shopping;
import entities.Client;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);

		List<Product> product = new ArrayList<>();
		List<Client> client = new ArrayList<>();
		List<Shopping> shopping = new ArrayList<>();
		int choice, secondChoice, thirdChoice, fourthChoice;
		do {
			System.out.println("\n\n######## WELCOME TO SUPERMARKET #######");
			System.out.print("\n[1] LOJISTA\n" + "[2] CLIENTE\n" + "[0] ENCERRAR O PROGRAMA\n");
			System.out.print("\nESCOLHA UMA OPCAO: ");
			choice = input.nextInt();
			while (choice < 0 || choice > 2) {
				System.out.print("\nOPCAO INVALIDA. DIGITE NOVAMENTE: ");
				choice = input.nextInt();
			}
			switch (choice) {
			case 1:
				do {
					System.out.println("\n=============== MENU LOJISTA ===============");
					System.out.print("[1] CADASTRAR NOVO PRODUTO\n" + "[2] CADASTRAR NOVO CLIENTE\n"
							+ "[3] MOSTRAR TODOS OS PRODUTOS\n" + "[4] MOSTRAR TODOS OS CLIENTES\n"
							+ "[5] MOSTRAR PRODUTOS POR DEPARTAMENTO\n" + "[6] MOSTRAR PRODUTOS POR NOME\n"
							+ "[7] ATUALIZAR ESTOQUE\n" + "[8] ATUALIZAR PRECO\n" + "[9] REMOVER PRODUTO\n"
							+ "[0] VOLTAR AO MENU INICIAL\n");
					System.out.print("\n\nESCOLHA A SUA OPCAO: ");
					secondChoice = input.nextInt();
					while (secondChoice < 0 || secondChoice > 9) {
						System.out.print("\nOPCAO INVALIDA! TENTE NOVAMENTE: ");
						secondChoice = input.nextInt();
					}
					switch (secondChoice) {
					case 1: // REGISTRAR NOVO PRODUTO
						System.out.print("PRODUCT CODE: ");
						Integer productCode = input.nextInt();
						while (hasId(product, productCode)) {
							System.out.print("PRODUCT CODE ALREADY TAKEN. TRY AGAIN: ");
							productCode = input.nextInt();
						}
						input.nextLine();
						System.out.print("PRODUCT NAME: ");
						String productName = input.nextLine();
						System.out.print("DEPARTMENT: ");
						String department = input.nextLine();
						System.out.print("PRICE: U$ ");
						Double price = input.nextDouble();
						System.out.print("QUANTITY IN STOCK: ");
						Integer stock = input.nextInt();
						product.add(new Product(productCode, productName.toUpperCase(), department.toUpperCase(), price,
								stock));
						break;

					case 2: // REGISTRAR NOVO CLIENTE
						Integer id = client.size() + 1;
						input.nextLine();
						System.out.print("CLIENT NAME: ");
						String name = input.nextLine();
						System.out.print("ACCOUNT BALANCE: U$ ");
						Double balance = input.nextDouble();
						client.add(new Client(id, name.toUpperCase(), balance));
						break;

					case 3: // MOSTRAR LISTA TODOS OS PRODUTOS
						System.out.println("\n### LISTA DE PRODUTOS ###");
						showList(product, client, 1);
						break;

					case 4: // MOSTRAR LISTA TODOS OS CLIENTES
						System.out.println("\n### LISTA DE CLIENTES ###");
						showList(product, client, 2);
						break;

					case 5: // MOSTAR PRODUTOS POR DEPARTAMENTO
						input.nextLine();
						System.out.print("\nDIGITE O DEPARTAMENTO: ");
						String resDepartment = input.nextLine();
						System.out.println("\n### PRODUTOS POR DEPARTAMENTO ###");
						showByDepartment(product, resDepartment.toUpperCase());
						break;

					case 6: // MOSTRAR PRODUTO POR NOME
						input.nextLine();
						System.out.print("\nDIGITE O NOME DO PRODUTO: ");
						String resProductName = input.nextLine();
						System.out.println("\n### PRODUTOS POR NOME ###");
						showByName(product, resProductName.toUpperCase());
						break;

					case 7: // ATUALIZAR ESTOQUE
						System.out.print(
								"\n[1] ADICIONAR PRODUTO(S) NO ESTOQUE\n" + "\n[2] REMOVER PRODUTO(S) DO ESTOQUE\n");
						System.out.print("\nESCOLHA SUA OPCAO: ");
						int atualizaEstoque = input.nextInt();
						while (atualizaEstoque != 1 && atualizaEstoque != 2) {
							System.out.print("\nOPCAO INVALIDA. TENTE NOVAMENTE: ");
							atualizaEstoque = input.nextInt();
						}
						System.out.print("\nDIGITE O CODIGO DO PRODUTO: ");
						int atualizaProductCode = input.nextInt();
						while (!hasId(product, atualizaProductCode)) {
							System.out.print("\nCODIGO NAO ENCONTRADO. TENTE NOVAMENTE: ");
							atualizaProductCode = input.nextInt();
						}
						if (atualizaEstoque == 1) {
							System.out.print("\nDIGITE A QUANTIDADE A SER ADICIONADA: ");
							int quantityAdd = input.nextInt();
							addStock(product, atualizaProductCode, quantityAdd);
						} else {
							System.out.print("\nDIGITE A QUANTIDADE A SER REMOVIDA: ");
							int quantityRm = input.nextInt();
							rmStock(product, atualizaProductCode, quantityRm);
						}
						break;

					case 8: // ALTERAR PRECO DO PRODUTO
						System.out.print("\nDIGITE O CODIGO DO PRODUTO: ");
						atualizaProductCode = input.nextInt();
						while (!hasId(product, atualizaProductCode)) {
							System.out.print("\nCODIGO NAO ENCONTRADO. TENTE NOVAMENTE: ");
							atualizaProductCode = input.nextInt();
						}
						showById(product, atualizaProductCode);
						System.out.print("\nDITIE O NOVO PRECO: U$");
						Double newPrice = input.nextDouble();
						updatePrice(product, atualizaProductCode, newPrice);
						break;

					case 9: // REMOVER PRODUTO
						System.out.print("\nDIGITE O CODIGO DO PRODUTO A SER REMOVIDO: ");
						Integer productCodeRm = input.nextInt();
						showById(product, productCodeRm);
						System.out.print("\nTEM CERTEZA QUE DESEJA REMOVER ESTE PRODUTO? [S/N]: ");
						char sure = input.next().charAt(0);
						if (sure == 'S' || sure == 's') {
							product.removeIf(x -> x.getProductCode() == productCodeRm);
							System.out.println("\nPRODUTO REMOVIDO!");
							for (Product x : product) {
								if (x.getProductCode() > productCodeRm) {
									x.setProductCode(x.getProductCode() - 1);
								}
							}
							System.out.println("### LISTA DE PRODUTOS ATUALIZADA ###");
							showList(product, client, 1);
						} else {
							System.out.println("NADA FEITO.");
						}
						break;
					default:
						break;
					}
				} while (secondChoice != 0);
				break;

			case 2:
				do {
					System.out.println("\n=============== MENU CLIENTE ===============");
					System.out.print(
							"[1] BUSCAR PRODUTO PELO NOME\n" + "[2] MOSTRAR DEPARTAMENTOS\n" + "[3] COMPRAR PRODUTOS\n"
									+ "[4] SALDO\n" + "[5] DEPOSITAR\n" + "[0] VOLTAR AO MENU INICIAL\n");
					System.out.print("\n\nESCOLHA A SUA OPCAO: ");
					thirdChoice = input.nextInt();
					while (thirdChoice < 0 || thirdChoice > 5) {
						System.out.print("\nOPCAO INVALIDA. DIGITE NOVAMENTE: ");
						thirdChoice = input.nextInt();
					}
					switch (thirdChoice) {
					case 1: // BUSCAR PELO NOME DO PRODUTO
						input.nextLine();
						System.out.print("\nDIGITE O NOME DO PRODUTO: ");
						String resProductName = input.nextLine();
						System.out.println("\n### PRODUTOS POR NOME ###");
						showByName(product, resProductName.toUpperCase());
						break;
					case 2: // MOSTRAR DEPARTAMENTOS
						input.nextLine();
						System.out.print("\nDIGITE O DEPARTAMENTO: ");
						String resDepartment = input.nextLine();
						System.out.println("\n### PRODUTOS POR DEPARTAMENTO ###");
						showByDepartment(product, resDepartment.toUpperCase());
						break;
					case 3: // COMPRAR PRODUTO
						System.out.print("DIGITE SEU ID: ");
						int clientId = input.nextInt();
						showClientById(client, clientId);
						do {
							System.out.print("\n### ABA DE COMPRAS ###");
							System.out.print("\n[1] INSERIR ITENS NO CARRINTO\n[2] REMOVER ITENS DO CARRINHO\n"
									+ "[3] VER CARRINHO\n" + "[4] FINALIZAR COMPRA\n[0] VOLTAR AO MENU ANTERIOR\n");
							fourthChoice = input.nextInt();
							while (fourthChoice < 0 || fourthChoice > 4) {
								System.out.print("OPCAO INVALIDA. TENTE NOVAMENTE: ");
								fourthChoice = input.nextInt();
							}
							switch (fourthChoice) {
							case 1: // INSERIR ITEM
								String answer = "S";
								do {
									input.nextLine();
									System.out.print("DIGITE O NOME DO PRODUTO: ");
									String resName = input.nextLine();
									showByName(product, resName);
									System.out.print("DIGITE O CODIGO DO PRODUTO: ");
									Integer codeOf = input.nextInt();
									while (!hasId(product, codeOf)) {
										System.out.print("\nCODIGO NAO ENCONTRADO. TENTE NOVAMENTE: ");
										codeOf = input.nextInt();
									}
									input.nextLine();
									double priceOf = 0.00;
									for (Product x : product) {
										if (x.getProductCode() == codeOf) {
											priceOf = x.getPrice();
										}
									}
									System.out.print("DIGITE A QUANTIDADE: ");
									Integer quantityOf = input.nextInt();
									shopping.add(new Shopping(codeOf, resName, priceOf, quantityOf));
									System.out.print("\nDESEJA CONTINUAR COMPRANDO? [S/N]: ");
									answer = input.nextLine().toUpperCase();
								} while (answer.equals("S"));
								break;
							case 2: // REMOVER ITEM
								String answerTwo = "S";
								do {
									input.nextLine();
									System.out.print("DIGITE O NOME DO PRODUTO");
									String resName = input.nextLine();
									showByName(product, resName);
									System.out.print("Digite o codigo do produto: ");
									Integer codeOf = input.nextInt();
									showById(product, codeOf);
									System.out.print("\nTEM CERTEZA QUE DESEJA REMOVER ESTE ITEM? [S/N]: ");
									char sure = input.next().charAt(0);
									input.nextLine();
									if (sure == 'S' || sure == 's') {
										shopping.removeIf(x -> x.getCodeOf() == codeOf);
										System.out.println("\nPRODUTO REMOVIDO!");
										System.out.println("\n### CARRINHO ATUALIZADO ###");
										showShopping(shopping);
									} else {
										System.out.println("NADA FEITO.");
									}
									System.out.print("DESEJA REMOVER OUTRO PRODUTO? [S/N]");
									answerTwo = input.nextLine().toUpperCase();
								} while (answerTwo.equals("S"));
								break;
							case 3: // MOSTRAR CARRINHO
								showShopping(shopping);
								break;
							case 4: // FINALIZAR A COMPRA

								Double total = 0.0;
								for (Shopping x : shopping) {
									total = x.total(shopping, x.getPriceOf(), x.getQuantityOf());
								}
								System.out.println("\n$$$ CHECK-LIST $$$");
								showShopping(shopping);
								System.out.printf("TOTAL À PAGAR: U$ %.2f%n", total);
								System.out.print(
										"\n[1] FINALIZAR COMPRA\n[2] LIMPAR CARRINHO\n[3] CONTINUAR COMPRANDO: ");
								System.out.print("\nDIGITE SUA OPCAO: ");
								int finish = input.nextInt();
								while (finish < 1 || finish > 3) {
									System.out.print("OPCAO INVALIDA. TENTE NOVAMENTE: ");
									finish = input.nextInt();
								}
								if (finish == 1) {
									checkBalance(client, product, shopping, total, clientId);
									shopping.removeAll(shopping);
								} else if (finish == 2) {
									shopping.removeAll(shopping);
									System.out.println("SEU CARRINHO ESTA LIMPO!");
								} else {
									fourthChoice = 1;
								}
								break;
							default: 
								break;
							}
						} while (fourthChoice != 0);
						break;
					
					case 4:
						System.out.print("DIGITE O SEU ID DE CLIENTE: ");
						clientId = input.nextInt();
						for (Client x : client) {
							if (x.getId() == clientId) {
								System.out.printf("\nSALDO: U$%.2f", x.getBalance());
							}
						}
						break;
					
					case 5:
						System.out.print("DIGITE O SEU ID DE CLIENTE: ");
						clientId = input.nextInt();
						showClientById(client, clientId);
						System.out.print("\nDIGITE O VALOR DE DEPOSITO: U$ ");
						double deposit = input.nextDouble();
						for (Client x : client) {
							if (x.getId() == clientId) {
								x.deposit(deposit);
								System.out.printf("\nNOVO SALDO: U$ %.2f", x.getBalance());
							}
						}
					}
				} while (thirdChoice != 0);

			}
		} while (choice != 0);
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
		List<Product> result = product.stream().filter(x -> x.getDepartment().equals(department))
				.collect(Collectors.toList());
		for (Product x : result) {
			System.out.println(x);
		}
	}

	public static void showByName(List<Product> product, String name) {
		List<Product> result = product.stream().filter(x -> x.getProductName().equals(name))
				.collect(Collectors.toList());
		System.out.println(result);
	}

	public static void showById(List<Product> product, Integer productCode) {
		List<Product> result = product.stream().filter(x -> x.getProductCode() == productCode)
				.collect(Collectors.toList());
		for (Product x : result) {
			System.out.println(x);
		}
	}

	public static void showClientById(List<Client> client, Integer clientId) {
		List<Client> result = client.stream().filter(x -> x.getId() == clientId).collect(Collectors.toList());
		for (Client x : result) {
			System.out.println(x);
		}
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

	public static void showShopping(List<Shopping> shopping) {
		for (Shopping x : shopping) {
			System.out.println(x);
		}
	}

	public static void checkBalance(List<Client> client, List<Product> product, List<Shopping> shopping, Double total,
			int clientId) {
		for (Client x : client) {
			if (x.getId() == clientId) {
				if (x.getBalance() < total) {
					System.out.print("SALDO INSUFICIENTE!");
				} else {
					x.buy(total);
					for (Shopping y : shopping) {
						for (Product z : product) {
							if (y.getCodeOf() == z.getProductCode()) {
								rmStock(product, y.getCodeOf(), y.getQuantityOf());
							}
						}
					}
					System.out.print("COMPRA FINALIZADA COM SUCESSO!");
				}
			}
		}
	}

}
