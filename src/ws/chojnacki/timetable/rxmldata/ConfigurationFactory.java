/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public interface ConfigurationFactory {
    /**
     * Persistance manager takes control of saving and restoring jaxb objects
     * @return PersistanceManager object providing what the name states
     */
    public PersistanceManager getPersistanceManager();
    public SchemaGenerator getSchemaGenerator();
    // interface will be modified furrther
}
