package org.code;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin extends User{

    public Admin(String username) {
        super(username);
    }

    public void displayEveryBookLoaned(){

        boolean flag = false;
        Map<String, List<Book>> loanedBooks = Library.getInstance().getLoanedBooks();

        for(Map.Entry<String, List<Book>> entry : loanedBooks.entrySet()){

            String user = entry.getKey();
            List<Book> books = entry.getValue();

            System.out.println("User: " + user);

            for(Book b : books){
                System.out.println("- " + b.getTitle());
            }
            System.out.println();

            flag=true;

        }

        if(!flag) System.out.println("No books currently on loan");

    }

    public void getnumberOfTimesBookHasBeenLoaned(String title){

        Map<String, List<Book>> books = Library.getInstance().getBooks();

        Map<String, List<Book>> loanedBooks = Library.getInstance().getLoanedBooks();


        List<Book> bookList = books.get(title); //list of books with this title


        int numberOfTimesLoaned = 0;

        if(bookList!=null){
            for (Book book : bookList) {
                numberOfTimesLoaned += book.getNumberOfTimesLoaned();
            }
        }


        //Also check through the copies of the same book that are currently loaned

        for(Map.Entry<String, List<Book>> entry : loanedBooks.entrySet()){
            List<Book> booksArr = entry.getValue();

            for(Book b : booksArr){

                if(b.getTitle().toLowerCase().equals(title)){
                    numberOfTimesLoaned += b.getNumberOfTimesLoaned();
                }
            }


        }
        System.out.println("n. of times " + title + " has been loaned: " + numberOfTimesLoaned);

    }

    @Override
    public void showMenu(){

        Scanner scanner = new Scanner(System.in);
        String option ="";

        while(option != "3") {


            System.out.println();
            System.out.println("1 - Display every book loaned");
            System.out.println("2 - Check for number of times a particular book has been loaned");
            System.out.println("3 - Exit");
            System.out.println();


            option = scanner.nextLine();

            switch (option) {

                case "1":
                    displayEveryBookLoaned();
                    break;
                case "2":
                    askForBookName();
                    break;
                case "3":
                    System.exit(0);
            }
        }

    }

    public void askForBookName(){

        System.out.println("Enter book title");

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine().toLowerCase();

        getnumberOfTimesBookHasBeenLoaned(title);


    }
}
