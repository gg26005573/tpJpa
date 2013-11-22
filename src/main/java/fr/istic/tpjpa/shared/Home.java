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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Home implements HomeItf {
	private Long id;
		
	private List<Heater> heaters;
	
	private Person owner;
	
	private Address address;
	
	public Home() {
		this.heaters = new ArrayList<Heater>();
	}
	
	public Home(Address address) {
		this();
		this.address = address;
		this.address.setHome(this);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#getId()
	 */
	@Override
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#setId(java.lang.Long)
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#getOwner()
	 */
	@Override
	@ManyToOne
	@JsonIgnore
	public Person getOwner() {
		return owner;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#setOwner(fr.istic.tpjpa.shared.Person)
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#getHeaters()
	 */
	@Override
	@OneToMany(mappedBy="location", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	public List<Heater> getHeaters() {
		return heaters;
	}
	
	public void setHeaters(List<Heater> heaters) {
		this.heaters = heaters;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#addHeater(fr.istic.tpjpa.shared.Heater)
	 */
	public void addHeater(Heater h){
		heaters.add(h);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#getAddress()
	 */
	@Override
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	public Address getAddress() {
		return address;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.shared.HomeItf#setAddress(fr.istic.tpjpa.shared.Address)
	 */
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
