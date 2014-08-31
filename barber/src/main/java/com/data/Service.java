package com.data;

public class Service {

	private Long id;

	private String title;

	private int price;

	public Service() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", title=" + title + ", price=" + price
				+ "]";
	}

}
