/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chojnacki.timetable.rxmldata;

import ws.chojnacki.timetable.mapping.Line;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.chojnacki.timetable.rxmldata.IdentifiedEntity;
import ws.chojnacki.timetable.rxmldata.annotation.XmlMapping;
import ws.chojnacki.timetable.rxmldata.annotation.XmlOneToMany;
import ws.chojnacki.timetable.rxmldata.annotation.XmlStore;
import ws.chojnacki.timetable.rxmldata.container.ContainerInterface;
import ws.chojnacki.timetable.rxmldata.refs.ReferingEntity;

/**
 *
 * @author Pawel
 */
public class EntityFactoryImpl implements EntityFactory {

    //private classTree
    public EntityFactoryImpl() {
        classMap = new HashMap<Class, Map<Integer, IdentifiedEntity>>();
        sequences = new HashMap<Class, Integer>();
    }
    private Map<Class, Map<Integer, IdentifiedEntity>> classMap;
    private Map<Class, Integer> sequences;
    private ConfigurationFactory configurationFactory = new ConfigurationFactoryImpl();
    private SequenceManagerFactory sequenceManagerFactory = new InMemorySequenceManagerFactory();

    public void persist(IdentifiedEntity ie) throws Exception {

        Map currentStore = classMap.get(ie.getClass());
        //Integer sequence = sequences.get(ie.getClass());
        SequenceManager sm = sequenceManagerFactory.getSequenceManager(ie.getClass());
        ie.setId((int) sm.next()); //FIXME: convert to long or sumting        
        currentStore.put(ie.getId(), ie);
    //loadReferenceWithIdentity(ie);

    }

    public void remove(IdentifiedEntity ie) throws Exception {

        Map currentStore = classMap.get(ie.getClass());
        //Integer sequence = sequences.get(ie.getClass());
        //SequenceManager sm = sequenceManagerFactory.getSequenceManager(ie.getClass());
        //ie.setId((int) sm.next()); //FIXME: convert to long or sumting
        //currentStore.put(ie.getId(), ie);
        //loadReferenceWithIdentity(ie);
        currentStore.remove(ie.getId());


    }
    //public void

    public void use(Class... clazz) {
        for (Class cl : clazz) {
            if (!cl.isAnnotationPresent(XmlStore.class)) {
                throw new RuntimeException("class " + clazz.getClass().getName() + " does not contain XmlStore annotation");
            }
            //FIXME: Add custom runtime exception class

            if (!classMap.containsKey(cl)) {
                // classMap.put(cl, null);
                classMap.put(cl, new HashMap<Integer, IdentifiedEntity>());
                sequences.put(cl, 0); //FIXME: deprecated
                sequenceManagerFactory.register(cl);
            //classMap = new ArrayList<IdentifiedEntity>();
            }
        }
    }

    public void load() throws Exception {
        PersistanceManager pmInstance = getConfigurationFactory().getPersistanceManager();
        for (Class clazz : classMap.keySet()) {
            ContainerInterface containerInstance = getContainerClassInstance(clazz);

            try {

                //pmInstance.saveElement(containerInstance);
                pmInstance.loadElement(containerInstance);
            } catch (Exception ignore) {
                throw ignore;
            }
            classMap.get(clazz).clear();
            if (containerInstance.getIdentifiedEntities()!=null)

            for (Object object : containerInstance.getIdentifiedEntities()) {
                IdentifiedEntity ie = (IdentifiedEntity) object;
                classMap.get(clazz).put(ie.getId(), ie);
                //System.err.println("33"+ie.getId()+ie.getClass().getName());
                if (ie.getClass().isAssignableFrom(Line.class)) {
                    Line line = (Line) ie;
                //System.err.println(""+line.getName());

                }
                sequenceManagerFactory.getSequenceManager(clazz).adjust(ie.getId());
            }
        }
        for (Map<Integer, IdentifiedEntity> map : classMap.values()) {
            for (IdentifiedEntity ie : map.values()) {
                loadIdentityFromReference(ie);

            }

        }
    }

    public IdentifiedEntity getEntity(Class clazz, int id) {
        //System.err.println("a"+id+clazz.getName());
        return classMap.get(clazz).get(id);
    }

    private Collection<ReflectionAssignmentContainer> recurseForMapping(Object obj, Class annotationClass) {

        ArrayList<ReflectionAssignmentContainer> retList = new ArrayList<ReflectionAssignmentContainer>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            //  //System.err.println("dupa" + field.getName());
            if (field.isAnnotationPresent(annotationClass)) {
                XmlMapping annot = field.getAnnotation(XmlMapping.class);
                try {
                    Field refField = obj.getClass().getDeclaredField(annot.referenceFieldName());
                    retList.add(new ReflectionAssignmentContainer(obj, field, refField));
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException("Refered entity not found " + ex.getMessage());
                } catch (SecurityException ex) {
                    throw new RuntimeException("Coś nie tak: " + ex.getMessage());
                }


            } else if (field.isAnnotationPresent(XmlElement.class) ||
                    field.isAnnotationPresent(XmlAttribute.class)) {// if (field.isAnnotationPresent(XmlRootElement.class)) {
                // //System.err.println("ddupaa");
                try {
                    boolean wasAccessible = field.isAccessible();
                    field.setAccessible(true);
                    Object recObj = field.get(obj);
                    field.setAccessible(wasAccessible);
                    if (recObj == null) {
                        continue;
                    }
                    retList.addAll(recurseForMapping(recObj, annotationClass));
                } catch (IllegalArgumentException ex) {
                    throw new RuntimeException("Coś nie tak: " + ex.getMessage());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException("Coś nie tak: " + ex.getMessage());
                }
            }


        }

