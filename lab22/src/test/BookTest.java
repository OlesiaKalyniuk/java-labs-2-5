package test;

//import static org.testng.Assert.*;

import java.text.ParseException;


import org.testng.annotations.*;

import packages.*;

public class BookTest {
	private static Book book;

	@BeforeTest
	public void Init() throws ParseException {
		book = new Book();
	}

	@Test
	public void addAuthorAuthorValidThrowNothing() {
		book.addAuthor(new Author(1, "Paolo", "Koelo","1998-07-04", "USA", "Denver"));
	}

	@Test
	public void editAuthorAuthorValidThrowNothing() {
		book.editAuthor(new Author(1, "Paolo", "Koelo","1998-07-04", "USA", "Denver"));
	}

	@Test
	public void removeAuthorIdValidThrowNothing() {
		book.removeAuthor(1);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void removeAuthorIdNotValidThrowIllegalArgumentException() {
		book.removeAuthor(-1);
	}

	

}
