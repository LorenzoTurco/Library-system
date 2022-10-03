package org.example;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    static Library library = new Library();

    public static List<Book> books;
    public static void main(String[] args) {

        User user1 = new User("Lorenzo", false);
        User user2 = new User("Ben", true);


        Book thisBook = new Book("10","How to Think Like Sherlock Holmes","Konnikova, Maria","nonfiction","psychology","Penguin"
        );
        csvToJson("data\\books_data.csv");
        //csvToJson2("data\\books_data.csv");

        library.addBooks(books);
        library.loanBook(user1, thisBook);
        library.addBook(thisBook);



    }
    public static void csvToJson(String path){

        File csvFile = new File(path);

        try (InputStream in = new FileInputStream(csvFile)) {

            CSV csv = new CSV(true, ',', in );
            List< String > fieldNames = null;

            if (csv.hasNext()) fieldNames = new ArrayList< >(csv.next());

            List <Map< String, String >> list = new ArrayList < > ();
            while (csv.hasNext()) {
                List < String > x = csv.next();
                Map < String, String > obj = new LinkedHashMap < > ();
                for (int i = 0; i < fieldNames.size(); i++) {
                    obj.put(fieldNames.get(i), x.get(i));
                }
                list.add(obj);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); //indent its contents

            mapper.writeValue(new File("data\\new_books_data.json"), list); //where to save output, json

            String json = mapper.writeValueAsString(list); //creating a json string


            // CREATING BOOK OBJECTS FROM THE JSON

            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //doesn't throw an error if property not found
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

            books = mapper.readValue(json, new TypeReference<>() {
            });



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}