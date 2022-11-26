import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.text.html.HTMLDocument.RunElement;

import java.util.List;
import java.util.ArrayList;

public class Etat {
	private final int id;
	private final boolean etatAcc;
	private Map<Character, Set<Etat>> transitions = new HashMap<Character, Set<Etat>>();

	public Etat(int id, boolean etatAcc) {
		this.id = id;
		this.etatAcc = etatAcc;
	}

	public int getId() {
		return id;
	}

	public boolean estAcceptant() {
		return etatAcc;
	}

	public final boolean equals(Object e) {
		if (!(e instanceof Etat))
			return false;
		return (((Etat) e).id == id);
	}

	public final int hashCode() {
		return id;
	}

	// Ensemble des lettres étiquetant une transition sortante.
	public Set<Character> alphabet() {
		return this.transitions.keySet();
	}

	// pour l'affichage
	public String toString() {
		String s = "Etat " + id;
		if (estAcceptant())
			s += " (acceptant)";
		s += "\n";
		for (char c : this.alphabet()) {
			for (Etat e : this.transitions.get(c))
				s += c + " " + e.id + "\n";
		}
		return s;
	}

	// Retourne null si c n'étiquette aucune transition.
	public Set<Etat> succ(char c) {
		return transitions.get(c);
	}

	// Si c étiquetait déjà une transition, l'ancienne valeur est remplacée par e.
	public void ajouteTransition(char c, Etat e) {
		Set<Etat> es = new HashSet<Etat>();
		if (transitions.get(c) != null)
			es.addAll(transitions.get(c));
		es.add(e);
		transitions.put(c, es);
	}

	// premier algorithme d'acceptation d'un mot
	boolean accepte(String mot) {
		// si on a fini de lire
		if (mot.length() == 0)
			// c'est accepté si état acceptant
			return estAcceptant();
		else { // on suit les transition possibles de la première lettre
			Set<Etat> es = this.succ(mot.charAt(0));
			// si pas de transition c'est un échec
			if (es == null)
				return false;
			else {
				for (Etat e : es)
					if (e.accepte(mot.substring(1)))
						return true;
				return false;
			}
		}
	}

	int nombreTransitions() {
		int nb = 0;
		for (char c : alphabet()) {
			nb += transitions.get(c).size();
		}
		return nb;
	}

	boolean estDeterministe() {
		for (char c : alphabet()) {
			if (transitions.get(c).size() > 1)
				return false;
		}
		return true;
	}

	/**
	 * Removes all the transitions going to e
	 * 
	 * @param e
	 */
	void enleverTransitions(Etat e) {
		for (char c : alphabet()) {
			Set<Etat> es = transitions.get(c);
			es.remove(e);
			transitions.put(c, es);
		}

	}

	void enleverTransitions(Set<Etat> e) {
		for (Etat etat : e) {
			enleverTransitions(etat);
		}

	}

	void enleverTransitions(int id) {
		for (char c : alphabet()) {
			Set<Etat> es = transitions.get(c);
			for (Etat e : es) {
				if (e.getId() == id) {
					es.remove(e);
					break;
				}
			}
			transitions.put(c, es);
		}
	}

	void enleverTransitions(char c, Etat e) {
		System.out.println(c);
		System.out.println(e);
		transitions.get(c).remove(e);
	}

	void enleverTransitions(char c, Set<Etat> se) {
		for (Etat e : se)
			transitions.get(c).remove(e);
	}

	public int getEtatsNonCoAccessibles(int[] tab) {

		if (tab[id] == 1)
			return 1;

		if (etatAcc) {
			tab[id] = 1;
			return 1;
		}

		for (char c : alphabet()) {
			for (Etat e : succ(c)) {
				if (e != this) {
					switch (tab[e.id]) {
						case 0:
							int res = e.getEtatsNonCoAccessibles(tab);
							tab[id] = res == 1 ? 1 : tab[id];
							break;
						case 1:
							tab[id] = 1;
							return 1;

						case -1:
							break;
					}
				}
			}

		}
		if (tab[id] == 0)
			tab[id] = -1;

		return tab[id];

	}

}
