package javafx.template.mvc.client.i18n;

public interface IMessage {

	/**
	 * Insert String into message and allow String format (%s).
	 *
	 * @param args Object...
	 * @return String.
	 */
	String use(Object... args);

}