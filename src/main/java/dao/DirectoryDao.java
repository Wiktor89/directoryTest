package dao;

import storage.RefBook;

/**
 *Dao
 */
public interface DirectoryDao {

    /**
     *Сохранение в файл
     */
     void save(RefBook refBook);

    /**
     * return @RefBook
     *Чтение из файла
     */
     RefBook load();
}
