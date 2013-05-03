/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping.container;

import ws.chojnacki.timetable.rxmldata.container.*;
import ws.chojnacki.timetable.mapping.Line;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pawel
 */
@XmlRootElement(name="timetable")
public class Lines implements ContainerInterface<Line>{
    @XmlElement(required=true, name="line")
    private Collection<Line> lines;
    
    @XmlTransient
    public void setIdentifiedEntities(Collection<Line> ieCollection) {
        this.lines = ieCollection;
    }

    public Collection<Line> getIdentifiedEntities() {
        return this.lines;
    }
    
    public void addIdentifiedEntities(Line... ies) {
        for (Line line : ies) {
            lines.add(line);
        }
    }


}
