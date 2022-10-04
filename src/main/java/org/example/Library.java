package org.example;
import java.util.*;

public class Library {

    final private static Utility utilityFunctions = new Utility();
    private static Map<String, List<Book>> books = new HashMap<>(); //title - book
    private static Map<String,List<Book>> loanedBooks = new HashMap<>(); //username - book

    private static Library library; //Singleton instantiation of a library

    public static Library getInstance() { //Create library if it doesn't already exist
        if (library == null) {
            library = new Library();
        }
        return library;
    }

    public void addBook(Book newBook){ //add single book

        List<Book> newBooks;

        if(books.containsKey(newBook.getTitle())){
            newBooks = books.get(newBook.getTitle());
        }else{
            newBooks= new ArrayList<>();
        }
        newBooks.add(newBook);
        books.put(newBook.getTitle(), newBooks);
        utilityFunctions.updateBookListJson(books);

    }

    public void addBooks(List<Book> booksToBeAdded){ //add multiple books

        for(int i = 0; i<booksToBeAdded.size(); i++){

            List<Book> newBooks;

            if(books.containsKey(booksToBeAdded.get(i).getTitle())){
                newBooks = books.get(booksToBeAdded.get(i).getTitle());
            }else{
                newBooks= new ArrayList<>();
            }
            newBooks.add(booksToBeAdded.get(i));
            books.put(booksToBeAdded.get(i).getTitle(), newBooks);
        }
        utilityFunctions.updateBookListJson(books);

    }

    public void removeBook(Book bookToBeRemoved){

        List<Book> newBooks;

        if(!books.containsKey(bookToBeRemoved.getTitle())){
            System.out.println("Book not in the library");
            return;
        }

        newBooks = books.get(bookToBeRemoved.getTitle());

        if(newBooks.isEmpty()){
            System.out.println("The book is currently loaned out.\nWait for it to come back before removing it.");
            return;
        }

        if(newBooks.size() == 1){
            books.remove(bookToBeRemoved.getTitle());
            System.out.println("Book removed successfully.");
            return;
        }


        newBooks.remove(0);
        books.put(bookToBeRemoved.getTitle(), newBooks);

        System.out.println("Book removed successfully.");

        utilityFunctions.updateBookListJson(books);
    }

    public void loanBook(User user, String title){

        if(!books.containsKey(title)){
            System.out.println("Book not in the library");
            return;
        }

        List<Book> relevantBooks = books.get(title); //get books with that title

        //MOVE THIS BOOK TO USER LOANED BOOK LIST

        //add book to list of loaned books from user

        List<Book> arr;
        if(!loanedBooks.containsKey(user.getUsername())){ //if first time user loaning a book create new user record
            arr = new ArrayList<>();

        }else{

            arr = loanedBooks.get(user.getUsername());
        }
        relevantBooks.get(0).increaseNumberOfTimesLoaned();
        arr.add(relevantBooks.get(0));
        loanedBooks.put(user.getUsername(), arr);

        removeBook(relevantBooks.get(0));//remove 1 instance of the books from the available books

        user.setBooksLoaned(loanedBooks.get(user.getUsername()));

        utilityFunctions.updateUserListJson(loanedBooks);
        utilityFunctions.updateBookListJson(books);
    }

    public void addNewUser(User user){

        if(loanedBooks.containsKey(user.getUsername())){
            System.out.println("User already exists");
            return;
        }

        loanedBooks.put(user.getUsername(), new ArrayList<>());
        System.out.println("User added successfully");
    }

    public void giveBookBack(User user, String book){

        if(!loanedBooks.containsKey(user.getUsername())){
            System.out.println("Username invalid.");
            return;
        }

        //REMOVE BOOK FROM USER LOANED BOOKS
        //ADD BOOK BACK TO AVAILABLE BOOK LIST

        List<Book> userLoanedBooks = loanedBooks.get(user.getUsername()); //get every loaned books of the user in question

        if(!books.containsKey(book)){
            books.put(book, new ArrayList<>());
        }

        List<Book> availableBooks = books.get(book); //get all the available copies of that book


        for(int i = 0; i<userLoanedBooks.size(); i++){
            if(userLoanedBooks.get(i).getTitle().equals(book)){

                availableBooks.add((userLoanedBooks.get(i)));
                userLoanedBooks.remove(userLoanedBooks.get(i));

                break;
            }
        }


        books.put(book, availableBooks);
        loanedBooks.put(user.getUsername(), userLoanedBooks);

        utilityFunctions.updateUserListJson(loanedBooks);
        utilityFunctions.updateBookListJson(books);

    }

    public void displayBooks(){
        System.out.println(books.toString());
    }

    public Map<String,List<Book>> getLoanedBooks(){
        return loanedBooks;
    }

    public Map<String, List<Book>> getBooks() {
        return books;
    }
}
