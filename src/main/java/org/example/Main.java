package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        csvToJson("data\\books_data.csv");

    }
    public static void csvToJson(String path){

        File csvFile = new File(path);

        try (InputStream in = new FileInputStream(csvFile);) {

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
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(System.out, list);

            System.out.println(mapper);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}