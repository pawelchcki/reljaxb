/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ws.chojnacki.timetable.rxmldata.annotation.XmlMapping;
import ws.chojnacki.timetable.rxmldata.annotation.XmlStore;


import ws.chojnacki.timetable.rxmldata.refs.ReferingEntity;


/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlStore(container=Routes.class)
public class Route {//extends IdentifiedEntity{
    //@XmlAttribute(required=true)
    @XmlTransient
    private String name;
/**
 * 
 */
    //@XmlTransient
    //@XmlMapping(referedClass=Stop.class, referenceFieldName="refStops")
    //private Collection<Stop> stops;

    @XmlTransient
    @XmlMapping(referedClass=Stop.class, referenceFieldName="refStops")
    private Collection<Stop> stops;
/**
 *
 */
    @XmlElement(name="stop", required=true,type=ReferingEntity.class)
    private Collection<ReferingEntity> refStops;
    

    public Route() {
        stops = new ArrayList<Stop>();
    }
    

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    @Deprecated
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    @Deprecated
    public void setName(String name) {
        this.name = name;
    }
    
    public Collection<Stop> getStops() {
        return stops;
    }

    public void setStops(Collection<Stop> stops) {
        this.stops = stops;
    }

}
