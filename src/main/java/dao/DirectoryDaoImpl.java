package dao;

import org.apache.log4j.Logger;
import storage.RefBook;
import java.io.*;

/**
 *Дао для работы со справочником контактов
 */
public class DirectoryDaoImpl implements DirectoryDao {
    private static final Logger LOGGER = Logger.getLogger(DirectoryDaoImpl.class);

    @Override
    public void save(RefBook refBook) {
        try (ObjectOutputStream stream  = new ObjectOutputStream(new FileOutputStream(
                new File("RefBook.ser")))) {
            stream.writeObject(refBook);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public RefBook load() {
        RefBook refBook = null;
        final File file = new File("RefBook.ser");
        if (file.exists()){
            try (ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(file))) {
                refBook = (RefBook) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            refBook = new RefBook();
        }
        return refBook;
    }


}
