package test;

import static org.testng.Assert.assertNotEquals;

import java.text.ParseException;
import java.util.ArrayList;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import packages.*;

public class LibraryTest {
	private static Library library;

	@BeforeTest
	public void Init() throws ParseException {
		library = new Library();
	}

	@Test
	public void addBookBookValidThrowNothing() {
		ArrayList<Author> obj = new ArrayList<Author>();
		obj.add(new Author(2, "Paolo", "Koelo", "1998-07-04", "USA", "Denver"));
		library.addBook(new Book(1, "Book1", 320, "Ukraine",obj));
	}

	@Test
	public void editBookBookValidThrowNothing() {
		ArrayList<Author> obj = new ArrayList<Author>();
		obj.add(new Author(2, "Paolo", "Koelo","1998-07-04", "USA", "Denver"));
		library.editBook(new Book(1, "Book1", 320, "Ukraine", obj));
	}


	@Test
	public void removeBookIdValidThrowNothing() {
		library.removeBook(3);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void removeBookIdNotValidThrowIllegalArgumentException() {
		library.removeBook(-1);
	}

	@Test
	public void getBookByAthourBookFoundReturnNotEmptyListSizeNotEqualsExpected() {
		assertNotEquals(library.getBooksByAthour("Author").size(), 2);
	}


	@Test
	public void getBookByPublisherBooksFoundReturnNotEmptyListSizeNotEqualsExpected() {
		assertNotEquals(library.getBooksByPublisher("USA").size(), 4);
	}
}
