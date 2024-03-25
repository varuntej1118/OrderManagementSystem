package com.model;


public class Clothing extends Product {
    private String size;
    private String color;

    // Constructors
    public Clothing() {
    }

    public Clothing(int productId, String productName, String description, double price, int quantityInStock, String type, String size, String color) {
        super(productId, productName, description, price, quantityInStock, type);
        this.setSize(size);
        this.setColor(color);
    }
    // Getters and Setters

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
  
}
