package javafx.template.mvc.application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.template.mvc.client.i18n.impl.I18nMessage;
import javafx.template.mvc.client.manager.UserInterface;
import javafx.template.mvc.client.resources.Resource;
import javafx.template.mvc.util.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class ClientApplication extends Application {

	/* Logger */
	private final static Logger LOGGER = new Logger(ClientApplication.class);

	/**
	 * Start the client application.
	 *
	 * @throws Exception
	 */
	public static void startFromBootstrap() throws Exception {
		LOGGER.verbose("Starting client...");

		/* Loading global resource bundle for i18n. */
		Locale locale = Resource.DEFAULT_LOCALE_FRENCH;

		/*
		if (!Bootstrap.FRENCH_LANGUAGE.isUsed()) {
			locale =
		}*/

		ResourceBundle resourceBundle = Resource.loadResourceBundle("strings", locale);
		I18nMessage.setGlobalResourceBundle(resourceBundle);

		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			UserInterface.get().initialize(primaryStage);

			primaryStage.getIcons().add(Resource.loadImage("app.png"));
			primaryStage.setTitle("App name");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
