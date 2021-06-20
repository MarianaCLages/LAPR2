package app.domain.shared;

import app.controller.App;
import app.domain.model.Company;

import java.io.*;
import java.util.Properties;

public class Serialize {

    private Serialize() {
    }

    /**
     * Reads objects from a file.
     *
     * @param stringFile the file
     * @return the file's content
     * @throws IOException
     */
    public static Object readFile(String stringFile) throws IOException {
        Object read = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(stringFile))) {
            read = in.readObject();
            return read;
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException("File doesn't exist!");
        }
    }

    /**
     * Writes in a file.
     *
     * @param stringFile  the file
     * @param savedObject the saved object
     * @return true if it writes, false if it doesn't
     */
    public static boolean writeObject(String stringFile, Company savedObject) {
        try {
            Properties props = App.getProperties();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(stringFile));
            File dir = new File(props.getProperty("serialize.folder"));
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                out.writeObject(savedObject);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
