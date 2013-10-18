package fr.istic.tpjpa.shared;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Entity
public class Person implements PersonItf {
	private Long id;
	
	private String name;
	
	private List<Home> homes;
	
	private List<Person> friends;
	
	private List<ElectronicDevice> devices;
	
	public Person() {
		friends = new ArrayList<>();
		homes = new ArrayList<>();
		devices = new ArrayList<>();
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}
	
	public static List<Person> getPersonsByName(String name){
		EntityManager manager = EntityManagerFactory.getInstance();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("name").in(name));
		return manager.createQuery(query).getResultList();
	}
	
	public static Person getPersonByID(Long id){
		EntityManager manager = EntityManagerFactory.getInstance();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("id").in(id));
		Person res = manager.createQuery(query).getSingleResult();
		System.err.println("*** *** Person="+res+" *** ***");
		return res;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
	public List<Home> getHomes() {
		return homes;
	}
	
	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
	
	public void addHome(Home h){
		homes.add(h);
	}
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List<Person> getFriends() {
		return friends;
	}
	
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Person f){
		friends.add(f);
	}
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
	public List<ElectronicDevice> getDevices() {
		return devices;
	}
	
	public void setDevices(List<ElectronicDevice> devices) {
		this.devices = devices;
	}
	
	public void addDevice(ElectronicDevice ed){
		devices.add(ed);
	}
	
	@Transient
	public int getNbFriends() {
		return friends.size();
	}
	
	public void setNbFriends(int nbFriends){
		// nop
	}
	
	
	@Override
	public String toString() {
		return name;
	}
}
