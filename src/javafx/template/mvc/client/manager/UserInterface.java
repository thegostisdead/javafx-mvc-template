package javafx.template.mvc.client.manager;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.template.mvc.client.ui.mvc.IController;
import javafx.template.mvc.client.ui.mvc.IView;
import javafx.template.mvc.client.ui.page.home.HomeView;
import javafx.template.mvc.util.Logger;

public class UserInterface {

	/* Logger */
	private final static Logger LOGGER = new Logger(UserInterface.class);

	/* Singleton */
	private final static UserInterface INSTANCE = new UserInterface();

	/* Variables */
	private Stage stage;
	private Scene scene;
	private IView currentView;
	private IController currentController;
	private Stage currentDialog;

	/**
	 * Constructor UserInterface
	 */
	private UserInterface() {
	}

	/**
	 * Get the UserInterface.
	 *
	 * @return UserInterface.
	 */
	public static UserInterface get() {
		return INSTANCE;
	}


	/**
	 * Setting up first page
	 *
	 * @param primaryStage Stage.
	 * @throws Exception
	 */
	public void initialize(Stage primaryStage) throws Exception {
		this.stage = primaryStage;


		set(new HomeView());
	}

	/**
	 * Set view to an other.
	 *
	 * @param view View to switch
	 */
	public void set(IView view) {
		Platform.runLater(() -> {
			if (this.currentController != null) {
				this.currentController.onUnmount();
				this.currentController = null;
			}

			this.currentView = view;
			this.currentController = view.createController();

			LOGGER.verbose("Set view to: %s", view.getClass().getCanonicalName());

			if (this.currentController != null) {
				this.currentController.onMount();
				this.currentController.attachView(view);
			}

			if (this.scene == null) {
				this.scene = new Scene(view.getRoot());
				this.stage.setScene(this.scene);
			} else {
				this.scene.setRoot(view.getRoot());
			}
		});
	}

	/**
	 * Open a dialog window
	 *
	 * @param view View of Dialog
	 */
	public void openDialog(IView view) {
		// run dialog in a new thread
		Platform.runLater(() -> {
			IController controller = view.createController();

			LOGGER.verbose("Opening dialog to: %s", view.getClass().getCanonicalName());

			if (controller != null) {
				controller.onMount();
				controller.attachView(view);
			}

			this.currentDialog = new Stage();

			this.currentDialog.setScene(new Scene(view.getRoot()));

			this.currentDialog.initOwner(this.stage);
			this.currentDialog.initModality(Modality.APPLICATION_MODAL);
			this.currentDialog.showAndWait();

			if (controller != null) {
				controller.onUnmount();
			}

			this.currentDialog = null;
		});


	}

	/**
	 * Close current Dialog.
	 *
	 * @see <a href="https://stackoverflow.com/questions/28698106/why-am-i-unable-to-programmatically-close-a-dialog-on-javafx">How to close dialog</a>
	 */
	public void closeCurrentDialog() {
		if (this.currentDialog != null) {
			this.currentDialog.close();
		}

	}

	/**
	 * Get current Opened dialog.
	 *
	 * @return CurrentDialog.
	 */
	public Stage getCurrentDialog() {
		return this.currentDialog;
	}
}