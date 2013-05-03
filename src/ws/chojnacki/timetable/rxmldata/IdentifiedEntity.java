/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;


import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import ws.chojnacki.timetable.rxmldata.refs.ReferingEntity;

/**
 *
 * @author Pawel
 */
public class IdentifiedEntity{
    @XmlAttribute(name="id") 
    private int id=-1;
    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
