/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pawel
 */
public class NamedEntity {
    @XmlAttribute(required=true)
    private int id;
    @XmlAttribute(required=true)
    private String name;

    public NamedEntity() {
    }

    public NamedEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    

    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
