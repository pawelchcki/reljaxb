/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

import java.util.Collection;
import java.util.List;
import ws.chojnacki.timetable.mapping.Line;

/**
 *
 * @author Pawel
 */
public interface EntityFactory {

    public List<IdentifiedEntity> getAllEntitiesList(Class aClass);
    /**
     * uses specified entity,
     *
     * only thing that matters is the entity id
     * if it exist in class's data store then that entity is discarded
     * and specified entity is placed instead.
     * If specified entity is not yet initialized
     * it gets id assigned from availaible pool and then is added to datastore
     * 
     * @param ie entity to use
     */
    public void persist(IdentifiedEntity ie) throws Exception;

    public void remove(IdentifiedEntity ie) throws Exception;

    public void use(Class... a);
    public void load() throws Exception;
    public IdentifiedEntity getEntity(Class clazz, int id);
    //public ResultSet executeQuery(TTQuery query);
    //public IdentifiedEntity[] executeQuery(TTQuery query);
    public Collection<IdentifiedEntity> getAllEntities(Class clazz);
    public void save() throws Exception;

    public ConfigurationFactory getConfigurationFactory();
    public void setConfigurationFactory(ConfigurationFactory configurationFactory);
}
