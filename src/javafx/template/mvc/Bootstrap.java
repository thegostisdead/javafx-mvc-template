package javafx.template.mvc;


import javafx.template.mvc.application.ClientApplication;
import javafx.template.mvc.util.Logger;
import javafx.template.mvc.util.cli.Option;
import javafx.template.mvc.util.cli.OptionParser;

public class Bootstrap {

	/* Options */
	public static final Option HELP_OPTION = new Option('h', "help", "Show this help page.");
	public static final Option LOG_OPTION = new Option('l', "log", "Enable some log-level. (e.g: DEBUG,VERBOSE)", "");

	/* Options examples

	public static final Option SERVER_OPTION = new Option('s', "server", "Start a server.");
	public static final Option CLIENT_OPTION = new Option('c', "client", "Start a client.");
	public static final Option IP_OPTION = new Option('i', "ip", "Specify the IP address to connect to.", "localhost");
	public static final Option PORT_OPTION = new Option('p', "port", "Specify the port to connect to.", 5555);
	public static final Option CLIENT_GIVEN_EMAIL_OPTION = new Option((char) 0, "email", "Pre-fill the client UI with an email.", "");
	public static final Option CLIENT_GIVEN_PASSWORD_OPTION = new Option((char) 0, "password", "Pre-fill the client UI with a password.", "");
	public static final Option FRENCH_LANGUAGE = new Option('f', "french", "Select French 'fr' ");
	*/

	/* Logger */
	private static final Logger LOGGER = new Logger(Bootstrap.class);

	/* Bootstrap */
	public static void main(String[] args) throws Exception {
		OptionParser optionParser = new OptionParser();

		optionParser.addOption(HELP_OPTION);
		optionParser.addOption(LOG_OPTION);


		try {
			optionParser.parse(args);

			if (HELP_OPTION.isUsed()) {
				throw new UnsupportedOperationException();
			}
		} catch (Exception exception) {
			if (!(exception instanceof UnsupportedOperationException)) {
				System.out.println("Failed to parse arguments.");
				System.out.println("  Cause: ");
				exception.printStackTrace();
			}

			optionParser.printHelp(System.out);

			if (exception instanceof UnsupportedOperationException) {
				System.exit(0);
			}
		}

		/* Enable log-level from CLI. */
		if (LOG_OPTION.isUsed()) {
			String[] levelsToEnable = ((String) LOG_OPTION.getValue()).split(",");

			for (String levelToEnable : levelsToEnable) {
				try {
					Logger.Level level = Logger.Level.valueOf(levelToEnable);

					level.enable();
					LOGGER.log(level, "Level enabled!");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}


		ClientApplication.startFromBootstrap();

	}

}