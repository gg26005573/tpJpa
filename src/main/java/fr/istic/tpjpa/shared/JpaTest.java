package fr.istic.tpjpa.shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

//		EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//
//		// creating entities
//		try{
//			test.createStuff();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		// persist entity
//
//		tx.commit();

		// run request
		
		test.listDevices();
		
		Long nbPeople = manager.createQuery("SELECT count(p.id) FROM Person p", Long.class).getSingleResult();
		System.out.println("nbPeople == "+nbPeople);
		
		try{
			System.out.println("Trying to get Homer Simpson");
			Person.getPersonsByName("Homer Simpson");
			System.out.println("Homer Simpson gotten without errors");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		nbPeople = manager.createQuery("SELECT count(p.id) FROM Person p", Long.class).getSingleResult();
		System.out.println("nbPeople == "+nbPeople);
		
		try{
			System.out.println("Trying to get Person 1");
			Person.getPersonByID(1L);
			System.out.println("Person 1 gotten without errors");
		}catch(Exception e){
			e.printStackTrace();
		}

		System.out.println(".. done");

		manager.close();
	}
	
	public void createStuff(){
		long nbPeople = manager.createQuery("SELECT count(p.id) FROM Person p", Long.class).getSingleResult();
		System.out.println("nbPeople == "+nbPeople);
		
		if(nbPeople==0){
			// People
			Person homer = new Person("Homer Simpson");
			Person ned = new Person("Ned Flanders");
			Person spider = new Person("Spider Cochon");
			homer.addFriend(ned);
			ned.addFriend(homer);
			homer.addFriend(spider);
			spider.addFriend(homer);
			manager.persist(homer);
			manager.persist(ned);
			manager.persist(spider);
			

			// Homes & Address
			Address homerA = new Address("742 Evergreen Terrace, Springfield, North Takoma");
			Home homerH = new Home(homerA);
			homerH.setOwner(homer);
			homer.addHome(homerH);
			manager.persist(homerA);
			manager.persist(homerH);
			
			Address nedA = new Address("744 Evergreen Terrace, Springfield, North Takoma");
			Home nedH = new Home(nedA);
			nedH.setOwner(ned);
			ned.addHome(nedH);
			manager.persist(nedA);
			manager.persist(nedH);
			
			// Devices
			ElectronicDevice nedSmartphone = new ElectronicDevice(ned, "Ned's smartphone");
			ned.addDevice(nedSmartphone);
			ElectronicDevice homerSmartphone = new ElectronicDevice(homer, "Homer's smartphone");
			homer.addDevice(homerSmartphone);
			manager.persist(nedSmartphone);
			manager.persist(homerSmartphone);
			
			// Heaters
			Heater homerBathroom = new Heater(homerH, "Homer's bathroom's heater");
			homerH.addHeater(homerBathroom);
			Heater homerOven = new Heater(homerH, "Homer's oven");
			homerH.addHeater(homerOven);
			Heater nedLivingroom = new Heater(nedH, "Ned's living room's heater");
			nedH.addHeater(nedLivingroom);
			manager.persist(homerBathroom);
			manager.persist(homerOven);
			manager.persist(nedLivingroom);
		}
		
		nbPeople = manager.createQuery("SELECT count(p.id) FROM Person p", Long.class).getSingleResult();
		System.out.println("nbPeople == "+nbPeople);
	}

	private void listDevices(){
		List<String> results = manager.createQuery("SELECT e.name FROM ElectronicDevice AS e", String.class).getResultList();
		System.out.println(results);
	}
	
}
