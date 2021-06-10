package app.domain.shared;

import app.domain.model.Company;

import java.io.*;

public class Serialize {

    private Serialize() {
    }

    public static Object readFile(String stringFile) throws IOException {
        Object read = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(stringFile))) {
            read = in.readObject();
            return read;
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException("File dont exists");
        }

    }

    public static boolean writeObject(String stringFile, Company savedObject) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(stringFile));
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
