package pack;

public class TestDLinkedList {
	public static void main(String[] args) throws Exception {
		System.out.println("Start testing");
		DLinkedList bookList = new DLinkedList();
		bookList.printNextList();
		
		Book book1= new Book("abcde",120,"book1");
		Book book2= new Book("12345",220,"book2");
		Book book3= new Book("4fefef",320,"book3");
		Book book4= new Book("34r43efc",110,"book4");
		Book book5= new Book("9j9ij",170,"book5");
		Book book6= new Book("34r43efc",190,"book6");
		
		bookList.addFirst(book1);
		bookList.addFirst(book2);
		bookList.addFirst(book3);
		bookList.addFirst(book4);
		bookList.addFirst(book6);
		bookList.addLast(book5);
		bookList.addLast(book5);
		bookList.printPrevList();
		System.out.println("");
		System.out.println(bookList.returnMaxCostBook().getTitle());
		System.out.println(bookList.findBook("12345").getTitle());
		
		Book bookD1 = bookList.deleteFirst();
		bookList.printNextList();
		System.out.println("");
		
		Book bookD2 = bookList.deleteLast();
		bookList.printNextList();
		System.out.println("");
		
		bookList.deleteAll();
		bookList.printNextList();
		System.out.println("");
		
		Book bookD3 = bookList.deleteLast();
	}
}
