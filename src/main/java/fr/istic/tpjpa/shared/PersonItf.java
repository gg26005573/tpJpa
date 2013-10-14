package fr.istic.tpjpa.shared;

import java.util.List;

public interface PersonItf {
	public String getName();
	public Long getId();
	public void setId(Long id);
	public List<Person> getFriends();
	public List<ElectronicDevice> getDevices();
}
