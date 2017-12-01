package packages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {
	public static ArrayList<Book> exportXML(String path) throws ParserConfigurationException, IOException, SAXException {
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = (Document) builder.parse(file);
		NodeList nodeList = document.getElementsByTagName("Book");
		ArrayList<Book> books = new ArrayList<Book>();
		for(int i = 0; i < nodeList.getLength(); i ++) {
			Node node = (Node) nodeList.item(i);
			if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;
                Book book = new Book();
                		book.setName(element.getElementsByTagName("name").item(0).getTextContent());
                		book.setId(Integer.valueOf(element.getElementsByTagName("id").item(0).getTextContent()));
                		book.setCountPages(Integer.valueOf(element.getElementsByTagName("countPages").item(0).getTextContent()));
                		book.setPublisher(element.getElementsByTagName("publisher").item(0).getTextContent());

                		String str = element.getElementsByTagName("authorList").item(0).getTextContent().trim();
                		String[] string = str.split("\n");
                		Author author = new Author(Integer.valueOf(string[0]), string[1], string[2], string[3], string[4], string[5]);
                		ArrayList<Author> authors = new ArrayList<Author>();
                		authors.add(author);
                		book.setAuthor(authors);
                
                books.add(book);
            }
		}
		return books;
	}
	
	
	public static void importXML(ArrayList<Book> books) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
        try {
        	Document doc = builder.newDocument();
        	Element rootElement = doc.createElement("AllBooks");
        	doc.appendChild(rootElement);
        	for(int i = 0; i < books.size(); i ++) {
        		Element bookNode = doc.createElement("Book");
        		Element idNode = doc.createElement("id");    
        		idNode.appendChild(doc.createTextNode(String.valueOf(books.get(i).getId())));
        		Element nameNode = doc.createElement("name"); 
        		nameNode.appendChild(doc.createTextNode(books.get(i).getName()));
        		Element countPagesNode = doc.createElement("countPages");
        		countPagesNode.appendChild(doc.createTextNode(String.valueOf(books.get(i).getCountPages())));
        		Element publisherNode = doc.createElement("publisher");
        		publisherNode.appendChild(doc.createTextNode(books.get(i).getPublisher()));
        		Element authorListNode = doc.createElement("authorList");
        			Element authorElement = doc.createElement("Author");
        				Element authorIdNode = doc.createElement("id");
        				authorIdNode.appendChild(doc.createTextNode(String.valueOf(books.get(i).getAuthor().get(0).getId())));
        				Element authorNameNode = doc.createElement("name");
        				authorNameNode.appendChild(doc.createTextNode(books.get(i).getAuthor().get(0).getName()));
        				Element authorSurnameNode = doc.createElement("surname");
        				authorSurnameNode.appendChild(doc.createTextNode(books.get(i).getAuthor().get(0).getSurname()));
        				Element authorDateOfBirthdayNode = doc.createElement("dateOfBirthday");
        				authorDateOfBirthdayNode.appendChild(doc.createTextNode(books.get(i).getAuthor().get(0).getDateOfBirthday()));
        				Element authorCountryNode = doc.createElement("Country");
        				authorCountryNode.appendChild(doc.createTextNode(books.get(i).getAuthor().get(0).getCountry()));
        				Element authorCityNode = doc.createElement("City");
        				authorCityNode.appendChild(doc.createTextNode(books.get(i).getAuthor().get(0).getCity()));
        				
        				authorElement.appendChild(authorIdNode);
        				authorElement.appendChild(authorNameNode);
        				authorElement.appendChild(authorSurnameNode);
        				authorElement.appendChild(authorDateOfBirthdayNode);
        				authorElement.appendChild(authorCountryNode);
        				authorElement.appendChild(authorCityNode);
        		authorListNode.appendChild(authorElement);
        	
        		bookNode.appendChild(idNode);
        		bookNode.appendChild(nameNode);
        		bookNode.appendChild(countPagesNode);
        		bookNode.appendChild(publisherNode);
        		bookNode.appendChild(authorListNode);
        		
        		rootElement.appendChild(bookNode);
        	} 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
 
           // StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("import.xml"));
 
            //transformer.transform(source, console);
            transformer.transform(source, file);
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
	

}
