package fr.istic.tpjpa.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fr.istic.tpjpa.shared.Person;
import fr.istic.tpjpa.shared.PersonItf;

public class PersonJsonConverter {

	private PersonJsonConverter() {
	}

	private static PersonJsonConverter instance = new PersonJsonConverter();

	// Instantiate the factory
	fr.istic.tpjpa.shared.MyFactory factory = GWT
			.create(fr.istic.tpjpa.shared.MyFactory.class);

	// In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

	public PersonItf makePerson() {
		// Construct the AutoBean
		AutoBean<PersonItf> p = factory.person();

		// Return the Person interface shim
		return p.as();
	}

	String serializeToJson(Person p) {
		// Retrieve the AutoBean controller
		AutoBean<PersonItf> bean = AutoBeanUtils.getAutoBean(p);

		return AutoBeanCodex.encode(bean).getPayload();
	}

	PersonItf deserializePersonFromJson(String json) {
		AutoBean<PersonItf> bean = AutoBeanCodex.decode(factory, PersonItf.class,
				json);
		return bean.as();
	}

	public static PersonJsonConverter getInstance() {
		return instance;
	}
}
