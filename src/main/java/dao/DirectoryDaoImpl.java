package dao;

import org.apache.log4j.Logger;
import storage.RefBook;

import java.io.*;

/**
 *Класс для загрузки и записи справочника
 */
public class DirectoryDaoImpl implements DirectoryDao {
    private static final Logger LOGGER = Logger.getLogger(DirectoryDaoImpl.class);

    /**
     *
     * @param refBook объект для серелизации
     */
    @Override
    public void save(RefBook refBook) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(
                    new File("RefBook.ser")));
            stream.writeObject(refBook);
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return десерелизованный объект
     */
    @Override
    public RefBook load() {
        RefBook refBook = null;
        final File file = new File("RefBook.ser");
        if (file.exists()){
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(
                        new File("RefBook.ser")));
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
