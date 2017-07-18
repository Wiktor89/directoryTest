package models;

import controller.ControllerImpl;
import storage.RefBook;
import utilits.ConsoleReader;
import java.io.IOException;
import java.util.Set;


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
