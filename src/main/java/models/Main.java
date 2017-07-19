package models;

import controller.ControllerImpl;
import factory.ContactsFactory;
import factory.EntityFactory;
import factory.GroupFactory;
import utilits.ConsoleReader;
import utilits.TeamList;

import java.io.IOException;


/**
 *
 */
public class Main {
    public static void main(String[] args){
        new Main().goo();
    }
    public void goo()  {
        try (ConsoleReader reader = new ConsoleReader()){
            new ControllerImpl(reader)
                    .startPage();
        }catch (Exception e) {
            e.printStackTrace();
        }



        //Это фабричный метод
//        String name = "FIO";
//        String command = "gro";
//        try {
//            //введите что хотите создать
//            EntityFactory factory = creatingEntityFactory(command);
//            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
//                System.out.println("контакт"); //Введите FIO контакта;
//
//            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro))) //Введите имя группы
//            System.out.println("группа"); //Введите FIO контакта
//            Entity entity = factory.creatingEntity(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    static EntityFactory  creatingEntityFactory (String entity) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new ContactsFactory();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new GroupFactory();
        throw new IOException();
    }
}
