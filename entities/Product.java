package entities;

public class Product {
	private Integer productCode;
	private String productName;
	private String department;
	private Double price;
	private Integer stock;

	public Product() {

	}

	public Product(Integer productCode, String productName, String department, Double price, Integer stock) {
		this.productCode = productCode;
		this.productName = productName;
		this.department = department;
		this.price = price;
		this.stock = stock;
	}

	public Integer getProductCode() {
		return productCode;
	}
	
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return String.format("Product code: %d | Product name: %s | Department: %s | Pruce: U$ %.2f | Stock: %d",
				productCode, productName, department, price, stock);
	}

	public void addStock(Integer quantity) {
		stock += quantity;
	}

	public void rmStock(Integer quantity) {
		stock -= quantity;
	}
	
	public void updatePrice(Double price) {
		this.price = price;
	}
	
	

}