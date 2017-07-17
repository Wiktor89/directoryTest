package models;

import controller.ControllerImpl;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import utilits.ConsoleReader;

import java.io.IOException;


/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new Main().goo();



    }

    public void goo()  {
        try (ConsoleReader reader = new ConsoleReader()){
            new ControllerImpl(reader)
                    .startPage();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
