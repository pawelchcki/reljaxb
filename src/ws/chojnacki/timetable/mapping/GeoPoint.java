/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
public class GeoPoint {
    @XmlAttribute(required=true)
    private Double lattitude;
    @XmlAttribute(required=true)
    private Double longitude;
    @XmlTransient
    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }
    @XmlTransient
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public GeoPoint(Double lattitude, Double longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public GeoPoint() {
    }
    
}
