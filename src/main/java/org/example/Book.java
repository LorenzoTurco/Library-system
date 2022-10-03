package org.example;

public class Book {

    private String Number;
    private String Title;
    private String Author;
    private String Genre;
    private String SubGenre;
    private String Publisher;
    private boolean loaned;

    public Book(String Number, String Title, String Author, String Genre, String SubGenre, String Publisher){
        this.Number = Number;
        this.Title = Title;
        this.Author = Author;
        this.Genre = Genre;
        this.SubGenre = SubGenre;
        this.Publisher = Publisher;
        this.loaned = false;
    }

    public Book(){}


    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getSubGenre() {
        return SubGenre;
    }

    public void setSubGenre(String SubGenre) {
        this.SubGenre = SubGenre;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        Publisher = Publisher;
    }

    public boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }


    @Override public String toString()
    {
        return "Book [Number=" + Number + ", Title=" + Title
                + ", Author=" + Author
                + ", Genre=" + Genre + "SubGenre=" + SubGenre + ", Publisher="+ Publisher+ "]";
    }
}