        return retList;
    }

    private void loadReferenceFromIdentity(Object obj) {

        for (ReflectionAssignmentContainer objFF : recurseForMapping(obj, XmlMapping.class)) {
            try {
                // //System.err.println("dupa");
                objFF.refered.setAccessible(true);
                objFF.reference.setAccessible(true);
                if (objFF.reference.getType().isPrimitive()) {
                    IdentifiedEntity ie = (IdentifiedEntity) objFF.refered.get(objFF.object);
                    objFF.reference.set(objFF.object, ie.getId());
                } else if (objFF.reference.getType().isAssignableFrom(Collection.class)) {
                    Collection<IdentifiedEntity> ie = (Collection<IdentifiedEntity>) objFF.refered.get(objFF.object);
                    Collection<ReferingEntity> rf;//= (Collection<ReferingEntity>) objFF.refered.get(obj);
                    rf = new ArrayList<ReferingEntity>();
                    for (IdentifiedEntity ideE : ie) {
                        rf.add(new ReferingEntity(ideE.getId()));
                    }
                    objFF.reference.set(objFF.object, rf);
                } else {
                    IdentifiedEntity ie = (IdentifiedEntity) objFF.refered.get(objFF.object);
                    objFF.reference.set(objFF.object, new ReferingEntity(ie.getId()));
                }

            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Coś nie tak: " + ex.getMessage());
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Coś nie tak: " + ex.getMessage());
            }
        }
    }

    private void loadIdentityFromReference(Object obj) {

        for (ReflectionAssignmentContainer ref : recurseForMapping(obj, XmlMapping.class)) {
            try {
                // //System.err.println("dupa");
                ref.refered.setAccessible(true);
                ref.reference.setAccessible(true);
                if (ref.reference.getType().isPrimitive()) {
                    //System.err.println("Dupa2");
                    Integer refVal = ((Long) ref.reference.get(ref.object)).intValue(); //FIXME:!!!!
                    IdentifiedEntity ie = getEntity(ref.refered.getType(), refVal);
                    //IdentifiedEntity ie = (IdentifiedEntity) ref.refered.get(ref.object);
                    ref.refered.set(ref.object, ie);

                } else if (ref.reference.getType().isAssignableFrom(Collection.class)) {
                    //System.err.println("Dupa");
                    Collection<IdentifiedEntity> ie;//(Collection<IdentifiedEntity>) ref.refered.get(ref.object);
                    Collection<ReferingEntity> rf = (Collection<ReferingEntity>) ref.reference.get(ref.object);
                    ie = new ArrayList<IdentifiedEntity>();
                    if (rf != null) {
                        for (ReferingEntity referingEntity : rf) {
                            ie.add(getEntity(
                                    ((XmlMapping) ref.refered.getAnnotation(XmlMapping.class)).referedClass(), referingEntity.getId()));
                        }
                    }

                    ref.refered.set(ref.object, ie);
                } else {

                    ReferingEntity rf = (ReferingEntity) ref.reference.get(ref.object);
                    ref.refered.set(ref.object, getEntity(ref.object.getClass(), rf.getId()));
                    IdentifiedEntity ie = (IdentifiedEntity) ref.refered.get(ref.object);
                    ref.reference.set(ref.object, new ReferingEntity(ie.getId()));
                }

            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Coś nie Ill tak: " + ex.getMessage());
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Coś nie tak: " + ex.getMessage());
            }

        }
    }

    private ContainerInterface getContainerClassInstance(Class clazz) {
        ContainerInterface ci = null;
        try {
            ci = (ContainerInterface) ((XmlStore) clazz.getAnnotation(XmlStore.class)).container().newInstance();
        } catch (InstantiationException ex) {
            //FIXME: Woot ?
            Logger.getLogger(EntityFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EntityFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci;
    }

    public void save() throws Exception {
        //FIXME: Specify exception
        PersistanceManager pmInstance = getConfigurationFactory().getPersistanceManager();
        // Iterate over all registered classes
        for (Class clazz : classMap.keySet()) {
            ContainerInterface containerInstance = getContainerClassInstance(clazz);
            // Fill temporary container used for saving in single files under common root
            // with values, FIXME: May be slow
            containerInstance.setIdentifiedEntities(classMap.get(clazz).values());
            for (IdentifiedEntity ie : classMap.get(clazz).values()) {
                loadReferenceFromIdentity(ie);
            }
            //FIXME: wiill be slow spid it up dude!

            try {
                pmInstance.saveElement(containerInstance);
            } catch (Exception ignore) {
                throw ignore;
//FIXME: shit
            }
        }
    //pmInstance.saveElements(elems);
    }

    public ConfigurationFactory getConfigurationFactory() {
        return configurationFactory;
    }

    public void setConfigurationFactory(ConfigurationFactory configurationFactory) {
        this.configurationFactory = configurationFactory;
    }

    @Deprecated
    public ResultSet executeQuery(TTQuery query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<IdentifiedEntity> getAllEntities(Class clazz) {
        //Collection<IdentifiedEntity> col = classMap.get(clazz).values();
        //return (IdentifiedEntity[]) col.toArray(new IdentifiedEntity[col.size()]);
        return classMap.get(clazz).values();
    }

    public List<IdentifiedEntity> getAllEntitiesList(Class aClass) {
        ArrayList<IdentifiedEntity> aList = new ArrayList<IdentifiedEntity>(getAllEntities(aClass));
        return aList;

    }

    class ReflectionAssignmentContainer {

        Object object;
        Field reference;
        Field refered;

        public ReflectionAssignmentContainer(Object object, Field annoted, Field refered) {
            this.object = object;
            this.reference = refered;
            this.refered = annoted;
        }
    }
    //private void addClass
}
