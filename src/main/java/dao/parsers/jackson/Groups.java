package dao.parsers.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 *
 */
@JacksonXmlRootElement(localName = "groups")
public final class Groups {

    @JacksonXmlElementWrapper(localName = "group",useWrapping = false)
    private List<GroupJac> group;

    public Groups() {
    }

    public Groups(List<GroupJac> group) {
        this.group = group;
    }

    public List<GroupJac> getGroup() {
        return group;
    }

    public void setGroupJacs(List<GroupJac> group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupJacs=" + group +
                '}';
    }
}
