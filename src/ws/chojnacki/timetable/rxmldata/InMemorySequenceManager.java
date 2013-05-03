/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

/**
 *
 * @author Pawel
 */
public class InMemorySequenceManager implements SequenceManager {
    long sequence=0;
    public void adjust(long value) {
        if (value>sequence)
            sequence = value;
        sequence++;
    }

    public void change(long value) {
        sequence = value;
    }

    public long next() {
        return sequence++;
    }

    public long current() {
        return sequence;
    }

}
