package allumettes;

/** Joueur modélise un joueur. Un joueur a un nom et une stratégie.
 * @author Marouane BERRAD
 */
public class Joueur {

    private String nomJoueur;
    private Strategie strategie;

    public Joueur(String nomJoueur, Strategie strategie) {
        this.nomJoueur = nomJoueur;
        this.strategie = strategie;
    }

    /** Obtenir le nom du joueur.
     * @return le nom du joueur
     */
    public String getNom() {
        return this.nomJoueur;
    }

    /** Obtenir la strategie du joueur.
     * @return la strategie du joueur
     */
    public Strategie getStrategie() {
        return this.strategie;
    }

    /** Changer la stratégie du joueur.
     */
    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    /** Afficher le nom du joueur.
     * @return affichage du joueur
     */
    public String toString() {
        return this.getNom();
    }

    /** Obtenir le nombre d'allumette choisi par le joueur.
     * @return le nombre d'allumette choisi
     */
    public int getPrise(Jeu jeu) {
        return this.strategie.getPrise(jeu);
    }

    /** Vérifier l'égaliter entre deux joueurs.
     * @param joueur le joueur à qui on compare
     * @return l'égalité ou pas
     */
    public Boolean isEqual(Joueur joueur) {
        return this == joueur || (this.getNom() == joueur.getNom()
        && this.getStrategie() == joueur.getStrategie());
    }

}
