package fr.istic.tpjpa.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Address {
	private Long id;
	
	private String fullAddress;
	
	private Home home;
	
	public Address() {
	}
	
	public Address(String ad) {
		fullAddress = ad;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="address", nullable=false)
	public String getFullAddress() {
		return fullAddress;
	}
	
	public void setFullAddress(String address) {
		this.fullAddress = address;
	}
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="address")
	public Home getHome() {
		return home;
	}
	
	public void setHome(Home home) {
		this.home = home;
	}
	
}
