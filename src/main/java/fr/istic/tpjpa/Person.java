package fr.istic.tpjpa;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Person {
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
