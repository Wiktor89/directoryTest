package views;

import utilits.ConsoleReader;
import utilits.TeamList;
import java.io.EOFException;
import java.io.IOException;

/**
 * View для инф.
 */
public class ViewInf {

    private ConsoleReader consol = null;
    private ViewImpl view  = null;

    public ViewInf() {
        this.consol = new ConsoleReader();
        this.view = new ViewImpl();
        this.view.setConsol(consol);
        this.view.setView(this);
    }

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

    public void pageActionGroup() {
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для группы\n");
        stringBuilder.append("Создать группу                      --add\n");
        stringBuilder.append("Удалить группу                      --rem\n");
        stringBuilder.append("Редактировать группу                --upd\n");
        stringBuilder.append("Список групп                        --list\n");
        stringBuilder.append("Показать список контактов группы    --listc\n");
        stringBuilder.append("Вверх                               --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        view.actionGroup();
    }

    public void pageActionContact(){
        StringBuilder stringBuilder = new StringBuilder("Выберите действие для контакта\n");
        stringBuilder.append("Добавить контакт                --add\n");
        stringBuilder.append("Удалить контакт                 --rem\n");
        stringBuilder.append("Редактировать контакт           --upd\n");
        stringBuilder.append("Показать список контактов       --list\n");
        stringBuilder.append("Добавить контакт в группу       --addg\n");
        stringBuilder.append("Удалить контакт из группы       --remg\n");
        stringBuilder.append("Показать информацию о контакте  --inf\n");
        stringBuilder.append("Вверх                           --up\n");
        System.out.println(stringBuilder);
        System.out.println("==========================================\n");
        try {
            view.actionContact();
        } catch (EOFException e) {
            System.out.println("не удача");
        }catch (IOException e){

        }

    }

    public void getNoGroup() {
        System.out.println("нет группы");
    }

    public void getNoContact(){
        System.out.println("нет Ф И О");
    }

    public void getSuc() {
        System.out.println("успешно");
    }

    public void emptyLine(){
        System.out.println("пустая строка");
    }

    public void notFound(){
        System.out.println("не найденно");
    }

    public void emptyList(){
        System.out.println("пустой список");
    }

    public String getExit(){
        System.out.println("для выхода exit");
        return this.consol.readString();
    }
}
