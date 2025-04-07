package allumettes;

public interface Strategie {

    /** Obtenir le nombre d'allumette choisi par le joueur.
     * @param game la partie jouée
     * @return le nombre d'allumette choisi
     * @throws CoupInvalideException
     */
    int getPrise(Jeu game);

    /** Obtenir le nom de la stratégie.
     * @return le nom de la stratégie
     */
    String getNomStrat();

}
