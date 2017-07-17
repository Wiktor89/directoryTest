package dao;

import storage.RefBook;

/**
 *То что класс умеет
 */
public interface DirectoryDao {
    public void save(RefBook refBook);
    public RefBook load();
}
