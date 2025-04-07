package allumettes;

/** StrategieTricheur modélise le choix du joueur de tricher.
 * @author Marouane BERRAD
 */
public class StrategieTricheur implements Strategie {

    /**Le nom de la stratégie.*/
    private String nomStrat;

    /** Construire la stratégie tricheur à partir de son nom.
     */
    public StrategieTricheur() {
        this.nomStrat = "tricheur";
    }

    @Override
    public String getNomStrat() {
        return this.nomStrat;
    }

    @Override
    public int getPrise(Jeu jeu) {

        if (jeu.getNombreAllumettes() > 2) {
            System.out.println("[Je triche...]");
            try {
            while (jeu.getNombreAllumettes() - 2 > 0) {
                jeu.retirer(Math.min(jeu.getNombreAllumettes() - 2, Jeu.PRISE_MAX));
            }
            } catch (CoupInvalideException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("[Allumettes restantes : " + jeu.getNombreAllumettes() + "]");
        return 1;

    }

}
