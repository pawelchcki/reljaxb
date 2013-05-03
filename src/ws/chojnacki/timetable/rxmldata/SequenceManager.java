/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public interface SequenceManager {
    public void adjust(long value);
    public void change(long value);
    public long next();
    public long current();
}
