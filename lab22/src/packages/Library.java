package packages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Library {
	
	private int id;
	private String name;
	private String situation;
	private List<Book> books;
	private static ArrayList<Book> booksList;
	
	public Library() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setSetBooks(List<Book> newList) {
		this.books = newList;
	}
	
	public Library(int id, String name, String situation, List<Book> book) {
		this.id =id;
		this.name = name;
		this.situation = situation;
		this.books = book;
	}
	@Override
	public boolean equals(Object object) {
		if ((object instanceof Library) && (((Library) object).getName() == this.name)
				&& ((((Library) object).getSituation() == this.situation))
				&& ((((Library) object).getBooks() == this.books))) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void validateId(int id) {
		if (id < 1)
			throw new IllegalArgumentException("id must be a positive integer number");
	}
	
	
	public void addBook(Book newBook) {
		booksList = new ArrayList<Book>();
		booksList.add(newBook);
	}

	public void editBook(Book book) {
		Book bookEdit = null;
		for (Book a : booksList) {
			if (a.getId() == book.getId()) {
				bookEdit = a;
				break;
			}
	
		}
		bookEdit.setName(book.getName());
		bookEdit.setCountPages(book.getCountPages());
		bookEdit.setPublisher(book.getPublisher());
		bookEdit.setAuthor(book.getAuthor());
	}




	public List<Book> getBooksByAthour(String surname) {
		books = new ArrayList<Book>();
		for (Book b : books) {
			if (b.getSurname() == surname) {
				books.add(b);
			}
		}
		return books;
	}

	
	public List<Book> getBooksByPublisher(String publisher) {
		books = new ArrayList<Book>();
		for (Book b : books) {
			if (b.getPublisher() == publisher) {
				books.add(b);
			}
		}
		return books;
	}

	public void removeBook(int idBook) {
	    ArrayList<Book> newBookList = new ArrayList<Book>();
		validateId(idBook);
		newBookList = books.stream().filter((p)->p.getId()==idBook).collect(Collectors.toCollection(ArrayList::new));
		books = newBookList;
	}

}
