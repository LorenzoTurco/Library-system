package org.example;

import java.util.*;

public class Library {

    private Map<String, Set<Book>> books = new HashMap<>(); //title - book
    private Map<String,List<Book>> loanedBooks = new HashMap<>(); //username - book

    private static Library library;    //Singleton instantiation of a library

    public static Library getInstance() { //Create library if it doesn't already exist
        if (library == null) {
            library = new Library();
        }
        return library;
    }

    public void addBook(Book newBook){

        Set<Book> newBooks;

        if(books.containsKey(newBook.getTitle())){
            newBooks = books.get(newBook.getTitle());
        }else{
            newBooks= new HashSet<>();
        }
        newBooks.add(newBook);
        books.put(newBook.getTitle(), newBooks);

        System.out.println(books.toString());

    }

    public void addBooks(List<Book> booksToBeAdded){

        for(int i = 0; i<booksToBeAdded.size(); i++){

            Set<Book> newBooks;

            if(books.containsKey(booksToBeAdded.get(i).getTitle())){
                newBooks = books.get(booksToBeAdded.get(i).getTitle());
            }else{
                newBooks= new HashSet<>();
            }
            newBooks.add(booksToBeAdded.get(i));
            books.put(booksToBeAdded.get(i).getTitle(), newBooks);
        }

            System.out.println(books.toString());

    }

    public void removeBook(Book bookToBeRemoved){

        Set<Book> newBooks;

        if(!books.containsKey(bookToBeRemoved.getTitle())){
            System.out.println("Book not found");
            return;
        }

        newBooks = books.get(bookToBeRemoved.getTitle());
        newBooks.remove(bookToBeRemoved);

        books.put(bookToBeRemoved.getTitle(), newBooks);
        System.out.println(books.toString());

    }

    public void loanBook(User user, Book bookToBeLoaned){

        if(!books.containsKey(bookToBeLoaned.getTitle())){
            System.out.println("Book not available");
            return;
        }

        Set<Book> relevantBooks = books.get(bookToBeLoaned.getTitle()); //get books with that title
        Book book = relevantBooks.iterator().next(); //get 1 instance of the books

        removeBook(book);


        //add book to list of loaned books fromm user

        List<Book> userBooks = loanedBooks.get(user.getUsername());
        userBooks.add(bookToBeLoaned);

        loanedBooks.put(user.getUsername(), userBooks);
    }

    public void addNewUser(User user){

        if(loanedBooks.containsKey(user.getUsername())){
            System.out.println("User already exists");
            return;
        }

        loanedBooks.put(user.getUsername(), new ArrayList<>());
        System.out.println("User added successfully");
    }


}
