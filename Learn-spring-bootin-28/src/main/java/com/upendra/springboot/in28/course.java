package com.upendra.springboot.in28;

public class course {

	private int id;
	private String name;
	private String author;

	public course(int id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return "course [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

}
