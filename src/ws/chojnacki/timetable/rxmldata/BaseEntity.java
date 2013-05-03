/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public interface BaseEntity {
    //private EntityFactory ef;
    public void setEntityFactory(EntityFactory ef);
    public EntityFactory getEntityFactory();
}
