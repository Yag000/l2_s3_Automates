import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.List;

public class Automate {

    // etats[i] est l'état dont l'id est i
    private Etat[] etats;

    // Crée un automate avec n états numérotés de 0 à n-1
    public Automate(int n) {
        this.etats = new Etat[n];
        for (int i = 0; i < n; ++i) {
            etats[i] = new Etat(i, false, false);
        }
    }

    // getter pour l'ensemble des états
    Etat[] getEtats() {
        return this.etats;
    }

    // getter pour l'état avec l'id i
    Etat getEtat(int i) {
        return this.etats[i];
    }

    int nbEtats() {
        return this.etats.length;
    }

    void setInitial(int i, boolean initial) {
        etats[i].estInitial = initial;
    }

    boolean getInitial(int i) {
        return etats[i].estInitial;
    }

    void setAcceptant(int i, boolean acceptant) {
        etats[i].estAcceptant = acceptant;
    }

    boolean getAcceptant(int i) {
        return etats[i].estAcceptant;
    }

    @Override
    public String toString() {
        String s = "" + this.etats.length + " etats;\n";
        for (Etat e : getEtats()) {
            s += e.toString() + "\n";
        }
        return s;
    }

    // Ajoute une transition
    void ajouteTransition(int idDebut, int idFin, char c) {
        etats[idDebut].ajouteTransition(c, idFin);
    }

    // Retourne le nombre de transitions dans l'automate
    int nombreTransitions() {
        int n = 0;
        for (Etat e : getEtats())
            n += e.nombreTransitions();
        return n;
    }

    // Retourne l'ensemble des lettres utilisées dans l'automate.
    Set<Character> alphabet() {
        Set<Character> alph = new HashSet<Character>();
        for (Etat e : getEtats())
            alph.addAll(e.alphabet());
        return alph;
    }

    // Retourne true si et seulement si l'automate est complet
    boolean estComplet() {
        Set<Character> alph = this.alphabet();
        for (Etat e : getEtats())
            if (!e.alphabet().equals(alph))
                return false;
        return true;
    }

    // Pour true si et seulement si l'automate est déterministe
    boolean estDeterministe() {
        for (Etat e : getEtats())
            for (char a : e.alphabet())
                if (e.succ(a).size() > 1)
                    return false;
        return true;
    }

    // Calcule les états accessibles a partir d'un ensemble d'états,
    // pour une lettre donnée.
    Set<Integer> successeurEnsemble(Set<Integer> etatsActuels, char c) {
        Set<Integer> etatsSuivants = new HashSet<Integer>();

        // On fait l'union des successeurs de chaque état de etatsActuels.
        for (Integer id : etatsActuels) {
            Set<Integer> transition = etats[id].succ(c);
            if (transition != null)
                etatsSuivants.addAll(transition);
        }

        return etatsSuivants;
    }

    // Algorithme efficace d'acceptation d'un mot
    boolean accepte(String mot) {
        Set<Integer> etatsActuels = new HashSet<Integer>();
        for (Etat e : getEtats())
            if (e.estInitial)
                etatsActuels.add(e.getId());

        for (int i = 0; i < mot.length(); ++i) {
            etatsActuels = successeurEnsemble(etatsActuels, mot.charAt(i));
        }

        for (Integer id : etatsActuels)
            if (etats[id].estAcceptant)
                return true;
        return false;
    }

}
