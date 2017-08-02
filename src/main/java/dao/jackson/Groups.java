package dao.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 *Обертка для групп
 */
@JacksonXmlRootElement(localName = "groups")
public final class Groups {

    @JacksonXmlElementWrapper(localName = "group",useWrapping = false)
    private List<Group> group;

    public Groups() {
    }

    public Groups(List<Group> group) {
        this.group = group;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroupJacs(List<Group> group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupJacs=" + group +
                '}';
    }

}