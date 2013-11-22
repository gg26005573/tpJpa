package fr.istic.tpjpa.shared;

import java.util.List;

public interface HomeItf {

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract Person getOwner();

	public abstract void setOwner(Person owner);

	public abstract List<Heater> getHeaters();

	public abstract Address getAddress();

	public abstract void setAddress(Address address);

}