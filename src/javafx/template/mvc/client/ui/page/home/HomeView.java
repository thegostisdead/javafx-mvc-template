package javafx.template.mvc.client.ui.page.home;

import javafx.scene.control.Button;
import javafx.template.mvc.client.ui.mvc.AbstractView;
import javafx.template.mvc.client.ui.mvc.IController;


public class HomeView extends AbstractView {


	/* UI */

	private final Button helloButton;


	/* Constructor */

	public HomeView() {
		super();

		this.helloButton = this.findById("hello");

	}

	@Override
	public String getViewPath() {
		return "home.fxml";
	}


	@Override
	public IController createController() {
		return new HomeController();
	}

	public Button getHelloButton() {
		return helloButton;
	}
}