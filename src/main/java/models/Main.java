package models;

import views.ViewInf;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        new Main().goo();
    }

    public void goo() {
        ViewInf viewInf  = new ViewInf();
        viewInf.startPage();
    }
}
