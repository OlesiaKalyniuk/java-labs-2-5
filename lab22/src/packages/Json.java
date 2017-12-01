package packages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Json {
	
	public static ArrayList<Book> exportJSON(String path) throws FileNotFoundException {
		Type bookType = new TypeToken<ArrayList<Book>>() {}.getType();
		Json json = new Json();
		JsonReader reader = new JsonReader(new FileReader(path));
		Type collectionType = new TypeToken<ArrayList<Book>>(){}.getType();
		ArrayList<Book> lcs = (ArrayList<Book>) new Gson()
		               .fromJson( reader , collectionType);
		return lcs;
	}
	
	public static void importJSON(ArrayList<Book> books) throws IOException {		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(books);
		FileWriter fileWriter = new FileWriter("import.json");
        fileWriter.write(jsonString);
        fileWriter.close();
	}
	

}

