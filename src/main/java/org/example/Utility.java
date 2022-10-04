package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Utility {

    public void updateUserListJson(Map<String,List<Book>>loanedBooks){

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("data\\user_data.json"), loanedBooks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateBookListJson(Map<String, List<Book>> books){

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("data\\books_data.json"), books);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
