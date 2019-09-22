package com.example.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.tool.schema.Action;

import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceProvider;
import java.util.List;
import java.util.Map;

public class EntityManagerProvider {

    private static EntityManager entityManager;

    private EntityManagerProvider() {
    }

    public static EntityManager get() {
        if (entityManager == null) {
            init();
        }
        return entityManager;
    }

    private static void init() {
        PersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        var emf = persistenceProvider.createEntityManagerFactory("em", Map.of(
                AvailableSettings.HBM2DDL_DATABASE_ACTION, Action.CREATE_DROP,
                AvailableSettings.CLASSLOADERS, List.of(EntityManagerProvider.class.getClassLoader()),
                AvailableSettings.JPA_JDBC_DRIVER, "org.h2.Driver",
                AvailableSettings.JPA_JDBC_URL, "jdbc:h2:mem:test",
                AvailableSettings.JPA_JDBC_USER, "sa",
                AvailableSettings.JPA_JDBC_PASSWORD, ""
        ));
        entityManager = emf.createEntityManager();
    }

}
