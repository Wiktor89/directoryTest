package net.directory.utilits;

/**
 *
 */
public class Util {



    public static String numberUsers (Integer number){
        return "{number_users:"+number+"}";
    }


    public static String numberContact (Integer number){
        return "{number_contacts:"+number+"}";
    }

    public static String quantityGroupsUserRest (Integer number){
        return "{quantity_groups_users:"+number+"}";
    }

    public static String averageNumberContactsGroupsRest (Integer number){
        return "{average_number_group:"+number+"}";
    }

    public static String averageNumberContactsUser (Integer number){
        return "{average_number_contacts_user:"+number+"}";
    }


    public static String userWithContactsMin_10 (Integer number){
        return "{user_with_contacts_min_10:"+number+"}";
    }
}
