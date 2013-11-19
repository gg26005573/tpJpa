package fr.istic.tpjpa.client;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.istic.tpjpa.shared.ElectronicDevice;
import fr.istic.tpjpa.shared.Person;
import fr.istic.tpjpa.shared.PersonItf;

public class Formater {

	public static void displayPerson(RootPanel panel, PersonItf p){
		panel.clear();
		
		VerticalPanel vp = new VerticalPanel();
		panel.add(vp);
		vp.add(new Label("name : "+p.getName()));
		vp.add(new Label("ID : "+p.getId()));
		List<ElectronicDevice> devices = p.getDevices();
		if(!devices.isEmpty())
			vp.add(new Label("Devices : "));
		for(ElectronicDevice d : devices){
			vp.add(new Label("  - "+d.getName()+" (ID="+d.getId()+")"));
		}
		List<Person> friends = p.getFriends();
		if(!friends.isEmpty())
			vp.add(new Label("Friends : "));
		for(Person f : friends){
			vp.add(new Label("  - "+f.getName()+" (ID="+f.getId()+")"));
		}
	}
}
