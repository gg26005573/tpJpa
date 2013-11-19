package fr.istic.tpjpa.client;

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
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

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
	        Window.alert("You selected a menu item!");
	      }
	    };
	    
	    Command searchPersonCmd = new Command() {
	      public void execute() {
	    	  displayPersonSearch();
	      }
	    };

	    // Make some sub-menus that we will cascade from the top menu.
	    MenuBar searchMenu = new MenuBar(true);
	    searchMenu.addItem("Person", searchPersonCmd);
	    searchMenu.addItem("Home", cmd);

	    MenuBar updateMenu = new MenuBar(true);
	    updateMenu.addItem("Person", cmd);
	    updateMenu.addItem("Home", cmd);


	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("Search", searchMenu);
	    menu.addItem("Update", updateMenu);

	    // Add it to the root panel.
	    RootPanel.get("gwtMenu").add(menu);

	}
	
	public void displayPersonSearch(){
		RootPanel panel = RootPanel.get("gwtProcess");
		
		panel.clear();
		// Create a text
		final TextBox area = new TextBox();
		area.setValue("1");
		panel.add(area);
		
		
		// Create a button
		com.google.gwt.user.client.ui.Button b = new Button();
		b.setText("sudo display this person, now!");
		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT
						.getHostPageBaseURL() + "rest/person/" + area.getText());
				rb.setCallback(new RequestCallback() {

					public void onError(Request request, Throwable exception) {
						Window.alert(exception.getMessage());
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (200 == response.getStatusCode()) {
							PersonJsonConverter converter = PersonJsonConverter
									.getInstance();
							PersonItf p = converter
									.deserializeFromJson(response.getText());
//									 Window.alert("Name: " + p.getName()
//									 + ", Devices: " + p.getDevices());
							 Formater.displayPerson(
									 RootPanel.get("gwtResult"),
									 p);
							// Window.alert("Serialized person: " +
							// response.getText());
							 displayMario();
						}
					}
				});
				try {
					rb.send();
				} catch (RequestException e) {
					e.printStackTrace();
				}

			}
		});

		panel.add(b);
	}
	
	public void displayMario(){
		RootPanel.get("mario").setStylePrimaryName("visible center");
	}
}
