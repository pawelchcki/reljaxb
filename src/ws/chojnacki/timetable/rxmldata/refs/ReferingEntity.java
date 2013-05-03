/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chojnacki.timetable.rxmldata.refs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ws.chojnacki.timetable.rxmldata.EntityFactory;
import ws.chojnacki.timetable.rxmldata.IdentifiedEntity;

/**
 *
 * @author Pawel
 */
@XmlType(name="")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferingEntity {
    @XmlAttribute(name = "id", required = true)
    private int id;

    public ReferingEntity(int id) {
        setId(id);
    }

    public ReferingEntity(){}
    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
