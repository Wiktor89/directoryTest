package views;

import utilits.ConsoleReader;

/**
 *Посмотреть
 */
public class View {

    private ConsoleReader consol = null;

    public View() {
        this.consol = new ConsoleReader();
    }

    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }

    public String startPage(){
        System.out.println("==========================================\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Стартовая страница \n");
        stringBuilder.append("Действие с контактом введите         --con\n");
        stringBuilder.append("Действие с группой контактов введите --gro\n");
        stringBuilder.append("Для выхода введите                   --exit\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        return this.consol.readString();
    }

    public String pageRemoveContact(){
        System.out.println("Введите fio контакта который хотите удалить");
        return this.consol.readString();
    }

    public String pageActionContact(){
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
        return this.consol.readString();
    }

    public String pageActionGroup() {
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для группы\n");
        stringBuilder.append("Создать группу                      --addg\n");
        stringBuilder.append("Удалить группу                      --remg\n");
        stringBuilder.append("Редактировать группу                --updg\n");
        stringBuilder.append("Список групп                        --listg\n");
        stringBuilder.append("Показать список контактов группы    --listcofg\n");
        stringBuilder.append("Вверх                               --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        return this.consol.readString();
    }

    public String pageDeleteGroup(){
        System.out.println("Введите имя группы для удаления");
        return this.consol.readString();
    }

    public String pageInformationConatact(){
        System.out.println("Введите fio контакта инф. о котором хотите просмотреть");
        return this.consol.readString();
    }

    public String pageUpdateContact(){
        System.out.println("Введите fio контакта который хотите отредактировать");
        return this.consol.readString();
    }

    public String pageGroupContacts(){
        System.out.println("Введите название группы");
        return this.consol.readString();
    }
}
