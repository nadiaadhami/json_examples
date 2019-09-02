package nadiatests;

/**
 http://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParseTest {

	private static final String filePath = "c:\\user2.json";

	public static void main(String[] args) {

		try {
			// read the json file
			FileReader reader = new FileReader(filePath);
            // to do - these parameters are not correct
			JSONParser jsonParser = new JSONParser(filePath, null, true);    //source, global, boolean
			JSONObject jsonObject = (JSONObject) jsonParser.parse();

			// get a String from the JSON object
			String firstName = (String) jsonObject.get("firstname");
			System.out.println("The first name is: " + firstName);

			// get a number from the JSON object
			long id =  (long) jsonObject.get("id");
			System.out.println("The id is: " + id);

			// get an array from the JSON object
			JSONArray lang= (JSONArray) jsonObject.get("languages");

			// take the elements of the json array
			for(int i=0; i<lang.length(); i++){
				System.out.println("The " + i + " element of the array: "+lang.get(i));
			}
//			Iterator i = lang.iterator();
//
//			// take each value from the json array separately
//			while (i.hasNext()) {
//				JSONObject innerObj = (JSONObject) i.next();
//				System.out.println("language "+ innerObj.get("lang") +
//						" with level " + innerObj.get("knowledge"));
//			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: " + structure.get("name"));

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
//		} catch (ParseException ex) {
//			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}

}