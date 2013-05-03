/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;


import javax.xml.bind.JAXBException;
import ws.chojnacki.timetable.rxmldata.container.ContainerInterface;

/**
 *
 * @author Pawel
 */
public interface PersistanceManager {
    public void saveElement(ContainerInterface jaxbElement) throws Exception;
    public void saveElements(ContainerInterface... jaxbElements) throws Exception;
    public void loadElement(ContainerInterface jaxbElement) throws Exception;
    public void loadElements(ContainerInterface... jaxbElements) throws Exception;

    //public String[] generateFileNames(Class... classes);
    /**
     *  This method returns filename generated or matched
     * for specified class
     * Default implementaation should or might be
     * To either provide only filenames mapped by some external configuration
     * or generate them according to clazz.<b>getName()</b>  with chcecking for
     * duplicates and taking measures to counter them in preferably reproductible and
     * deterministic manner.
     *
     * @param clazz
     * @return
     */
    public String getFileName(Class clazz);
}
