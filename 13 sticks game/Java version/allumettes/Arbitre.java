package allumettes;

/** Arbitre fait respecter les règles du jeu aux deux joueurs.
 *
 * @author Marouane BERRAD
 * @version 1.0
*/

public class Arbitre {

    /**Premier joueur.*/
    private Joueur j1;
    /**Deuxième joueur.*/
    private Joueur j2;
    /**Joueur qui doit jouer le coup.*/
    private Joueur role;

    /**Est ce qu'une tricherie est detectée?*/
    private static Boolean tricherie = false;

    /**Construction d'un arbitre à partir des deux joueurs.
     * Par défaut, le premier joueur commence le jeu.
     * @param j1 le premier joueur
     * @param j2 le deuxième joueur
     */
    public Arbitre(Joueur j1, Joueur j2) {
        assert j1 != null && j2 != null;
        this.j1 = j1;
        this.j2 = j2;
        this.role = j1;
    }

    /** Changer le statut tricherie.
     * @param status Oui ou Non
     */
    public static void setTricherie(Boolean status) {
        tricherie = status;
    }

    /** Obtenir le joueur qui doit jouer le coup.
     * @return le joueur qui doit jouer le coup
     */
    public Joueur getRole() {
        return this.role;
    }

    /** Passer le rôle à l'autre joueur pour jouer le coup suivant.
     */
    public void passerRole() {
        if (this.role.isEqual(this.j1)) {
            this.role = this.j2;
        } else {
            this.role = j1;
        }
    }

    /** Afficher le coup joué par le joueur.
     * @param nbPrise le nombre choisi par le joueur
     */
    public void afficherCoup(int nbPrise) {
        System.out.print(this.getRole().getNom() + " prend " + nbPrise);
        if (nbPrise <= 1) {
            System.out.println(" allumette.");
        } else {
            System.out.println(" allumettes.");
        }
    }

    /** Faire respecter les règles du jeu lors du coup joué.
     * @param game la partie jouée
     * @throws CoupInvalideException
     */
    public void arbitrerCoup(Jeu game) throws CoupInvalideException {
        System.out.println("Allumettes restantes : " + game.getNombreAllumettes());
        int nbPrise = 0;
        Jeu jeu = game;
        try {
            if (game instanceof Proxy) {
                jeu = ((Proxy) game).getPartie();
            }
            nbPrise = this.getRole().getPrise(game);
            jeu.retirer(nbPrise);
            afficherCoup(nbPrise);
            this.passerRole();
        } catch (CoupInvalideException e) {
            afficherCoup(nbPrise);
            System.out.println("Impossible ! Nombre invalide : " + e.getProbleme());
        } catch (OperationInterditeException e) {
            System.out.println("Abandon de la partie car " + this.getRole().getNom()
            + " triche !");
            setTricherie(true);
            jeu.retirer(jeu.getNombreAllumettes());
        }

    }

    /** Terminer la partie en annonçant le gagnant et le perdant.
     * @param partie la partie jouée
     */
    public void fin(Jeu partie) {
        if (this.role.isEqual(this.j1)) {
            System.out.println(this.j2 + " perd !");
        } else {
            System.out.println(this.j1 + " perd !");
        }
        System.out.println(this.getRole() + " gagne !");
    }

    /** Arbitrer la partie.
     * @param game la partie jouée
     */
    public void arbitrer(Jeu game) {
        try {
            while (game.getNombreAllumettes() > 0) {
            this.arbitrerCoup(game);
        }
        } catch (CoupInvalideException e) {
        }
        if (!tricherie) {
            this.fin(game);
        }
    }

}
