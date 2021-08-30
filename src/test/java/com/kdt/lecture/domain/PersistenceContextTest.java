package com.kdt.lecture.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Slf4j
@SpringBootTest
public class PersistenceContextTest {

    @Autowired
    CustomerRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        repository.deleteAll();


    }


    @Test
    void 저장() {
        EntityManager em = emf.createEntityManager(); // 1) 엔티티 매니저 생성
        EntityTransaction transaction = em.getTransaction(); // 2) 트랜잭션 획득
        transaction.begin(); // 3) 트랙잰셕 begin

        Customer customer = new Customer(); // 4-1) 비영속
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        em.persist(customer); // 4-2)영속화

        transaction.commit(); // 5) 트랜잭션 commit
    }

    @Test
    void 조회() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        em.persist(customer);

        transaction.commit();

        em.clear(); //영속성 컨텍스트를 초기화 한다.

        Customer entity = em.find(Customer.class, 1L); // DB 에서 조회한다.
        log.info("{} {}", entity.getFirstName(), entity.getLastName());
    }

    @Test
    void 조회_1차캐시_이용() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        em.persist(customer);

        transaction.commit();

        Customer entity = em.find(Customer.class, 1L); // 1차 캐시에서 조회한다.
        log.info("{} {}", entity.getFirstName(), entity.getLastName());
    }

    @Test
    void 수정() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        em.persist(customer);
        transaction.commit();

        transaction.begin();

        Customer entity = em.find(Customer.class, 1L);
        entity.setFirstName("guppy");
        entity.setLastName("hong");

        // em.update(entity) ??
        transaction.commit();
    }

    @Test
    void 삭제() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        em.persist(customer);
        transaction.commit();

        transaction.begin();

        Customer entity = em.find(Customer.class, 1L);
        em.remove(entity);

        transaction.commit();
    }

}
