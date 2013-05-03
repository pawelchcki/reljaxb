/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import ws.chojnacki.timetable.rxmldata.*;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ws.chojnacki.timetable.rxmldata.annotation.XmlStore;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlStore(container=ws.chojnacki.timetable.mapping.container.Lines.class)
public class Line extends IdentifiedEntity {
    @XmlAttribute()
    private String name;

    @XmlElement(name="route",required=true)
    private Route route;

    @XmlElementWrapper(name="departures-week")
    @XmlElement(name="departure",required=true)
    private Collection<Departure> departuresWeek;

    @XmlElementWrapper(name="departures-saturday")
    @XmlElement(name="departure",required=true)
    private Collection<Departure> departuresSaturday;

    @XmlElementWrapper(name="departures-holiday")
    @XmlElement(name="departure",required=true)
    private Collection<Departure> departuresHoliday;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    

    public Line() {
    }
    
    public Line(int id, String name) {
        setName(name);
        setId(id);
    }
    
    public Line(String name) {
        setName(name);
    }

    public Collection<Departure> getDeparturesHoliday() {
        return departuresHoliday;
    }

    public void setDeparturesHoliday(Collection<Departure> departuresHoliday) {
        this.departuresHoliday = departuresHoliday;
    }

    public Collection<Departure> getDeparturesSaturday() {
        return departuresSaturday;
    }

    public void setDeparturesSaturday(Collection<Departure> departuresSaturday) {
        this.departuresSaturday = departuresSaturday;
    }

    public Collection<Departure> getDeparturesWeek() {
        return departuresWeek;
    }

    public void setDeparturesWeek(Collection<Departure> departuresWeek) {
        this.departuresWeek = departuresWeek;
    }
    
}
