package models;

import controller.ControllerImpl;
import utilits.ConsoleReader;
import views.ViewImpl;
import views.ViewInf;

import java.util.UUID;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        new Main().goo();
    }

    public void goo() {
        ConsoleReader consol = new ConsoleReader();
        ViewInf viewInf  = new ViewInf();
        viewInf.startPage();

        //Это фабричный метод
//        String name = "FIO";
//        String command = "gro";
//        try {
//            //введите что хотите создать
//            EntityFactory factory = creatingEntity(command);
//            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
//                System.out.println("контакт"); //Введите FIO контакта;
//
//            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro))) //Введите имя группы
//            System.out.println("группа"); //Введите FIO контакта
//            Entity entity = factory.creatingEntity(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//    static EntityFactory  creatingEntity (String entity) throws IOException{
//        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new ContactsFactory();
//        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new GroupFactory();
//        throw new IOException();
//    }
    }
}
