package com.example.model;

import com.example.configuration.EntityManagerProvider;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private final EntityManager em = EntityManagerProvider.get();

    @Test
    void non_updatable_fields_should_be_inserted() {
        em.getTransaction().begin();
        final Model model = new Model();
        em.persist(model);
        final Long id = model.getId();
        model.setWorksFine("foo");
        model.setIncorrect("bar");
        assertEquals(0, ((SessionImpl)em).getActionQueue().numberOfUpdates());
        assertEquals(1, ((SessionImpl)em).getActionQueue().numberOfInsertions());
        em.flush();
        assertFalse(((SessionImpl) em).isDirty(), "Context should be dirty after a flush");
        final Object[] singleResult = (Object[]) em.createNativeQuery(
                "SELECT works_fine, incorrect FROM MODEL WHERE id = " + id).getSingleResult();
        assertAll(() -> assertTrue(singleResult[0] != null, "works_fine is inserted"),
                  () -> assertTrue(singleResult[1] != null, "incorrect should be there too!"));
        em.getTransaction().rollback();
    }
}