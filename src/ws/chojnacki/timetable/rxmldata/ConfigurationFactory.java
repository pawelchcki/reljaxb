package ws.chojnacki.timetable.rxmldata;

public interface ConfigurationFactory {
    /**
     * Persistence manager takes control of saving and restoring jaxb objects
     * @return PersistanceManager object providing what the name states
     */
    public PersistanceManager getPersistanceManager();
    public SchemaGenerator getSchemaGenerator();
    // interface will be modified further
}
