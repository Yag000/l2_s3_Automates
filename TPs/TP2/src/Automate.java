import java.rmi.server.RemoteStub;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.Map;

public class Automate extends HashSet<Etat> {

    private Etat init;

    public Automate(Etat e) {
        super();
        this.init = e;
        this.initialiseAutomate(e);
    }

    @Override
    public String toString() {
        String s = "" + this.size() + " états; ";
        s += "état intial : " + init.getId() + "\n\n";
        for (Etat e : this) {
            s += e.toString() + "\n";
        }
        return s;
    }

    // Retourne true si et seulement si e n'était pas déjà dans l'ensemble.
    boolean ajouteEtatSeul(Etat e) {
        return this.add(e);
    }

    /**
     * Construit un automate à partir de l'ensemble des états accessibles
     * depuis l'état initial.
     * Retourne true si et seulement si l'état initial n'était pas déjà
     * présent dans l'automate.
     */

    boolean initialiseAutomate(Etat e) {
        if (!ajouteEtatSeul(e))
            return false;
        for (char c : e.alphabet()) {
            for (Etat e1 : e.succ(c))
                initialiseAutomate(e1);
        }
        return true;
    }

    // Retourne le nombre de transitions dans l'automate
    int nombreTransitions() {
        int nb = 0;
        for (Etat e : this) {
            nb += e.nombreTransitions();
        }
        return nb;
    }

    // Retourne l'ensemble des lettres utilisées dans l'automate.
    Set<Character> alphabet() {
        Set<Character> alphabet = new HashSet<Character>();

        for (Etat e : this) {
            alphabet.addAll(e.alphabet());
        }
        return alphabet;
    }

    // Retourne true si et seulement si l'automate est complet
    boolean estComplet() {
        // à écrire
        return false;
    }

    // Pour true si et seulement si l'automate est déterministe
    boolean estDeterministe() {
        for (Etat e : this)
            if (!e.estDeterministe())
                return false;
        return true;

    }

    // premier algorithme d'acceptation d'un mot
    boolean accepte(String mot) {
        return this.init.accepte(mot);
    }

    // algorithme plus efficace
    boolean accepte2(String mot) {
        // écrire ici l'algorithme plus efficace
        if (mot == "")
            return init.estAcceptant();

        Set<Etat> etats = new HashSet<Etat>();
        Set<Etat> nextEtats = new HashSet<Etat>();

        etats.add(init);

        for (int i = 0; i < mot.length(); i++) {
            char c = mot.charAt(i);

            for (Etat e : etats) {
                if (e.alphabet().contains(c))
                    nextEtats.addAll(e.succ(c));

            }

            etats.clear();

            for (Etat e : nextEtats)
                etats.add(e);

            nextEtats.clear();

        }

        for (Etat e : etats)
            if (e.estAcceptant())
                return true;

        return false;
    }

    /**
     * Removes non coaccesible states from the automaton.
     */
    public void enleverEtatsNonCoAccessibles() {
        int[] tab = new int[this.size()];
        init.getEtatsNonCoAccessibles(tab);

        Set<Etat> etats = new HashSet<Etat>();
        for (Etat e : this) {
            if (tab[e.getId()] == -1)
                etats.add(e);
        }

        for (Etat e : this) {
            if (!etats.contains(e)) {
                e.enleverTransitions(etats);
            }
        }
        initialiseAutomate(init);

        for (Etat e : etats) {
            this.remove(e);
        }

    }

    void test() {
        int i = 0;
        for (Etat e : this) {
            if (i == 29) {
                e.enleverTransitions('c', e.succ('c'));
            }
            i++;
        }

    }

    // Déterminisation
    Automate determinisation() {
        // à écrire
        return null;
    }
}
