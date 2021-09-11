package com.kdt.lecture.domain.order;


import com.kdt.lecture.domain.multikey.Parent;
import com.kdt.lecture.domain.multikey.Parent2;
import com.kdt.lecture.domain.multikey.ParentId;
import com.kdt.lecture.domain.multikey.ParentId2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Slf4j
@SpringBootTest
public class MappingTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void multi_key_test() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Parent parent = new Parent();
        parent.setId1("id1");
        parent.setId2("id2");
        entityManager.persist(parent);

        transaction.commit();

        Parent entity = entityManager.find(Parent.class, new ParentId("id1", "id2"));
        log.info("{}, {}", entity.getId1(), entity.getId2());
    }

    @Test
    void multi_key_test_embedded() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Parent2 parent = new Parent2();
        parent.setId(new ParentId2("id1", "id2"));
        entityManager.persist(parent);

        transaction.commit();

        Parent2 entity = entityManager.find(Parent2.class, new ParentId2("id1", "id2"));
        log.info("{}, {}", entity.getId().getId1(), entity.getId().getId2());
    }
}
