package allumettes;

/** Proxy un intermédiaire qui prend une partie et contrôle les actions des joueurs.
 * @author Marouane BERRAD
 */
public class Proxy implements Jeu {

    /**La partie dont lequel le proxy contrôle les joueurs.*/
    private Jeu partie;

    /** Construire un proxy pour la partie jouée.
     * @param partie la partie jouée
     */
    public Proxy(Jeu partie) {
        this.partie = partie;
    }

    /** Obtenir la partie contrôlée par le proxy.
     * @return la partie dont lequel le proxy contrôle les joueurs
     */
    public Jeu getPartie() {
        return this.partie;
    }

    @Override
    public int getNombreAllumettes() {
        return this.partie.getNombreAllumettes();
    }

    @Override
    public void retirer(int nbPrises) {
        throw new OperationInterditeException(null);
    }
}
