

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logg {
	Logger log;

	public Logg() {
		BasicConfigurator.configure();
		log = Logger.getLogger(Logg.class.getCanonicalName());
		PropertyConfigurator.configure("log4j.properties");
		PropertyConfigurator.configure("log4j.properties");

	}

	// private static Logger getLogger(String name) {
	// // TODO Auto-generated method stub
	// return null;
	// };

	public void info(String strMsg) {

		log.info("I am info" + strMsg);

	}

	public void debug(String strMsg) {

		log.debug("I am debug" + strMsg);

	}
}
