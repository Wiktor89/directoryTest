package models;

import views.ViewImpl;

import java.sql.SQLException;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        new Main().goo();
    }

    public void goo() throws SQLException {
//        ViewImpl view = new ViewImpl();
//        view.run();
        Thread thread = new Thread(new ViewImpl());
        thread.start();
    
    
    }
}
