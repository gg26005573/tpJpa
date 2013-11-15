package fr.istic.tpjpa.shared;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Heater extends Device{
	
	// The ID is inherited
	
	private Home location;
	
	public Heater() {
	}
	
	public Heater(Home location, String name){
		super(name);
		this.location = location;
	}
	
	@ManyToOne
	@JsonIgnore
	public Home getLocation() {
		return location;
	}
	
	public void setLocation(Home location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Heater@" + getId();
	}
}
