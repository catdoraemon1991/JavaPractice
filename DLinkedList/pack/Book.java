package pack;

public class Book {
	private String isbn;
	private int pages;
	private String title;
	public Book (String isbn, int pages, String title) {
		this.isbn = isbn;
		this.pages = pages;
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public int getPages() {
		return pages;
	}
	public String getTitle() {
		return title;
	}
}
