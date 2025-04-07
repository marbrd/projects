package allumettes;
import java.util.Random;

/** StrategieExpert modélise le choix du joueur d'adopter une stratégie d'expert.
 * @author Marouane BERRAD
 */
public class StrategieExpert implements Strategie {

    /**Le nom de la stratégie.*/
    private String nomStrat;

    /** Construire la stratégie expert à partir de son nom.
     */
    public StrategieExpert() {
        this.nomStrat = "expert";
    }

    @Override
    public String getNomStrat() {
        return this.nomStrat;
    }

    @Override
    public int getPrise(Jeu jeu) {

        int allumettes = jeu.getNombreAllumettes();
        if (allumettes % (Jeu.PRISE_MAX + 1) == 1) {
            Random random = new Random();
            return random.nextInt(Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes())) + 1;
        } else if (allumettes % (Jeu.PRISE_MAX + 1) == 0) {
            return Jeu.PRISE_MAX;
        } else {
            return ((allumettes % (Jeu.PRISE_MAX + 1)) - 1) % Jeu.PRISE_MAX;
        }

    }

}
