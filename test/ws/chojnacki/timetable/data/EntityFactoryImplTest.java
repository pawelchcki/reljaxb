/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chojnacki.timetable.data;

import java.lang.reflect.Method;
import ws.chojnacki.timetable.rxmldata.ConfigurationFactoryImpl;
import ws.chojnacki.timetable.rxmldata.IdentifiedEntity;
import ws.chojnacki.timetable.rxmldata.EntityFactoryImpl;
import ws.chojnacki.timetable.mapping.Departure;
import ws.chojnacki.timetable.mapping.Route;
import ws.chojnacki.timetable.mapping.Line;
import ws.chojnacki.timetable.mapping.Stop;
import ws.chojnacki.timetable.mapping.GeoPoint;
import ws.chojnacki.timetable.mapping.Distance;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ws.chojnacki.timetable.mapping.container.Lines;
import static org.junit.Assert.*;

/**
 *
 * @author Pawel
 */
public class EntityFactoryImplTest {

    public EntityFactoryImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    public void testEntityFactoryImplWrite() throws Exception {
        EntityFactoryImpl instance = new EntityFactoryImpl();
        instance.setConfigurationFactory(new ConfigurationFactoryImpl());

        instance.use(Line.class);

        instance.persist(new Line("raz"));
        instance.persist(new Line("dwa"));

    // instance.commit();
    }
    @Test
    public void testEntityFactoryImplRead() throws Exception {

        EntityFactoryImpl instance = new EntityFactoryImpl();

        instance.use(Line.class, Stop.class, Distance.class);

        instance.load();
      //  instance.getEntity(Line.class, id)
        for (IdentifiedEntity ie : instance.getAllEntities(Line.class)) {
            Line line = (Line) ie;
            System.err.println(""+ie.getId()+ie.getClass().getName());
         //   System.err.println(""+line.getName()+line.getRoute().getStops().iterator().next().getName());
        }
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        instance.persist(new Line("cztery"));
        instance.persist(new Line("Dwiadziescia"));
        for (Method method : instance.getClass().getDeclaredMethods()) {
            System.err.println(""+method.getName());
        }

        //((Line)instance.getAllEntities(Line.class).iterator().next()).
        instance.save();
        
    }
    
    
    public void testEntityRefs() throws Exception {
        EntityFactoryImpl instance = new EntityFactoryImpl();
        instance.setConfigurationFactory(new ConfigurationFactoryImpl());
        instance.use(Stop.class, Line.class, Distance.class);
        Stop stopa = new Stop();
        Stop stopb = new Stop();
        stopa.setName("Kilinczszczakow");
        stopa.setLocation(new GeoPoint(0.32, 0.14));
        stopb.setName("Okulickiego");
        instance.persist(stopa);
        instance.persist(stopb);
        Line line = new Line("1");
        Route route = new Route();
        route.getStops().add(stopa);
        route.getStops().add(stopb);
        line.setRoute(route);
        line.setDeparturesWeek(new ArrayList<Departure>());
        line.getDeparturesWeek().add(new Departure("12:44"));
        Date date = new Date();
        date.setHours(12);
        line.getDeparturesWeek().add(new Departure(date));
        instance.persist(new Distance(stopa, stopb, 2, 1));
        
        instance.persist(line);
        instance.save();
    }
    
    
    public void testEntityRefsLoad() throws Exception {
        EntityFactoryImpl instance = new EntityFactoryImpl();
        instance.setConfigurationFactory(new ConfigurationFactoryImpl());
        //instance.use(Stop.class, Route.class);
        instance.load();
       // Route route = (Route) instance.getEntity(Route.class, 0);
       

    }

}