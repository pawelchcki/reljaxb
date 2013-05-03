/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping.container;


import ws.chojnacki.timetable.rxmldata.container.*;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.chojnacki.timetable.mapping.Distance;

/**
 *
 * @author Pawel
 */

@XmlRootElement(name="distances")
@XmlAccessorType(XmlAccessType.FIELD)
public class Distances implements ContainerInterface<Distance>{
    @XmlElement(name="distance")
    private Collection<Distance> data;
    public void setIdentifiedEntities(Collection<Distance> ieCollection) {
        data = ieCollection;
    }

    public Collection<Distance> getIdentifiedEntities() {
        return data;
    }

    public void addIdentifiedEntities(Distance... ies) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
