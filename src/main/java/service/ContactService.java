package service;

import models.Contact;
import models.Entity;
import views.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     *Добавление контакта
     */
    void addContact (List<String> attrEntity, Entity entity);

    /**
     *Обновление контакта
     */
    void updContact(Contact contact);

    /**
     *Удаление контакта
     */
    void remContact(String fio);

    /**
     *Добавить контакту группу
     */
    void appGroupContact(Contact contact);

    /**
     *Удаление группы у контакта контакта
     */
    void remGroupContact(Contact contact);

    /**
     *Список контактов
     */
    Set<Contact> getContacts();

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);








//
//    /**
//     *Добавить наблюдателя
//     */
//    void addObserver(Observer observer);
//
//    /**
//     *Удалить наблюдателя
//     */
//    void remObserver(Observer observer);
//
//    /**
//     *Уведомить наблюдателя
//     */
//    void notifyObserver();


}
