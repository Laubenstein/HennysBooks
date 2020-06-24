import java.awt.*;
import java.awt.event.*;


public class Controller extends WindowAdapter implements ActionListener{
    private Frame frame;
    private Library library;
    private Persistence persistence;
    private BookPanel view;
    
    public Controller(Library library,
                      BookPanel view,
                      Persistence persistence,
                      Frame frame) {
        this.frame = frame;
        this.library = library;
        this.persistence = persistence;
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            persistence.storeLibrary(library);
            System.out.println("Bibliothek gespeichert");
        } catch (LibraryException le) {
            System.out.println("Bibliothek konnte nicht gespeichert werden");
            le.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createNewBookDialog();
    }

    private void createNewBookDialog() {
        Dialog newBookDialog = new Dialog(frame, "Neues Buch", true);
        Panel dataPanel = new Panel(new GridLayout(4, 2));
        TextField authorTextField = new TextField(30);
        TextField titleTextField = new TextField(30);
        TextField pagesTextField = new TextField(10);
        dataPanel.add(new Label("Autor: "));
        dataPanel.add(authorTextField);
        dataPanel.add(new Label("Buchtitel: "));
        dataPanel.add(titleTextField);
        dataPanel.add(new Label("Seiten: "));
        dataPanel.add(pagesTextField);
        newBookDialog.add(dataPanel, BorderLayout.CENTER);

        Button okButton = new Button("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Book newBook = new Book(authorTextField.getText(),
                            titleTextField.getText(),
                            Integer.parseInt(pagesTextField.getText()));
                    library.addBook(newBook);
                    view.refresh();
                    newBookDialog.dispose();
                } catch (NumberFormatException p) {
                    pagesTextField.setText("Ungültige Seitenzahl!");
                }

            }
        });



        Panel southPanel = new Panel();
        southPanel.add(okButton);
        newBookDialog.add(southPanel, BorderLayout.SOUTH);

        newBookDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                newBookDialog.dispose();
            }
        });

        newBookDialog.pack();
        newBookDialog.setLocationRelativeTo(frame);
        newBookDialog.setVisible(true);

    }
}
