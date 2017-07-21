package views;

import controller.ControllerImpl;
import models.Contact;
import models.Group;
import utilits.ConsoleReader;
import utilits.TeamList;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *Отображение
 */
public class ViewImpl implements View {

    private ControllerImpl controller = null;
    private ConsoleReader consol = new ConsoleReader();


    public ViewImpl() {

    }

    public ControllerImpl getController() {
        return new ControllerImpl();
    }

    public void setController(ControllerImpl controller) {
        this.controller = controller;
    }

    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }

    @Override
    public void startPage() {
        while (true) {
            System.out.println("==========================================\n");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Стартовая страница \n");
            stringBuilder.append("Действие с контактом введите         --con\n");
            stringBuilder.append("Действие с группой контактов введите --gro\n");
            stringBuilder.append("Для выхода введите                   --exit\n");
            System.out.println(stringBuilder);
            System.out.println("==========================================\n");
            String command = this.consol.readString();
            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
                pageActionContact();
            if ((command.equalsIgnoreCase(String.valueOf(TeamList.gro))))
                pageActionGroup();
            if ((command.equalsIgnoreCase(String.valueOf(TeamList.exit))))
                System.exit(0);
            System.out.println("Команда не поддерживается");
        }
    }

    @Override
    public void pageActionContact(){
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для контакта\n");
        stringBuilder.append("Добавить контакт                --add\n");
        stringBuilder.append("Удалить контакт                 --rem\n");
        stringBuilder.append("Редактировать контакт           --upd\n");
        stringBuilder.append("Показать список контактов       --list\n");
        stringBuilder.append("Добавить контакт в группу       --addcatg\n");
        stringBuilder.append("Удалить контакт из группы       --remcofg\n");
        stringBuilder.append("Показать информацию о контакте  --inf\n");
        stringBuilder.append("Вверх                           --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        try {
            actionContact();
        } catch (EOFException e) {
            System.out.println("не удача");
        }catch (IOException e){

        }

    }

    @Override
    public void actionContact() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
            addContact("con");//Ввел добавить контакт
        if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
            remContact();//Наблюдатель Удаление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updContact();//Обновление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            appGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            remGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
            getContactInfo();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
            startPage();
        pageActionContact();
    }

    @Override
    public void addContact(String command) throws IOException   {
        List<String> attContact = new ArrayList<>();
        String fio = getNameContact();
        if (fio.trim().length() > 0){
            attContact.add(fio);
            System.out.println("Введите телефон");
            attContact.add(this.consol.readString());
            System.out.println("Введите email");
            attContact.add(this.consol.readString());
            attContact.add("нет группы");
            this.controller.addEntity(attContact,command);
            getSuc();
        }else {
            getNoContact();
        }
    }//***

    public void remContact ()  {
        getContacts();
        String fio = getNameContact();
        if (fio.trim().length() > 0){
            if (this.controller.existContact(fio)){
                controller.remContact(fio);
                getSuc();
            }
        }else{
            getNoContact();
        }
    }//***Работает

    @Override
    public String getNameContact()  {
        System.out.println("Введите Ф И О контакта (обязательное поле)");
        return  this.consol.readString();
    }

    @Override
    public void updContact()  {
        getContacts();
        List<String> attContact = new ArrayList<>();
        String name = getNameContact();
        attContact.add(0,name);
        if (this.controller.existContact(name)){
            System.out.println("Введите новое Ф И О");
            attContact.add(1,this.consol.readString());
            if (attContact.get(1).trim().length() > 0){
                System.out.println("Введите новый телефон");
                attContact.add(2,this.consol.readString());
                System.out.println("Введите новый email");
                attContact.add(3,this.consol.readString());
                this.controller.updContact(attContact);
                getSuc();
            }else {
                System.out.println("Вы не ввели Ф И О");
            }
        }else {
            System.out.println("нет контакта");
        }
    }//***Работает

    void appGroupContact() {
        List<String> attContact = new ArrayList<>();
        String fio = getNameContact();
        if (this.controller.existContact(fio)){
            String name = getNameGroup();
            if (this.controller.existGroup(name)){
                attContact.add(0, fio);
                attContact.add(1, name);
                this.controller.appGroupContact(attContact);
            }else {
                getNoGroup();
            }
        }else {
            getNoContact();
        }
    }//Добавление группы Работает



    @Override
    public void getContactInfo() {
        getContacts();
        Contact contact = this.controller.getContact(getNameContact());
        if (!(contact == null)){
            System.out.println(contact);
        }else {
            getNoContact();
        }
    }///Принимает контакт и выводит инф. о нем


    void remGroupContact() {
        String fio = getNameContact();
        if (this.controller.existContact(fio)){
            Contact contact = this.controller.getContact(fio);
            System.out.println(contact);
            String name = getNameGroup();
            if (this.controller.existGroup(name)){
                this.controller.remGroupContact(fio);
            }
        }else {
            getNoContact();
        }
    }//***


    @Override
    public void pageActionGroup() {
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для группы\n");
        stringBuilder.append("Создать группу                      --add\n");
        stringBuilder.append("Удалить группу                      --rem\n");
        stringBuilder.append("Редактировать группу                --upd\n");
        stringBuilder.append("Список групп                        --list\n");
        stringBuilder.append("Показать список контактов группы    --listcofg\n");
        stringBuilder.append("Вверх                               --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        actionGroup();
    }

    @Override
    public void actionGroup()  {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
            addGroup("gro");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
            remGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getGroups();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
            getContactsGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))startPage();
        pageActionGroup();
    }

    public void addGroup (String command) {
        List<String> attGroup = new ArrayList<>();
        String name = getNameGroup();
        if (name.trim().length() > 0){
            attGroup.add(name);
            try {
                this.controller.addEntity(attGroup,command);
                getSuc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("не ввели имя группы");
        }
    } //***

    public void remGroup() {
        getGroups();
        String name = getNameGroup();
        if(name.trim().length() > 0){
            if (this.controller.existGroup(name)){
                this.controller.remGroup(name);
                getSuc();
            }else {
                getNoGroup();
            }
        }else {
            emptyLine();
        }
    }//***

    public void updGroup() {
        getGroups();
        List<String> attGoup = new ArrayList<>();
        String oldName = getNameGroup();
        if (this.controller.existGroup(oldName)){
            String newName = getNameGroup();
            if (newName.trim().length() > 0){
                attGoup.add(0,oldName);
                attGoup.add(1,newName);
                this.controller.updGroup(attGoup);
            }else {
                emptyLine();
            }
        }else {
            getNoGroup();
        }
    }//***

    @Override
    public void getGroups(){
        System.out.println("Список доступных групп");
        Set<Group> groups = this.controller.getGroups();
        for (Group group : groups){
            System.out.println(group);
        }
    }//***




    public void getContactsGroup(){
        Set<Contact> contacts = this.controller.getContactsGroup(getNameGroup());
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
    }//***

    @Override
    public String getNameGroup() {
        System.out.println("Введите имя группы (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public void getNoGroup() {
        System.out.println("нет группы");
    }

    public void getNoContact(){
        System.out.println("нет Ф И О");
    }

    @Override
    public void getSuc() {
        System.out.println("успешно");
    }

    @Override
    public void getContacts(){
        Set<Contact> contacts = this.controller.getContacts();
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
    }

    public String getExit(){
        System.out.println("для выхода exit");
        return this.consol.readString();
    }

    public void emptyLine(){
        System.out.println("пустая строка");
    }

}
