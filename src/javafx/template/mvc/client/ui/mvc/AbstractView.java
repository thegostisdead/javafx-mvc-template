package javafx.template.mvc.client.ui.mvc;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.template.mvc.client.i18n.impl.I18nMessage;
import javafx.template.mvc.client.resources.Resource;
import javafx.template.mvc.util.Logger;
public abstract class AbstractView implements IView {

	/* Variables */
	private final Logger logger;
	private final Parent root;

	/* Constructor */
	protected AbstractView() {
		this.logger = new Logger(this.getClass());

		try {
			this.root = FXMLLoader.load(Resource.loadForm(this.getViewPath()), I18nMessage.getGlobalResourceBundle());
			//Debug.dump(this.root);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@SuppressWarnings("unchecked")
	protected <T extends Node> T findById(String id) {

		Node node = this.root.lookup("#" + id);

		if (node == null) {
			this.getLogger().warning("There is no view with ID: %s", id);
		}

		return (T) node;

	}

	@Override
	public Parent getRoot() {
		return this.root;
	}

	public Logger getLogger() {
		return this.logger;
	}

}
