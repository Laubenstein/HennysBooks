import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


public class BookPanel extends JPanel {
    private Library model;

    private static final Color EVEN_ROW_COLOR = new Color(240, 240,255);
    private static final Color ODD_ROW_COLOR = new Color(255, 255, 240);

    public BookPanel(Library model) {
        this.model = model;
        setBackground(Color.WHITE);
        setLayout(new GridLayout(0, 4, 0, 1));
        refresh();
    }

    public void refresh(){
        removeAll();
        boolean isEvenRowNumber = true;
        // Überschriften
        JLabel authorHeading = new JLabel("Author");
        authorHeading.setBackground(Color.PINK);
        add(authorHeading);
        JLabel titleHeading = new JLabel("Book Title");
        titleHeading.setBackground(Color.PINK);
        add(titleHeading);
        JLabel pagesHeading = new JLabel("Pages");
        pagesHeading.setBackground(Color.PINK);
        add(pagesHeading);
        JLabel deleteButtonHeading = new JLabel("");
        deleteButtonHeading.setBackground(Color.PINK);
        add(deleteButtonHeading);
        Iterator<Book> iterator = model.iterator();
        // Bücher aus der Liste reihenweise einfügen
        int buttonIndex = 0;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            Color rowColor = isEvenRowNumber ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
            JLabel authorLabel = new JLabel(book.getAuthor());
            authorLabel.setBackground(rowColor);
            add(authorLabel);
            JLabel titleLabel = new JLabel(book.getTitle());
            titleLabel.setBackground(rowColor);
            add(titleLabel);
            JLabel pagesLabel = new JLabel(String.valueOf(book.getPages()));
            pagesLabel.setBackground(rowColor);
            add(pagesLabel);
            JButton removeButton = new JButton("Delete" );
            String name = String.valueOf(buttonIndex);
            removeButton.setName(name);
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {

                    model.removeBook(Integer.parseInt(removeButton.getName()));
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
