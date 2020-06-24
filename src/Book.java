import java.io.Serializable;

class Book implements Serializable {
    private String title;
    private String author;
    private int pages;


    public Book(String author, String title, int pages){
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    void print() {System.out.println("Autor: " + getAuthor()
            + " | Titel: " + getTitle()
            + " | Seiten: " + getPages());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
    
    
    // The title, author and pages setters may be redundant and exchanged by ActionListeners
    void setTitle(String title) {
        this.title = title;
    }
    
    void setAuthor(String author) {
        this.author = author;
    }
    
    void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString(){
        return String.format("%s%s%d", title, author);
    }
}
