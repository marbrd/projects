package allumettes;

/** Exception qui indique que l'opération demander est interdite.
 * @author Marouane BERRAD
 */
public class OperationInterditeException extends RuntimeException {

  public OperationInterditeException(String message) {
	  super(message);
  }

}
