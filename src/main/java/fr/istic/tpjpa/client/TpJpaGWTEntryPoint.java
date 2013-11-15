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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TpJpaGWTEntryPoint implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Create a text
		final TextBox area = new TextBox();
		area.setValue("1");
		RootPanel.get("mainGwtEntryPoint").add(area);

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
//							PersonJsonConverter converter = PersonJsonConverter
//									.getInstance();
//							PersonItf p = converter
//									.deserializeFromJson(response.getText());
//							 Window.alert("Name: " + p.getName()
//							 + ", Devices: " + p.getDevices());
							// Window.alert("Serialized person: " +
							// response.getText());
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

		RootPanel.get("mainGwtEntryPoint").add(b);

	}
}
