import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


public class BookPanel extends Panel {
    private Library model;

    private static final Color EVEN_ROW_COLOR = new Color(240, 240,255);
    private static final Color ODD_ROW_COLOR = new Color(255, 255, 240);

    public BookPanel(Library model) {
        this.model = model;
        setBackground(Color.BLACK);
        setLayout(new GridLayout(0, 4, 0, 1));
        refresh();
    }

    public void refresh(){
        removeAll();
        boolean isEvenRowNumber = true;
        // Überschriften
        Label authorHeading = new Label("Author");
        authorHeading.setBackground(Color.PINK);
        add(authorHeading);
        Label titleHeading = new Label("Book Title");
        titleHeading.setBackground(Color.PINK);
        add(titleHeading);
        Label pagesHeading = new Label("Pages");
        pagesHeading.setBackground(Color.PINK);
        add(pagesHeading);
        Label deleteButtonHeading = new Label("");
        deleteButtonHeading.setBackground(Color.PINK);
        add(deleteButtonHeading);
        Iterator<Book> iterator = model.iterator();
        // Bücher aus der Liste reihenweise einfügen
        int buttonIndex = 0;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            Color rowColor = isEvenRowNumber ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
            Label authorLabel = new Label(book.getAuthor());
            authorLabel.setBackground(rowColor);
            add(authorLabel);
            Label titleLabel = new Label(book.getTitle());
            titleLabel.setBackground(rowColor);
            add(titleLabel);
            Label pagesLabel = new Label(String.valueOf(book.getPages()));
            pagesLabel.setBackground(rowColor);
            add(pagesLabel);
            Button removeButton = new Button("" + buttonIndex);
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    model.removeBook(Integer.parseInt(removeButton.getLabel()));
                    refresh();
                }
            });
            buttonIndex++;
            this.add(removeButton);

            isEvenRowNumber = !isEvenRowNumber;
        }
        revalidate();
    }

}
