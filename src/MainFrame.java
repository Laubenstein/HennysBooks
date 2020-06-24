import java.awt.*;


public class MainFrame extends Frame {
    ScrollPane scroll = new ScrollPane();
    public MainFrame (BookPanel bookPanel, Button newButton) {
        super("Bibliothek");
        setBackground(Color.LIGHT_GRAY);
        scroll.add(bookPanel, BorderLayout.NORTH);
        Panel southPanel = new Panel();
        southPanel.add(newButton);
        add(southPanel, BorderLayout.SOUTH);
        add(scroll);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
