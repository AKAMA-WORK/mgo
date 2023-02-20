package voyage.mgo;

import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;

import java.util.Collections;
import java.util.List;

public class AppJpaEntityProvider implements JpaEntityProvider {
    public static final String PROVIDER_ID = "mgo-entity-provider";
    private static final List<Class<?>> ENTITIES = Collections.singletonList(PersonEntity.class);

    @Override
    public List<Class<?>> getEntities() {
        return ENTITIES;
    }

    @Override
    public String getChangelogLocation() {
        return  "META-INF/db/changeLog.xml";
    }

    @Override
    public String getFactoryId() {
        return PROVIDER_ID;
    }

    @Override
    public void close() {

    }
}
