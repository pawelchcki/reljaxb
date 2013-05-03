/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.mapping;

import ws.chojnacki.timetable.rxmldata.*;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Pawel
 */
@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)

public class Departure {
    @XmlAttribute
    private String legend;

    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(type=Date.class, value=XmlTimeAdapter.class)
    private Date time;

    public Departure() {
    }

    public Departure(Date time) {
        this.time = time;
    }

    public Departure(String time){
        try {
            this.time = DateFormat.getTimeInstance(DateFormat.SHORT).parse(time);
        } catch (ParseException ex) {
            System.err.println("nie marudz ta funkcja tylko do testów być, nie wiesz co to nie uzywaj! :D");
        }
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

}
