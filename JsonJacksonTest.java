package nadiatests;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
/** http://www.mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/ */
public class JsonJacksonTest {
    public static class User {
          String name;
    }
    public static void main(String[] args) {
        try {
//            1.1 Convert Java object to JSON, writeValue(...)
            ObjectMapper objectMapper = new ObjectMapper();
            User user = new User();
            user.name = "John";
            //Object to JSON in file
            objectMapper.writeValue(new File("/Users/nadhami/user.json"), user);

            //Object to JSON in String
            String jsonInString = objectMapper.writeValueAsString(user);
            System.out.println("jsonInString="+jsonInString);

//            1.2 Convert JSON to Java object, readValue(...)
            ObjectMapper objectMapper1 = new ObjectMapper();
            String jsonInString2 = "{'name' : 'mkyong'}";

            //JSON from file to Object
            User user2 = objectMapper1.readValue(new File("/Users/nadhami/user.json"), User.class);
            System.out.println("user2="+user2);

            //JSON from String to Object
            User user3 = objectMapper1.readValue(jsonInString2, User.class);
            System.out.println("user3="+user3);

        }   catch (Exception e) {
            e.printStackTrace();
        }
    }
}
