import java.io.*;

public class Persistence {
    private String libraryPath;
    
    public Persistence(String libraryPath){
        this.libraryPath = libraryPath;
    }
    
    public Library loadLibrary() throws LibraryException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(libraryPath));
            return (Library) ois.readObject();
        } catch (IOException e) {
            throw new LibraryException(e);
        } catch (ClassNotFoundException e) {
            throw new LibraryException(e);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void storeLibrary(Library library) throws LibraryException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(libraryPath));
            oos.writeObject(library);
        } catch (IOException e) {
            throw new LibraryException(e);
        } finally {
            if (oos != null) try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
