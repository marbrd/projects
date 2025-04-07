package allumettes;

/** StrategieRapide modélise le choix du joueur de plus grand nombre possible
 * à chaque fois.
 * @author Marouane BERRAD
 */
public class StrategieRapide implements Strategie {

    /**Le nom de la stratégie.*/
    private String nomStrat;

    /** Construire la stratégie rapide à partir de son nom.
     */
    public StrategieRapide() {
        this.nomStrat = "rapide";
    }

    @Override
    public String getNomStrat() {
        return this.nomStrat;
    }

    @Override
    public int getPrise(Jeu jeu) {
        return Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes());
    }

}
