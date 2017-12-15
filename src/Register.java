
public class Register {

	int item, code, quantity;
	String description, unit;

	public Register(int item, int code, String description, int quantity, String unit) {
		this.item = item;
		this.code = code;
		this.description = description;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	public Register(int code, String description, int quantity, String unit) {
		this.code = code;
		this.description = description;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Item: " + getItem() + " | Código: " + getCode() + " | Descrição: " + getDescription() + " | Quantidade: "
				+ getQuantity() + " | Unidade: " + getUnit();
	}
}
