package fr.istic.tpjpa.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.istic.tpjpa.shared.ElectronicDevice;
import fr.istic.tpjpa.shared.Person;
import fr.istic.tpjpa.shared.PersonItf;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TpJpaGWTEntryPoint implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Make a command that we will execute from all leaves.
		Command cmd = new Command() {
			public void execute() {
				hideMario();
				Window.alert("You selected a menu item!");
			}
		};

		Command searchPersonCmd = new Command() {
			public void execute() {
				hideMario();
				displayPersonSearch();
			}
		};

		Command createPersonCmd = new Command() {
			public void execute() {
				hideMario();
				displayPersonCreate();
			}
		};

		// Make some sub-menus that we will cascade from the top menu.
		MenuBar searchMenu = new MenuBar(true);
		searchMenu.addItem("Person", searchPersonCmd);
		searchMenu.addItem("Home", cmd);

		MenuBar updateMenu = new MenuBar(true);
		updateMenu.addItem("Person", createPersonCmd);
		updateMenu.addItem("Home", cmd);

		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Search", searchMenu);
		menu.addItem("Update", updateMenu);

		// Add it to the root panel.
		RootPanel.get("gwtMenu").add(menu);

	}

	public void displayPersonSearch() {
		RootPanel panel = RootPanel.get("gwtProcess");

		panel.clear();

		VerticalPanel vp = new VerticalPanel();
		panel.add(vp);

		// Search by ID

		HorizontalPanel byID = new HorizontalPanel();
		vp.add(byID);

		byID.add(new Label("ID : "));
		final TextBox idBox = new TextBox();
		idBox.setValue("");
		byID.add(idBox);

		Button buttonID = new Button();
		byID.add(buttonID);
		buttonID.setText("Search by ID");
		buttonID.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchPersonById(idBox.getText());
			}
		});

		// Search by name

		HorizontalPanel byName = new HorizontalPanel();
		vp.add(byName);

		byName.add(new Label("Exact name : "));
		final TextBox nameBox = new TextBox();
		nameBox.setValue("");
		byName.add(nameBox);

		Button buttonName = new Button();
		byName.add(buttonName);
		buttonName.setText("Search by Name");
		buttonName.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchPersonByName(nameBox.getText());
			}
		});
	}

	public void displayPersonCreate() {
		RootPanel panel = RootPanel.get("gwtProcess");

		panel.clear();

		VerticalPanel vp = new VerticalPanel();
		panel.add(vp);

		// Name

		HorizontalPanel nameP = new HorizontalPanel();
		vp.add(nameP);

		nameP.add(new Label("Name : "));
		final TextBox nameBox = new TextBox();
		nameBox.setValue("");
		nameP.add(nameBox);

		// Address

		HorizontalPanel addressP = new HorizontalPanel();
		vp.add(addressP);

		addressP.add(new Label("Adress : "));
		final TextBox addressBox = new TextBox();
		addressBox.setValue("");
		addressP.add(addressBox);

		// Create button

		Button buttonName = new Button();
		vp.add(buttonName);
		buttonName.setText("Create!");
		buttonName.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				createPerson(nameBox.getText(), addressBox.getText());
			}
		});
	}

	private void searchPersonById(String id) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET,
				GWT.getHostPageBaseURL() + "rest/person/" + id);
		rb.setCallback(new RequestCallback() {

			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}

			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					PersonJsonConverter converter = PersonJsonConverter
							.getInstance();
					PersonItf p = converter.deserializePersonFromJson(response
							.getText());
					displayPerson(RootPanel.get("gwtResult"), p);
					displayMario();
				}
			}
		});
		try {
			rb.send();
		} catch (RequestException e) {
			e.printStackTrace();
			hideMario();
		}
	}

	private void searchPersonByName(String p) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET,
				GWT.getHostPageBaseURL() + "rest/person/named/" + p);
		rb.setCallback(new RequestCallback() {

			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}

			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					PersonJsonConverter converter = PersonJsonConverter
							.getInstance();
					PersonItf p = converter.deserializePersonFromJson(response
							.getText());
					displayPerson(RootPanel.get("gwtResult"), p);
					displayMario();
				}
			}
		});
		try {
			rb.send();
		} catch (RequestException e) {
			e.printStackTrace();
			hideMario();
		}
	}

	private void createPerson(final String name, final String address) {

		Window.alert("Creating " + name + " @ " + address);

		RequestBuilder reqCreatePerson = new RequestBuilder(RequestBuilder.GET,
				GWT.getHostPageBaseURL() + "rest/person/create/" + name);

		final RequestCallback homeCreated = new RequestCallback() {
			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}

			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {

					Window.alert("The home at " + address + " is created.");
					PersonJsonConverter converter = PersonJsonConverter
							.getInstance();
					PersonItf p = converter.deserializePersonFromJson(response
							.getText());
					displayPerson(RootPanel.get("gwtResult"), p);
					displayMario();
				}
			}
		};

		final RequestCallback personCreated = new RequestCallback() {
			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}

			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					Integer id = Integer.decode(response.getText());

					Window.alert(name + " created, it's ID is " + id);

					RequestBuilder reqCreateHome = new RequestBuilder(
							RequestBuilder.GET, GWT.getHostPageBaseURL()
									+ "person/" + id + "/createhome/" + address);

					reqCreateHome.setCallback(homeCreated);

					Window.alert("Sending request for the creation the home at "
							+ address);

					try {
						reqCreateHome.send();
					} catch (RequestException e) {
						e.printStackTrace();
						hideMario();
					}
				} else {
					Window.alert("Response recieved with cod ID: "
							+ response.getStatusCode() + "\nContent: "
							+ response.getText());

				}
			}
		};

		reqCreatePerson.setCallback(personCreated);

		Window.alert("Sending request for the creation of " + name);

		try {
			reqCreatePerson.send();
		} catch (RequestException e) {
			e.printStackTrace();
			hideMario();
		}
	}

	public void displayMario() {
		RootPanel.get("mario").setStylePrimaryName("visible center");
	}

	public void hideMario() {
		RootPanel.get("mario").setStylePrimaryName("hidden center");
	}

	public void displayPerson(RootPanel panel, PersonItf p) {
		panel.clear();

		VerticalPanel vp = new VerticalPanel();
		panel.add(vp);
		vp.add(new Label("name : " + p.getName()));
		vp.add(new Label("ID : " + p.getId()));
		List<ElectronicDevice> devices = p.getDevices();
		if (!devices.isEmpty())
			vp.add(new Label("Devices : "));
		for (ElectronicDevice d : devices) {
			vp.add(new Label("  - " + d.getName() + " (ID=" + d.getId() + ")"));
		}
		List<Person> friends = p.getFriends();
		if (!friends.isEmpty())
			vp.add(new Label("Friends : "));
		for (Person f : friends) {
			vp.add(new Label("  - " + f.getName() + " (ID=" + f.getId() + ")"));
		}
	}
}
