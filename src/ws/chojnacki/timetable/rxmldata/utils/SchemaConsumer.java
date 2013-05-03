/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata.utils;

import java.io.IOException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;

/**
 *
 * @author Pawel
 */
public class SchemaConsumer extends SchemaOutputResolver{

    @Override
    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
        Result res = new Result() {

            public void setSystemId(String systemId) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public String getSystemId() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        
        return res;
    }

}
