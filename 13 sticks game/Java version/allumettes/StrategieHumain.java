package allumettes;
import java.util.Scanner;

/** StrategieHumain modélise le choix de laisser l'utilisateur choisir le nombres.
 * @author Marouane BERRAD
 */
public class StrategieHumain implements Strategie {

    /**Le nom du joueur humain.*/
    private String nomJoueur;

    /**Le nom de la stratégie.*/
    private String nomStrat;

    /**le scan pour lire les choix de l'utilisateur entrés comme argument.*/
    private Scanner scan;


    /** Construire la stratégie humaine à partir de son nom, nom du joueur, et le scan.
     */
    public StrategieHumain(String nomjoueur, Scanner scan) {
        this.nomStrat = "humain";
        this.nomJoueur = nomjoueur;
        this.scan = scan;
    }

    @Override
    public String getNomStrat() {
        return this.nomStrat;
    }

    @Override
    public int getPrise(Jeu jeu) {

        System.out.print(this.nomJoueur + ", combien d'allumettes ? ");
        String in = this.scan.nextLine();

        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            if (!in.isEmpty() && in.equals("triche") && jeu.getNombreAllumettes() > 1) {
                try {
                    jeu.retirer(1);
                    System.out.println("[Une allumette en moins, plus que "
                    + jeu.getNombreAllumettes() + ". Chut !]");
                } catch (CoupInvalideException f) {
                    System.out.println(f.getMessage());
                }
            } else {
                System.out.println("Vous devez donner un entier.");
            }
            return getPrise(jeu);
        }
    }

}
