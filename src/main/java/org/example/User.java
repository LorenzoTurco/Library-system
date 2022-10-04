package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String username;

    private List<Book> booksLoaned = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooksLoaned() {
        return booksLoaned;
    }
    public void setBooksLoaned(List<Book> booksLoaned) {
        this.booksLoaned = booksLoaned;
    }

    public void displayBooksLoaned() {
        System.out.println("Books loaned:");
        for(Book book: booksLoaned){
            System.out.println("- " + book.getTitle());
        }
    }

    public void showMenu(){

        Scanner scanner = new Scanner(System.in);
        String option ="";

        while(option != "3") {

            System.out.println();
            System.out.println("1 - Display your books loaned");
            System.out.println("2 - Loan a new book");
            System.out.println("3 - Bring book back");
            System.out.println("4 - Exit");


            option = scanner.nextLine();

            switch (option) {

                case "1":
                    displayBooksLoaned();
                    break;
                case "2":
                    loanNewBook();
                    break;
                case "3":
                    giveBookBack();
                    break;
                case "4":
                    System.exit(0);
                    break;
            }
        }

    }

    public void loanNewBook(){
        System.out.println("Enter the title of the book you wish to loan");

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        Library.getInstance().loanBook(this, title);
    }

    public void giveBookBack(){
        System.out.println("Enter the title of the book you wish to bring back");

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        Library.getInstance().giveBookBack(this, title);
    }
}
