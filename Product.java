

public class Product{
	
	public String id;
	public String name;
	public String description;
	public int price;
	public int stock;

	public void newProduct(String id, String name, String description, int price, int stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}
	
	public String toString(){
		return "ID: "+id+" Nombre: "+name+" Precio: " +price+" Stock:"+ stock;
	}
}