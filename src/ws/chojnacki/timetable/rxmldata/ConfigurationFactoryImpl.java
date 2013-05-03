/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public class ConfigurationFactoryImpl implements ConfigurationFactory{
    private PersistanceManager persistanceManager;
    public PersistanceManager getPersistanceManager() {
        if (persistanceManager==null)
            persistanceManager = new FilePersistanceManager();
        return this.persistanceManager;
    }

    public SchemaGenerator getSchemaGenerator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPersistanceManager(PersistanceManager persistanceManager) {
        this.persistanceManager = persistanceManager;
    }

}
