package javafx.template.mvc.client.ui.mvc;

import javafx.scene.Parent;

public interface IView {

	String getViewPath();

	Parent getRoot();

	IController createController();

}