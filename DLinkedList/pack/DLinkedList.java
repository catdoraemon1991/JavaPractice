package pack;
public class DLinkedList{

	private class Node{
		private Book data;
		private Node next;
		private Node prev;
		public Node(Book data){
			this.data = data;
		}
		public Node getNext(){
			return next;
		}
		public Node getPrev() {
			return prev;
		}
		public Book getData() {
			return data;
		}
		public void setNext(Node next){
			this.next =  next;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}
	
	private Node front = null;
	private Node rear = null ;
	private int length = 0;
	
	public Book findBook(String isbn) {
		if (front == null) return null;
		Node cur = front;
		for (int i = 0; i < length; i++) {
			if (cur.getData().getIsbn().equals(isbn)) return cur.getData();
			cur = cur.getNext();
		}
		return null;
	}
	
	public void addFirst(Book data) {
		if (findBook(data.getIsbn()) != null) return;
		Node node = new Node(data);
		if (front == null) {
			front = node;
			rear = node;
		}else {
			node.setNext(front);
			front.setPrev(node);
			front = node;
		}
		length += 1;
	}
	public void addLast(Book data) {
		if (findBook(data.getIsbn()) != null) return;
		Node node = new Node(data);
		if (front == null) {
			front = node;
			rear = node;
		}else {
			node.setPrev(rear);
			rear.setNext(node);
			rear = node;
		}
		length += 1;
	}
	public Book deleteFirst() throws Exception {
		if (front == null) {
			throw new Exception("The list is empty"); 
		}
		Node node = front;
		if (front == rear) {
			front = null;
			rear = null;
		}else {
			front = front.getNext();
			front.setPrev(null);
		}
		length -= 1;
		return node.getData();
	}
	public Book deleteLast() throws Exception{
		if (front == null) {
			throw new Exception("The list is empty"); 
		}
		Node node = rear;
		if (front == rear) {
			front = null;
			rear = null;
		}else {
			rear = rear.getPrev();
			rear.setNext(null);
		}
		length -= 1;
		return node.getData();		
	}
	public void deleteAll() throws Exception {
		while(length > 0) {
			Book data = deleteLast();
		}
	}
	public int size() {
		return length;
	}
	public void printNextList() {
		Node cur = front;
		while (cur != null) {
			Book data = cur.getData();
			System.out.println("ISBN: " + data.getIsbn() + ", pages: " + data.getPages() + ", title: " + data.getTitle());
			cur = cur.getNext();
		}
	}
	public void printPrevList() {
		Node cur = rear;
		while (cur != null) {
			Book data = cur.getData();
			System.out.println("ISBN: " + data.getIsbn() + ", pages: " + data.getPages() + ", title: " + data.getTitle());
			cur = cur.getPrev();
		}
	}
	public Book returnMaxCostBook() {
		if (front == null) return null;
		int maxPage = front.getData().getPages();
		Node cur = front;
		Book maxBook = front.getData();
		while (cur != null) {
			if(cur.getData().getPages() > maxPage) {
				maxPage = cur.getData().getPages();
				maxBook = cur.getData();
			}
			cur = cur.getNext();
		}
		return maxBook;
	}
}
