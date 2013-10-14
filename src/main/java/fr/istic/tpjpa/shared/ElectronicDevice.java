package fr.istic.tpjpa.shared;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ElectronicDevice extends Device{
	
	private Person owner;
	
	public ElectronicDevice() {
	}
	
	public ElectronicDevice(Person owner, String name) {
		super(name);
		this.owner = owner;
	}
	
	@ManyToOne
	@JoinColumn(name="OWNER_ID") 
	public Person getOwner() {
		return owner;
	}
	
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "Electronic device@"+getId();
	}
}
