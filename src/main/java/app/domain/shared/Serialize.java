package app.domain.shared;

import java.io.*;

public class Serialize {

    private Serialize() {
    }

    public static Object readFile(String stringFile) throws IOException {
        Object read = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(stringFile))) {
            read = in.read();
            return read;
        } catch (IOException ex) {
            throw new IOException("File dont exists");
        }

    }

    public static boolean writeObject(String stringFile, Object savedObject) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(stringFile));
            try {
                out.writeObject(savedObject);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
