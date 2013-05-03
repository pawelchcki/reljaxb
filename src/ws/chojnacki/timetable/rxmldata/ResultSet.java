/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
@Deprecated
public interface ResultSet extends Iterable {
    public Class[] getColumnClasses();
    public Class getColumnClass(int rowNo);
    public int getCount();
    public int getRowList();
}
