/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata.utils;

import ws.chojnacki.timetable.rxmldata.ConfigurationFactoryImpl;
import ws.chojnacki.timetable.rxmldata.EntityFactory;
import ws.chojnacki.timetable.rxmldata.EntityFactoryImpl;

/**
 *
 * @author Pawel
 */
public class PoleTestowe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityFactoryImpl ef = new EntityFactoryImpl();
        ef.setConfigurationFactory(new ConfigurationFactoryImpl());
       // ef.use(clazz);

    }
    

}
