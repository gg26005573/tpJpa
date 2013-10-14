package fr.istic.tpjpa.shared;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Home {
	private Long id;
		
	private List<Heater> heaters;
	
	private Person owner;
	
	private Address address;
	
	public Home() {
		this.heaters = new ArrayList<>();
	}
	
	public Home(Address address) {
		this();
		this.address = address;
		this.address.setHome(this);
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	public Person getOwner() {
		return owner;
	}
	
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	@OneToMany(mappedBy="location", cascade=CascadeType.PERSIST)
	public List<Heater> getHeaters() {
		return heaters;
	}
	
	public void setHeaters(List<Heater> heaters) {
		this.heaters = heaters;
	}
	
	public void addHeater(Heater h){
		heaters.add(h);
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		String res = "Home[";
		for(Heater h : getHeaters()){
			res += h.toString()+", ";
		}
		return res + "";
	}
}
