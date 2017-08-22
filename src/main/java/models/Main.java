package models;

import dao.database.ContactsDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        new Main().goo();
    }

    public void goo() throws SQLException {
    
        ContactsDaoImpl contactsDao = ContactsDaoImpl.getContactsDaoImpl();
        List<String> strings = new ArrayList<String>();
        strings.add(0,"A");
        strings.add(1,"root");
        User user = contactsDao.authorizationPage(strings);
        if (user == null){
            System.out.println("залогинелся");
        }
//        System.out.println(user.getEnable()+""+user.getLogin());
    
    
    }
}
