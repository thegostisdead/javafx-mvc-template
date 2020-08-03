package javafx.template.mvc.client.ui.page.home;

import javafx.event.ActionEvent;
import javafx.template.mvc.client.ui.mvc.IController;
import javafx.template.mvc.client.ui.mvc.IView;


public class HomeController implements IController {


	/* Variables */
	private HomeView view;


	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == this.view.getHelloButton()) {
			System.out.println("Hello World !!!!");
		}


	}

	@Override
	public void attachView(IView view) {
		if (!(view instanceof HomeView)) {
			throw new IllegalArgumentException();
		}

		this.view = (HomeView) view;

		/* Set action events */

		this.view.getHelloButton().setOnAction(this);


	}

	@Override
	public void onMount() {

	}

	@Override
	public void onUnmount() {

	}


}