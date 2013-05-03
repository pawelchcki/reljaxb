/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping.container;

import ws.chojnacki.timetable.rxmldata.container.*;
import ws.chojnacki.timetable.mapping.Stop;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Stops implements ContainerInterface<Stop> {
    @XmlElement(required=true)
    private Collection<Stop> stop;
    

    public void setIdentifiedEntities(Collection<Stop> ieCollection) {
        stop = ieCollection;
    }

    public Collection<Stop> getIdentifiedEntities() {
        return stop;
    }

    public void addIdentifiedEntities(Stop... ies) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
