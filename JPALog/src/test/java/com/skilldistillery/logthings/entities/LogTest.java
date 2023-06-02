package com.skilldistillery.logthings.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Log log;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPALog");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		log = em.find(Log.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		log = null;
	}

	@Test
	void test_Log_mapping() {
		assertNotNull(log);
		assertEquals("Glacier", log.getTrip());
	}
}
