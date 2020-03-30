package eu.telecomsudparis.csc4102.gestionclefshotel;


import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Cette classe définit le consommateur. Les notifications sont typées.
 *
 * @author Denis Conan
 */
public class Administrateur implements Subscriber<String> {
    /**
     * identifiant du consommateur : pour les messages a la console qui servent a
     * suivre l'execution.
     */
    private String id;
    private String nom;
    private String prenom;

    /**
     * la souscription. Cet objet sert a controler le flux entre le producteur et le
     * consommateur.
     */
    private Subscription souscription;
    // on pourrait ajouter une collection pour garder les notifications recues

    /**
     * constructeur.
     *
     * @param id identifiant pour les affichages.
     */
    public Administrateur(final String id,String nom,String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public void onSubscribe(final Subscription souscription) {
        this.souscription = souscription;
        // on consomme un message des qu'il arrive ; un a la fois
        // on declare qu'on est pret a recevoir un message
        this.souscription.request(1);
        System.out.println("Admin " + id + nom +prenom+ " pret a recevoir la premiere notification");
    }

    @Override
    public void onNext(final String notification) {
        // reception d'une notification : ici, simple affichage a la console
        System.out.println("Admin " + id + nom +prenom + " a recu une nouvelle notification : " + notification);
        // on declare qu'on est pret a recevoir un nouveau message
        souscription.request(1);
    }

    @Override
    public void onError(final Throwable throwable) {
        // erreur sur la gestion du flux, par exemple producteur.subscribe
        // d'un consommateur qui est deja un subscriber du producteur
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        // lorsque le producteur ferme le flux, on affiche la fin a la console
        System.out.println("Admin "+ id + nom +prenom+ " est desabonne suite a la fermeture du flux par le producteur");
    }

}
