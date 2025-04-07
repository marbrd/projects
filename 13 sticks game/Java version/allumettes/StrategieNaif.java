package allumettes;
import java.util.Random;

/** StrategieNaif modélise le choix du joueur de prendre un nombre aléatoire
 * entre 1 et PRISE_MAX.
 * @author Marouane BERRAD
 */
public class StrategieNaif implements Strategie {

    /**Le nom de la stratégie.*/
    private String nomStrat;

    /** Construire la stratégie naïf à partir de son nom.
     */
    public StrategieNaif() {
        this.nomStrat = "naif";
    }

    @Override
    public String getNomStrat() {
        return this.nomStrat;
    }

    @Override
    public int getPrise(Jeu jeu) {
        Random random = new Random();
        return random.nextInt(Jeu.PRISE_MAX) + 1;
    }

}
