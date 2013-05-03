/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chojnacki.timetable.rxmldata;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ws.chojnacki.timetable.rxmldata.container.ContainerInterface;
import ws.chojnacki.timetable.mapping.container.Distances;
import ws.chojnacki.timetable.mapping.container.Lines;

import ws.chojnacki.timetable.mapping.container.Stops;

/**
 * Default implementation of persistance manager
 * File names are provided by taking information from resource file mapping
 * the classes to file names
 * Directory is also provided in such configuration file
 * @author Pawel
 */
public class FilePersistanceManager implements PersistanceManager {

    private Map<Class, String> classFileNames;
    private String filePath = "C:\\";

    public FilePersistanceManager() {
        String dupa = "saf";
        /// bla bla bla read from persistance for now we have something else
        //FIXME: THIS ;//
        classFileNames = new HashMap<Class, String>();
        classFileNames.put(Lines.class, "timetable.xml");
        classFileNames.put(Stops.class, "stops.xml");
        //classFileNames.put(Routes.class, "routes.xml");
        classFileNames.put(Distances.class, "distances.xml");


    }

    public void saveElement(ContainerInterface jaxbElement) throws Exception {
        saveElements(jaxbElement);
    }

    public void saveElements(ContainerInterface... jaxbElements) throws Exception {
        for (ContainerInterface element : jaxbElements) {

            JAXBContext linesContext = JAXBContext.newInstance(element.getClass());
            Marshaller m = linesContext.createMarshaller();
            m.setProperty(m.JAXB_FORMATTED_OUTPUT, true);
           

            File file = new File(this.filePath, getFileName(element.getClass()));
            m.setProperty(m.JAXB_FRAGMENT,Boolean.TRUE);
            
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));

            String testStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";

            String appendedString = "<?xml-stylesheet href=\""+
                            getFileName(element.getClass()).replace(".xml", ".xsl")
                            +"\" type=\"text/xsl\"?>\n";

            bf.write(testStr);
            bf.write(appendedString);
            m.marshal(element, bf);
            bf.close();
            
            /*RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0);
            String line;
            long lastPos =  raf.getFilePointer();
            String testStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            String begining = "";
            String appendedString = "\n<?xml-stylesheet href=\""+
                            getFileName(element.getClass()).replace(".xml", ".xsl")
                            +"\" type=\"text/xsl\"?>\n";
            while ((line = raf.readLine())!=null){
                
                int strPos = line.indexOf(testStr);
                
                if (strPos>-1){
                  //  raf.setLength(raf.length()+appendedString.length()+10);
                    raf.seek(lastPos+strPos+testStr.length());
                    raf.seek(0);
                    String rest="";
                    try{
                    rest = raf.readUTF();
                    } catch (EOFException ex){
                        System.err.println(rest);
                    }
                    System.err.println(rest);
                    raf.writeUTF(appendedString);
                    raf.writeUTF(rest);
                    break;
                }

                lastPos = raf.getFilePointer();
            }
            raf.close();*/
        //file.close();
        }
    }


    public String getFileName(Class clazz) {
        return classFileNames.get(clazz);
    }

    public void loadElement(ContainerInterface jaxbElement) throws Exception {
        loadElements(jaxbElement);
    }

    public void loadElements(ContainerInterface... jaxbElements) throws Exception {
        for (ContainerInterface element : jaxbElements) {

            JAXBContext linesContext = JAXBContext.newInstance(element.getClass());
            Unmarshaller unm = linesContext.createUnmarshaller();

            
            File file = new File(this.filePath, getFileName(element.getClass()));
            element.setIdentifiedEntities(
                    ((ContainerInterface)unm.unmarshal(file))
                    .getIdentifiedEntities());
        }


    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
    
    
}
