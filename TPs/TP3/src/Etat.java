import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Etat {
    private final int id;
    public boolean estInitial;
    public boolean estAcceptant;
    private Map<Character, Set<Integer>> transitions = new HashMap<Character, Set<Integer>>();

    public Etat(int id, boolean initial, boolean acceptant) {
        this.id = id;
        this.estInitial = initial;
        this.estAcceptant = acceptant;
    }

    public int getId() {
        return id;
    }

    public final boolean equals(Object e){
        if(!(e instanceof Etat))
            return false;
        return (((Etat)e).id == id);
    }

    public final int hashCode(){
        return id;
    }

    // Ensemble des lettres étiquetant une transition sortante.
    public Set<Character> alphabet() {
        return this.transitions.keySet();
    }

    // pour l'affichage
    public String toString() {
        String s = "Etat " + id;
        if(estInitial)
            s+=" (initial)";
        if(estAcceptant)
            s+=" (acceptant)";
        s += ":\n";
        for (char c : this.alphabet()) {
            for (int id: this.transitions.get(c))
            s += c + " --> " + id + "\n";
        }
        return s;
    }

    // Retourne null si c n'étiquette aucune transition.
    public Set<Integer> succ(char c) {
        return transitions.get(c);
    }

    // Ajoute une transition de l'état actuel vers l'état avec l'id i,
    // étiquetée par la lettre c
    public void ajouteTransition(char c, int i) {
        Set<Integer> es = transitions.get(c);
        if (es == null)
        {
            es = new HashSet<Integer>();
            transitions.put(c, es);
        }
        es.add(i);
    }

    // retourne le nombre de transitions sortant de l'état
    int nombreTransitions() {
        int res = 0;
        for (Set<Integer> succ: transitions.values())
            res += succ.size();
        return res;
    }

}
