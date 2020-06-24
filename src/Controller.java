import javax.swing.*;
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
        JDialog newBookDialog = new JDialog(frame, "Neues Buch", true);
        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        JTextField authorTextField = new JTextField(30);
        JTextField titleTextField = new JTextField(30);
        JTextField pagesTextField = new JTextField(10);
        dataPanel.add(new JLabel("Autor: "));
        dataPanel.add(authorTextField);
        dataPanel.add(new JLabel("Buchtitel: "));
        dataPanel.add(titleTextField);
        dataPanel.add(new JLabel("Seiten: "));
        dataPanel.add(pagesTextField);
        newBookDialog.add(dataPanel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
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



        JPanel southPanel = new JPanel();
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
