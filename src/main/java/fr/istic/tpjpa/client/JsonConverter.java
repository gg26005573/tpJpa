package fr.istic.tpjpa.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fr.istic.tpjpa.shared.Home;
import fr.istic.tpjpa.shared.HomeItf;
import fr.istic.tpjpa.shared.Person;
import fr.istic.tpjpa.shared.PersonItf;

public class JsonConverter {

	private JsonConverter() {
	}

	private static JsonConverter instance = new JsonConverter();

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
	
	public HomeItf makeHome() {
		// Construct the AutoBean
		AutoBean<HomeItf> h = factory.home();

		// Return the Home interface shim
		return h.as();
	}

	String serializeToJson(Person p) {
		// Retrieve the AutoBean controller
		AutoBean<PersonItf> bean = AutoBeanUtils.getAutoBean(p);

		return AutoBeanCodex.encode(bean).getPayload();
	}
	
	String serializeToJson(Home h) {
		// Retrieve the AutoBean controller
		AutoBean<HomeItf> bean = AutoBeanUtils.getAutoBean(h);

		return AutoBeanCodex.encode(bean).getPayload();
	}

	PersonItf deserializePersonFromJson(String json) {
		AutoBean<PersonItf> bean = AutoBeanCodex.decode(factory, PersonItf.class,
				json);
		return bean.as();
	}
	
	HomeItf deserializeHomeFromJson(String json) {
		AutoBean<HomeItf> bean = AutoBeanCodex.decode(factory, HomeItf.class,
				json);
		return bean.as();
	}

	public static JsonConverter getInstance() {
		return instance;
	}
}
