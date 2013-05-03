/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import ws.chojnacki.timetable.rxmldata.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ws.chojnacki.timetable.rxmldata.annotation.XmlStore;
import ws.chojnacki.timetable.mapping.container.Stops;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlStore(container=Stops.class)
public class Stop extends IdentifiedEntity {
    @XmlElement
    private GeoPoint location;
    @XmlAttribute(required=true)
    private String name;
    public Stop() {
    }
    public Stop(String name) {
        //super(id);
        this.name = name;
    }


    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
