package production;

/**
 * @author tommens
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UnknownDestinationException extends Exception {

	/**
	 * Constructor for production.UnknownDestinationException.
	 */
	public UnknownDestinationException() {
		this("production.Packet has unknown destination");
	}

	/**
	 * Constructor for production.UnknownDestinationException.
	 * @param arg0
	 */
	public UnknownDestinationException(String arg0) {
		super(arg0);
	}

}
