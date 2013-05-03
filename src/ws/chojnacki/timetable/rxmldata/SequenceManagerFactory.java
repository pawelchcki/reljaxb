/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public interface SequenceManagerFactory {
    public SequenceManager register(Class clazz);
    public SequenceManager getSequenceManager(Class clazz);
    public void setSequenceManagerType(SequenceManager mngr);
}
