package javafx.template.mvc.client.i18n.impl;


import javafx.template.mvc.client.i18n.IMessage;

import java.util.ResourceBundle;

public class I18nMessage implements IMessage {

	/* Globals */
	private static ResourceBundle resourceBundle;

	/* Variables */
	private final String key;

	/***
	 * Constructor I18nMessage
	 * @param key String.
	 */
	public I18nMessage(String key) {
		this.key = key;
	}

	@Override
	public String use(Object... args) {
		if (resourceBundle == null) {
			return this.key;
		}

		return String.format(resourceBundle.getString(this.key), args);
	}

	/***
	 * Define the global resource bundle for translations
	 * @param resourceBundle ResourceBundle.
	 */
	public static void setGlobalResourceBundle(ResourceBundle resourceBundle) {
		I18nMessage.resourceBundle = resourceBundle;
	}

	public static ResourceBundle getGlobalResourceBundle() {
		return resourceBundle;
	}
}