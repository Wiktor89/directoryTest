package models;

/**
 *Модель группы
 */
public class Group extends IdentifiedEntity  {
    /**
     * nameGroup
     * contact
     */
    private String nameGroup;
    private Contact contact;

    public Group(String nameGroup) {
        super();
        this.nameGroup = nameGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     *
     * @return имя группы
     */
    @Override
    public String toString() {
        return nameGroup;


    }
}
