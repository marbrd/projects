package allumettes;
import java.util.Scanner;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

	/**Le nombre initial des allumettes.*/
	private static final int INIT = 13;

	/** Obtenir la stratégie (non humaine) choisie par le joueur.
	 * @param nom le nom de la stratégie choisie
	 * @return la stratégie choisie
	 * @throws ConfigurationException
	 */
	public static Strategie choixStrategie(String nom) throws ConfigurationException {
        if (nom.equals("expert")) {
            return new StrategieExpert();
        } else if (nom.equals("rapide")) {
            return new StrategieRapide();
        } else if (nom.equals("tricheur")) {
            return new StrategieTricheur();
        } else if (nom.equals("naif")) {
            return new StrategieNaif();
        } else {
            throw new ConfigurationException("Strategie inexistante");
        }
	}

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) throws ConfigurationException {
		final int nbArgConfiant = 3;
		Scanner scan = new Scanner(System.in);
		try {
			verifierNombreArguments(args);
			String[] joueur1 = args[args.length - 2].split("@");
			String[] joueur2 = args[args.length - 1].split("@");
			if (joueur1.length > 2 || joueur2.length > 2) {
				throw new ConfigurationException(
				"Il faut donner un seule nom du joueur et une seule stratégie");
			}
			Joueur j1 = null;
			Joueur j2 = null;
			if (joueur1[1].equals("humain")) {
				j1 = new Joueur(joueur1[0], new StrategieHumain(joueur1[0], scan));
			} else {
				j1 = new Joueur(joueur1[0], choixStrategie(joueur1[1]));
			}
			if (joueur2[1].equals("humain")) {
				j2 = new Joueur(joueur2[0], new StrategieHumain(joueur2[0], scan));
			} else {
				j2 = new Joueur(joueur2[0], choixStrategie(joueur2[1]));
			}
			Arbitre arbitre = new Arbitre(j1, j2);
			Partie partie = new Partie(INIT);
			Proxy proxy = new Proxy(partie);
			if (args.length == nbArgConfiant && args[0].equals("-confiant")) {
				arbitre.arbitrer(partie);
			} else {
				arbitre.arbitrer(proxy);
			}

		} catch (ConfigurationException | ArrayIndexOutOfBoundsException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	/** Verifier le nombre des arguments.
	 * @param args les arguments
	 */
	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe.
	*/
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
