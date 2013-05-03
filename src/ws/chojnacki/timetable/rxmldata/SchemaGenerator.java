/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

import java.io.File;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Pawel
 */
public interface SchemaGenerator {
//FIXME: DESIGN AND IMPLEMENT!!!rin
    public File schemaToFile(JAXBElement element);
    public String schemaToString(JAXBElement element);
}
