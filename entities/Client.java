package entities;

public class Client {
	private Integer id;
	private String name;
	private Double balance;

	public Client() {

	}

	public Client(Integer id, String name, Double balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return String.format("Client id: %d | Name: %s | Balance: U$ %.2f", id, name, balance);
	}

	

}