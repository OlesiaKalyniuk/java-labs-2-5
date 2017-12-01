package packages;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException{
		ArrayList<Book> booktest = new ArrayList<Book>();
		booktest = Json.exportJSON("export.json");
		for(Book a : booktest)
			System.out.println(a.toString());
		Json.importJSON(booktest);
		
		booktest = Xml.exportXML("export.xml");
		for(Book a : booktest)
			System.out.println(a.toString());
		Xml.importXML(booktest);
	}
}
