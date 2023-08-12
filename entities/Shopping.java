package entities;

import java.util.List;

public class Shopping {
	private Integer codeOf;
	private String nameOf;
	private Double priceOf;
	private Integer quantityOf;
	
	
	public Shopping(Integer codeOf, String nameOf, Double priceOf, Integer quantityOf) {
		super();
		this.codeOf = codeOf;
		this.nameOf = nameOf;
		this.priceOf = priceOf;
		this.quantityOf = quantityOf;
	}

	public Integer getCodeOf() {
		return codeOf;
	}

	public void setCodeOf(Integer codeOf) {
		this.codeOf = codeOf;
	}

	public String getNameOf() {
		return nameOf;
	}

	public void setNameOf(String nameOf) {
		this.nameOf = nameOf;
	}

	public Double getPriceOf() {
		return priceOf;
	}

	public void setPriceOf(Double priceOf) {
		this.priceOf = priceOf;
	}

	public Integer getQuantityOf() {
		return quantityOf;
	}

	public void setQuantityOf(Integer quantityOf) {
		this.quantityOf = quantityOf;
	}

	public Double total(List<Shopping> shopping, Double price, Integer quantity) {
		Double total = 0.0;
		for (Shopping x : shopping) {
			total += x.priceOf * x.quantityOf;
		}
		return total;
	}

	@Override
	public String toString() {
		return String.format("Product code: %d | Producot name: %s "
				+ "| Price: U$ %.2f | Quantity: %d ", codeOf, nameOf, priceOf, quantityOf);
	}
}
