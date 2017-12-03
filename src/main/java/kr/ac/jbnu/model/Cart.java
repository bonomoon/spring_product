package kr.ac.jbnu.model;

public class Cart {
	private int cart_id;
	private String cart_user;
	private int cart_item;
	
	public Cart(String cart_user, int cart_item) {
		this.cart_user = cart_user;
		this.cart_item = cart_item;
	}
	
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getCart_user() {
		return cart_user;
	}
	public void setCart_user(String cart_user) {
		this.cart_user = cart_user;
	}
	public int getCart_item() {
		return cart_item;
	}
	public void setCart_item(int cart_item) {
		this.cart_item = cart_item;
	}
}
