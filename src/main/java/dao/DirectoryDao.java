package dao;

import storage.RefBook;

/**
 *Dao
 */
public interface DirectoryDao {

    /**
     *Сохранение в файл
     */
    public void save(RefBook refBook);

    /**
     * return @RefBook
     *Чтение из файла
     */
    public RefBook load();
}
