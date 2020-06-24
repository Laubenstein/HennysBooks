import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Library implements Serializable {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int i) {
        books.remove(i);
    }

    public Iterator<Book> iterator() {
        return books.iterator();
    }
}
