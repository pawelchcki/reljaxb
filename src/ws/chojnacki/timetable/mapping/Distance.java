/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import ws.chojnacki.timetable.rxmldata.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ws.chojnacki.timetable.rxmldata.annotation.XmlMapping;
import ws.chojnacki.timetable.rxmldata.annotation.XmlStore;
import ws.chojnacki.timetable.mapping.container.Distances;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlStore(container=Distances.class)
public class Distance extends IdentifiedEntity{
    @XmlAttribute(name="from-stop", required=true)
    private long refFromStop;
    @XmlAttribute(name="to-stop", required=true)
    private long refToStop;

    @XmlTransient
    @XmlMapping(referenceFieldName="refFromStop", referedClass=Stop.class)
    private Stop fromStop;
    @XmlMapping(referenceFieldName="refToStop", referedClass=Stop.class)
    @XmlTransient
    private Stop toStop;

    @XmlAttribute(name="high-traffic")
    private int highTraffic;
    @XmlAttribute(name="low-traffic")
    private int lowTraffic;

    public Stop getFromStop() {
        return fromStop;
    }

    public void setFromStop(Stop fromStop) {
        this.fromStop = fromStop;
    }

    public int getHighTraffic() {
        return highTraffic;
    }

    public void setHighTraffic(int highTraffic) {
        this.highTraffic = highTraffic;
    }

    public int getLowTraffic() {
        return lowTraffic;
    }

    public void setLowTraffic(int lowTraffic) {
        this.lowTraffic = lowTraffic;
    }

    public Stop getToStop() {
        return toStop;
    }

    public void setToStop(Stop toStop) {
        this.toStop = toStop;
    }

    public Distance(Stop fromStop, Stop toStop, int highTraffic, int lowTraffic) {
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.highTraffic = highTraffic;
        this.lowTraffic = lowTraffic;
    }

    public Distance() {
    }
    

}
