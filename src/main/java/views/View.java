package views;


/**
 * Интерфейс для вью
 */
public interface View {

    /**
     * Действия с контактом
     */
    void actionContact() ;

    /**
     * Действия с группой
     */
    void actionGroup() ;

    /**
     * Добавление контакта
     */
    void addContact(String command);

    /**
     * Действия для группы
     */
    void updateContact() ;

    /**
     * Список контактов
     */
    void getContacts();

    /**
     * Список групп
     */
    void getGroups();

    /**
     * Полная инф. о контакте
     */
    void getContactInfo() ;

    /**
     * Ввод контакта
     */
    String getNameContact() ;

    /**
     * Ввод группы
     */
    String getNameGroup();

    /**
     * Поиск контакта по имени
     */
    void searchName();
    
    /**
     * Стартовая страница
     */
    void startPage();
    
    /**
     * Страница действия для группы
     */
    void pageActionGroup();
    
    /**
     * Страница действия для контакта
     */
    void pageActionContact();
    
    /**
     * Сообщение нет группы
     */
    void getNoGroup();
    
    /**
     * Список групп
     */
    void getGroupsUtilit();
    
    /**
     * Сообщение успешно
     */
    void getSuc();
    
    /**
     * Сообщение пустая строка
     */
    void emptyLine();
    
    /**
     * Сообщение не найденно
     */
    void notFound();
    
    /**
     * Сообщение пустой список
     */
    void failed();
    
    /**
     * Страница авторизации
     */
    void authorizationPage();
    
    /**
     * Удаление контакта
     */
    void removeContact();
    
    /**
     * Добавление группы контакту
     */
    void appGroupContact();
    
    /**
     * Удаление группы у контакта
     */
    void removeGroupContact();
    
    /**
     * Добавление группы
     */
    void addGroup(String name);
    
    /**
     * Удаление группы
     */
    void removeGroup();
    
    /**
     * Обновление группы
     */
    void updateGroup();
    
    /**
     * Показать список контактов группы
     */
    void getContactsGroup();
    
    /**
     * Количество пользователей
     */
    void numberUsers();
    
    /**
     * Количество контактов каждого пользователя
     */
    void numberContacts();
    
    /**
     * Количество групп каждого пользователя
     */
    void quantityGroupsUser();
    
    /**
     * Среднее количество контактов в группах
     */
    void averageNumberContactsGroups();
    
    /**
     * Среднее количество контактов у пользователя
     */
    void averageNumberContactsUser();
    
    /**
     * Пользователь с количеством контактов < 10
     */
    void userWithContactsMin_10();
    
    /**
     * Страница с аналитической информацией БД
     */
    void pageAnalyticalInf();
    
    /**
     * Страница действия с аналитик. инф.
     */
    void actionAnalyticalInf();
    
    /**
     * Возвращает имя пользователя
     */
    String getNameUser();
    
    /**
     * Возвращает password пользователя
     */
    String getPasswordUser();
    
    /**
     * Ввод нового имени группы
     */
    String getNameNewGroup();
    
    
    
    
}
