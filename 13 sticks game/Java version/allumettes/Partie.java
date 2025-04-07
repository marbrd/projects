package allumettes;

/** Partie modélise une partie du jeu de 13 allumettes,
 * y compris les règles sur la prise des allumettes.
 * @author Marouane BERRAD
 */
public class Partie implements Jeu {

    /**Le nombre d'allumettes restantes à la partie.*/
    private int nombreAllumettes;

    /** Construire une partie à partir du nombre d'allumettes.
     */
    public Partie(int nombreAllumettes) {
        this.nombreAllumettes = nombreAllumettes;
    }

    @Override
    public int getNombreAllumettes() {
        return this.nombreAllumettes;
    }

    @Override
    public void retirer(int nbPrises) throws CoupInvalideException {
        if (nbPrises > Math.min(PRISE_MAX, getNombreAllumettes())) {
            throw new CoupInvalideException(nbPrises, nbPrises + " (> "
            + Math.min(PRISE_MAX, getNombreAllumettes()) + ")");
        } else if (nbPrises < 1) {
            throw new CoupInvalideException(nbPrises, nbPrises + " (< " + 1 + ")");
        } else {
            this.nombreAllumettes = this.nombreAllumettes - nbPrises;
        }
    }

}
