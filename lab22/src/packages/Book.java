package packages;


import java.util.stream.Collectors;


import java.util.ArrayList;



public class Book {

	private int id;
	private String name;
	private int countPages;
	private String publisher;
	private ArrayList<Author> authorList;

	public String getSurname() {
		return getSurname();
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

	public int getCountPages() {
		return countPages;
	}

	public void setCountPages(int countPages) {
		this.countPages = countPages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public ArrayList<Author> getAuthor() {
		return authorList;
	}

	public void setAuthor(ArrayList<Author> newList) {
		this.authorList = newList;
	}
	
	public Book(int id, String name, int countPages, String publisher) {
		this.id = id;
		this.name = name;
		this.countPages = countPages;
		this.publisher = publisher;
		this.authorList = new ArrayList<Author>();
	}
	public Book(int id, String name, int countPages, String publisher, ArrayList<Author> newauthors) {
		this.id = id;
		this.name = name;
		this.countPages = countPages;
		this.publisher = publisher;
		this.authorList = newauthors;
	}

	public Book() {
		
	}


	@Override
	public String toString() {
		return id + " " + name + " " + countPages + " " + publisher + " " + authorList;
	}

	@Override
	public boolean equals(Object object) {
		if ((object instanceof Book) && ((((Book) object).getId()) == this.id)
				&& (((Book) object).getName() == this.name) && ((((Book) object).getCountPages() == this.countPages))
				&& ((((Book) object).getPublisher() == this.publisher))) {
			return true;
		} else {
			return false;
		}
	}

	private static void validateId(int id) {
		if(id<1) {
			throw new RuntimeException("id not valid");
		}
	}
	
	public void addAuthor(Author newAuthor) {
		authorList = new ArrayList<Author>();
		authorList.add(newAuthor);
	}

	public void editAuthor(Author author) {
		Author authorEdit = null;
		for (Author a : authorList) {
			if (a.getId() == author.getId()) {
				authorEdit = a;
				break;
			}
		}
		authorEdit.setName(author.getName());
		authorEdit.setSurname(author.getSurname());
		authorEdit.setDateOfBirthday(author.getDateOfBirthday());
		authorEdit.setCountry(author.getCountry());
		authorEdit.setCity(author.getCity());
	}
	
	
	public void removeAuthor(int idAuthor) {
	    ArrayList<Author> newAuthorList = new ArrayList<Author>();
		validateId(idAuthor);
		newAuthorList = authorList.stream().filter((p)->p.getId()==idAuthor).collect(Collectors.toCollection(ArrayList::new));
		authorList = newAuthorList;
	}


}


