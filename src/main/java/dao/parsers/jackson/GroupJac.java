package dao.parsers.jackson;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 *Модель группы jackson
 */
public class GroupJac {

    /**
     * title
     * contact
     */
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "title")
    private String title;

    public GroupJac() {
    }

    public GroupJac(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupJac{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
