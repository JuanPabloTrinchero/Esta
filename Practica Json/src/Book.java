public class Book {
    private String ISBN;
    private String author;

    public Book(String ISBN, String author) {
        this.ISBN = ISBN;
        this.author = author;
    }

    public Book() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", author=" + author + "]";
    }

}