/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pawel
 */
public class InMemorySequenceManagerFactory implements SequenceManagerFactory {
    
    private Map<Class, SequenceManager> smMap = new HashMap<Class, SequenceManager>();

    public SequenceManager register(Class clazz) {
        if (smMap.containsKey(clazz))
            throw new RuntimeException("class already registered in sequence factory"); //FIXME: custom runtime exception
        smMap.put(clazz, new InMemorySequenceManager()); //FIXME: refactor to inner
        return smMap.get(clazz);
    }


    public SequenceManager getSequenceManager(Class clazz) {
        return smMap.get(clazz);
    }

    public void setSequenceManagerType(SequenceManager mngr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
