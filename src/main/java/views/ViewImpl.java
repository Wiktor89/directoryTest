package views;

import controller.ControllerImpl;
import models.Contact;
import models.Group;
import utilits.ConsoleReader;
import utilits.TeamList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            actionContacts();
        } catch (IOException e) {
            System.out.println("Вы не ввели Ф И О");
            pageActionContact();
        }
    }
    
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
            System.out.println("нет группы/ или не ввели имя новой группы");
            pageActionGroup();
        }
    }

    @Override
    public void actionContacts() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addc)))controller
                .addEntity("con");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remc)))
            controller.removeContact();//Наблюдатель Удаление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.updc)))
            controller.updateContact();//Обновление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
            controller.listContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addcatg)))
            controller.appGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remcofg)))
            controller.removeGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
            controller.informationContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
            startPage();
//        System.out.println("Команда не поддерживается");
        pageActionContact();
    }
    
    @Override
    public void actionGroup() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            controller.addEntity("gro");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            controller.removeGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.updg)))
            controller.updateGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listg)))
            controller.listGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listcofg)))
            controller.listGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))startPage();
//        System.out.println("Команда не поддерживается");
        pageActionGroup();
    }

    @Override
    public List<String> addContact() {
        List<String> attContact = new ArrayList<>();
        attContact.add(entContact());
        System.out.println("Введите телефон");
        attContact.add(this.consol.readString());
        System.out.println("Введите email");
        attContact.add(this.consol.readString());
        return attContact;
    }


    @Override
    public List<String> updateContact() {
        List<String> contacts = new ArrayList<>();
        contacts.add(entContact());
        System.out.println("Введите новый телефон");
        contacts.add(this.consol.readString());
        System.out.println("Введите новый email");
        contacts.add(this.consol.readString());
        return contacts;
    }

    @Override
    public void listContacts(Contact contact) {
        System.out.println(contact.informationContact());
    }

    @Override
    public void listGroup(Group group) {
        System.out.println("Список доступных групп");
        System.out.println(group);
    }

    @Override
    public void informationContact(Contact contact) {
        System.out.println(contact);
    }

    @Override
    public String entContact() {
        System.out.println("Введите Ф И О контакта (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public String entGroup() {
        System.out.println("Введите имя группы (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public String noGroup() {
        return "нет группы";
    }

    @Override
    public void succesAdd() {
        System.out.println("успешно добавлено");
    }

}
