import javax.swing.*;
import java.awt.*;


public class MainFrame extends Frame {
    ScrollPane scroll = new ScrollPane();
    public MainFrame (BookPanel bookPanel, JButton newButton) {
        super("Bibliothek");
        setBackground(Color.LIGHT_GRAY);
        scroll.add(bookPanel, BorderLayout.NORTH);
        JPanel southPanel = new JPanel();
        southPanel.add(newButton);
        add(southPanel, BorderLayout.SOUTH);
        add(scroll);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
