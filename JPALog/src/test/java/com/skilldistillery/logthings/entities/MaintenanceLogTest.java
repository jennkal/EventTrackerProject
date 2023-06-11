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

class MaintenanceLogTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MaintenanceLog mainLog;

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
		mainLog = em.find(MaintenanceLog.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mainLog = null;
	}

	@Test
	void test_Activity_mapping() {
		assertNotNull(mainLog);
		assertEquals("Truck and generator maintenance.", mainLog.getDescription());
	}
}
