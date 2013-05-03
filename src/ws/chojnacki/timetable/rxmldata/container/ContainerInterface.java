/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chojnacki.timetable.rxmldata.container;

import java.util.Collection;
import ws.chojnacki.timetable.rxmldata.IdentifiedEntity;

/**
 *
 * @author Pawel
 */
public interface ContainerInterface<T extends IdentifiedEntity> {

    public void setIdentifiedEntities(Collection<T> ieCollection);

    public Collection<T> getIdentifiedEntities();

    public void addIdentifiedEntities(T... ies);
}
