package views;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
        stringBuilder.append("Добавить контакт                --addc\n");
        stringBuilder.append("Удалить контакт                 --remc\n");
        stringBuilder.append("Редактировать контакт           --updc\n");
        stringBuilder.append("Показать список контактов       --listc\n");
        stringBuilder.append("Добавить контакт в группу       --addcatg\n");
        stringBuilder.append("Удалить контакт из группы       --remcofg\n");
        stringBuilder.append("Показать информацию о контакте  --inf\n");
        stringBuilder.append("Вверх                           --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        try {
            actionContact();
        } catch (EOFException e) {
            System.out.println("нет доступных групп");
            pageActionContact();
        }catch (IOException e){
            System.out.println("Вы не ввели Ф И О");
            pageActionContact();
        }
    }

    @Override
    public void actionContact() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addc)))
            addContact("con");//Ввел добавить контакт
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remc)))
            remContact();//Наблюдатель Удаление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.updc)))
            updContact();//Обновление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
            getContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addcatg)))
            appGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remcofg)))
            remGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
            getContactInfo();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
            startPage();
        pageActionContact();
    }

    @Override
    public void addContact(String command) throws IOException {
        List<String> attContact = new ArrayList<>();
        do {
            attContact.add(getNameContact());
            System.out.println("Введите телефон");
            attContact.add(this.consol.readString());
            System.out.println("Введите email");
            attContact.add(this.consol.readString());
            attContact.add("нет группы");
        }while (attContact.get(0).trim().length() > 0);
        controller.addEntity(attContact,command);
        getSuc();
    }//***

    public void remContact (){
        getContacts();
        controller.remContact(getNameContact());
    }//***

    @Override
    public void updContact() {
        Contact contact = this.controller.getContact(getNameContact());
        String fio = getNameContact();
        while (fio.trim().length() > 0){
            contact.setFio(fio);
            System.out.println("Введите новый телефон");
            contact.setPhone(this.consol.readString());
            System.out.println("Введите новый email");
            contact.setEmail(this.consol.readString());
        }
        this.controller.updContact(contact);

    }//***

    void appGroupContact(){
        Contact contact = this.controller.getContact(getNameContact());
        getGroups();
        Group group = contact.getGroup();
        String name = getNameGroup();
        if (name.trim().length() > 0){
            group.setName(name);
            this.controller.appGroupContact(contact);
        }else {
            System.out.println("нет имени группы");
        }
    }//***

    @Override
    public void getContactInfo() {
        Contact contact = this.controller.getContact(getNameContact());
        System.out.println(contact);
    }///***

    void remGroupContact(){
        Contact contact = this.controller.getContact(getNameContact());
        Group group = contact.getGroup();
        group.setName("нет группы");
        this.controller.remGroupContact(contact);
    }//***




    @Override
    public void pageActionGroup() {
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для группы\n");
        stringBuilder.append("Создать группу                      --addg\n");
        stringBuilder.append("Удалить группу                      --remg\n");
        stringBuilder.append("Редактировать группу                --updg\n");
        stringBuilder.append("Список групп                        --listg\n");
        stringBuilder.append("Показать список контактов группы    --listcofg\n");
        stringBuilder.append("Вверх                               --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        try {
            actionGroup();
        } catch (IOException e) {
            System.out.println("нет групп");
            pageActionGroup();
        }
    }


    @Override
    public void actionGroup() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            addGroup("gro");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            remGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.updg)))
            updGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listg)))
            getGroups();


        if (command.equalsIgnoreCase(String.valueOf(TeamList.listcofg)))
            getContactsGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))startPage();
        pageActionGroup();
    }

    public void addGroup (String command) throws IOException{
        List<String> attGroup = new ArrayList<>();
        String name = getNameGroup();
        if (name.trim().length() > 0){
            attGroup.add(name);
            this.controller.addEntity(attGroup,command);
        }else {
            System.out.println("не ввели имя группы");
        }
    } //***

    public void remGroup() throws IOException{
        String name = getNameGroup();
        if(name.trim().length() > 0){
            this.controller.remGroup(name);
        }else {
            System.out.println("не ввели имя группы");
        }
    }//***

    public void updGroup() throws IOException{
        String name = getNameGroup();
        Group group = this.controller.getGroup(name);
        String oldName = group.getName();
        if (!(group == null)){
            group.setName(getNameGroup());
        }

        this.controller.updGroup(group,oldName);
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
        this.controller.get

    }




    @Override
    public String getNameContact() {
        System.out.println("Введите Ф И О контакта (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public String getNameGroup() {
        System.out.println("Введите имя группы (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public String getNoGroup() {
        return "нет группы";
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

}
