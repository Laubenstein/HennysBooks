import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HennysBooks {
    private static final String LIBRARY_DEFAULT_NAME = "HennysLibrary.bin";
    private Persistence persistence;
    private Library library;
    private Frame mainFrame;
    private Controller controller;

    public HennysBooks() throws LibraryException {
        initModelAndPersistence();
        BookPanel bookPanel = new BookPanel(library);
        JButton newBookButton = new JButton("Neues Buch");
        mainFrame = new MainFrame(bookPanel, newBookButton);
        controller = new Controller(library, bookPanel, persistence, mainFrame);
        newBookButton.addActionListener(controller);
        mainFrame.addWindowListener(controller);
        mainFrame.setVisible(true);

    }

    private void initModelAndPersistence() {
        String libraryDirPath = System.getProperty("user.home") + "/libraries";
        File libraryDir = new File(libraryDirPath);
        libraryDir.mkdir();
        String libraryPath = libraryDirPath + "/" + LIBRARY_DEFAULT_NAME;
        persistence = new Persistence(libraryPath);
        File libraryFile = new File(libraryPath);
        if (libraryFile.exists()) {
            System.out.println("Library-Datei gefunden, lade Library");
            try {
                library = persistence.loadLibrary();
            } catch (LibraryException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Library-Datei nicht gefunden, erstelle neue Library");
            library = new Library();
        }
    }


    public static void main(String[] args) throws LibraryException {
        new HennysBooks();
    }
}