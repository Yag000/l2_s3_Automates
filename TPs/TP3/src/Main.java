import java.util.HashSet;
import java.util.Set;

//TODO: add tests
public class Main {

    private static Automate getAutomateAB() {
        Automate a = new Automate(2);
        a.ajouteTransition(0, 0, 'a');
        a.ajouteTransition(0, 1, 'b');
        a.ajouteTransition(1, 1, 'b');
        a.setInitial(0, true);
        a.setAcceptant(1, true);
        return a;
    }

    private static Automate getAutomateNonDeterministe() {
        Automate a = new Automate(3);
        a.ajouteTransition(0, 0, 'a');
        a.ajouteTransition(0, 1, 'a');
        a.ajouteTransition(0, 0, 'b');

        a.ajouteTransition(1, 2, 'a');

        a.ajouteTransition(2, 2, 'a');
        a.ajouteTransition(2, 2, 'b');

        a.setInitial(0, true);
        a.setAcceptant(2, true);
        return a;
    }

    public static void exemple() {

        System.out.println("--------------------------");
        System.out.println("Exemple:");
        Automate a = getAutomateAB();

        System.out.println(a);
        System.out.println(a.accepte(""));
        System.out.println(a.accepte("ab"));
        System.out.println(a.accepte("babaa"));
        System.out.println(a.accepte("aaabbb"));
        // l'algorithme d'acceptation est efficace
        System.out.println(
                a.accepte(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));

        System.out.println("--------------------------");
        System.out.println("Test de remplaceAParC:");
        Automate c = remplaceAParC(a); // Donne un automate pour c*b+
        System.out.println(c);
        System.out.println(c);
        System.out.println(c.accepte("ab"));
        System.out.println(c.accepte("cb"));
        System.out.println(c.accepte("cab"));
        System.out.println(c.accepte("ccbbb"));

    }

    // Exemple:
    // remplace les 'a' par des 'c' dans toutes les transitions de l'automate a1
    public static Automate remplaceAParC(Automate a1) {
        Automate a2 = new Automate(a1.nbEtats());

        for (int i = 0; i < a1.nbEtats(); ++i) {
            Etat e = a1.getEtat(i);

            // On copie les états initiaux / acceptants
            a2.setInitial(i, e.estInitial);
            a2.setAcceptant(i, e.estAcceptant);

            // On copie les transitions
            for (char c : e.alphabet()) {
                for (int id : e.succ(c)) {
                    if (c == 'a')
                        // Si la transition est étiquetée par un 'a', on remplace par la même avec 'c'
                        a2.ajouteTransition(e.getId(), id, 'c');
                    else
                        a2.ajouteTransition(e.getId(), id, c);
                }
            }
        }

        return a2;
    }

    // Calcul de l'automate reconnaissant le miroir de a1
    public static Automate automateMiroir(Automate a1) {
        int taille = a1.nbEtats();
        Automate a2 = new Automate(taille);

        for (int i = 0; i < taille; ++i) {

            Etat e = a1.getEtat(i);

            for (char c : e.alphabet()) {
                for (int id : e.succ(c)) {
                    a2.ajouteTransition(id, e.getId(), c);
                }
            }

            a2.setInitial(i, e.estAcceptant);
            a2.setAcceptant(i, e.estInitial);

        }

        return a2;
    }

    public static void testMiroir() {
        System.out.println("--------------------------");
        System.out.println("Test de l'automate miroir:");
        Automate a = getAutomateAB();
        a = automateMiroir(a);

        System.out.println(a);
        System.out.println(a.accepte("")); // false
        System.out.println(a.accepte("ab")); // false
        System.out.println(a.accepte("babaa")); // false
        System.out.println(a.accepte("aaabbb")); // false
        System.out.println(
                a.accepte(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")); // false
        System.out.println();
        System.out.println(a.accepte("")); // false
        System.out.println(a.accepte("b")); // true
        System.out.println(a.accepte("bbaa")); // true
        System.out.println(a.accepte("bab")); // false
    }

    // Calcul d'un automate correspondant à l'automate a1 complété
    public static Automate automateComplete(Automate a1) {
        int taille = a1.nbEtats();
        boolean isComplete = a1.estComplet();
        Automate a2 = isComplete ? new Automate(taille) : new Automate(taille + 1);

        for (int i = 0; i < taille; ++i) {
            Etat e = a1.getEtat(i);

            // On copie les états initiaux / acceptants
            a2.setInitial(i, e.estInitial);
            a2.setAcceptant(i, e.estAcceptant);

            // On copie les transitions
            for (char c : a1.alphabet()) {
                if (e.alphabet().contains(c)) {
                    for (int id : e.succ(c))
                        a2.ajouteTransition(e.getId(), id, c);
                } else {
                    a2.ajouteTransition(e.getId(), taille, c);

                }

            }
        }

        if (!isComplete)
            for (char c : a1.alphabet())
                a2.ajouteTransition(taille, taille, c);

        return a2;
    }

    public static void testComplete() {
        System.out.println("--------------------------");
        System.out.println("Test de l'automate complété:");
        // création d'un automate avec deux états pour a*b+
        Automate a = getAutomateAB();
        a = automateComplete(a);

        System.out.println(a);
        System.out.println(a.estComplet());
        System.out.println();
        System.out.println(a.accepte(""));
        System.out.println(a.accepte("ab"));
        System.out.println(a.accepte("babaa"));
        System.out.println(a.accepte("aaabbb"));
    }

    /********** Déterminisation **********/
    public static int ensembleVersEntier(Set<Integer> s) {
        int res = 0;

        for (int i : s)
            res += 1 << i;

        return res;
    }

    public static Set<Integer> entierVersEnsemble(int i) {
        var res = new HashSet<Integer>();

        for (int j = 0; j < 32; j++)
            if ((i & (1 << j)) != 0)
                res.add(j);

        return res;
    }

    public static void testEntierEnsemble() {

        System.out.println("----------Test ensembleVersEntier----------");
        var setTest = new HashSet<Integer>();
        System.out.println(Integer.toBinaryString(ensembleVersEntier(setTest)));
        setTest.add(0);
        System.out.println(Integer.toBinaryString(ensembleVersEntier(setTest)));
        setTest.add(2);
        setTest.add(3);
        System.out.println(Integer.toBinaryString(ensembleVersEntier(setTest)));
        setTest.add(8);
        setTest.add(17);
        setTest.add(10);
        System.out.println(Integer.toBinaryString(ensembleVersEntier(setTest)));

        System.out.println("----------Test entierVersEnsemble----------");

        System.out.println(entierVersEnsemble(0b0));
        System.out.println(entierVersEnsemble(0b11));
        System.out.println(entierVersEnsemble(0b10));
        System.out.println(entierVersEnsemble(0b11100));
        System.out.println(entierVersEnsemble(0b0000101101));
        System.out.println(entierVersEnsemble(0b1111));

    }

    public static int getNextEtat(Automate a, int e, char c) {
        var nextEtat = new HashSet<Integer>();

        for (int id : entierVersEnsemble(e)) {
            var successors = a.getEtat(id).succ(c);
            if (successors != null) {
                nextEtat.addAll(successors);
            }
        }

        return ensembleVersEntier(nextEtat);
    }

    public static void updateEtat(int id, Automate a1, Automate a2, Set<Integer> acceptantStates) {

        for (int i : entierVersEnsemble(id)) {
            if (acceptantStates.contains(i)) {
                a2.setAcceptant(id, true);
                break;
            }
        }

        for (char c : a1.alphabet()) {
            int nextEtat = getNextEtat(a1, id, c);

            if (nextEtat != 0) {
                a2.ajouteTransition(id, nextEtat, c);

                if (a2.getEtat(nextEtat).alphabet().size() == 0)
                    updateEtat(nextEtat, a1, a2, acceptantStates);
            }

        }
    }

    // Calcul de l'automate déterministe correspondant à l'automate a1

    public static Automate automateDeterministe(Automate a1) {
        Automate a2 = new Automate(1 << a1.nbEtats());

        var initialStates = new HashSet<Integer>();
        var acceptantStates = new HashSet<Integer>();

        for (Etat e : a1.getEtats()) {
            if (e.estInitial)
                initialStates.add(e.getId());
            if (e.estAcceptant)
                acceptantStates.add(e.getId());
        }

        updateEtat(ensembleVersEntier(initialStates), a1, a2, acceptantStates);
        return a2;

    }

    public static void testDeterministe() {
        System.out.println("--------------------------");
        System.out.println("Test de l'automate déterministe:");
        Automate a1 = getAutomateNonDeterministe();
        System.out.println(a1);

        Automate a2 = automateDeterministe(a1);
        System.out.println(a2);

    }

    /********** Complémentaire **********/
    // Calcul de l'automate complémentaire de l'automate a1
    public static Automate automateComplementaire(Automate a1) {
        Automate a2 = a1.estDeterministe() ? a1 : automateDeterministe(a1);
        a2 = automateComplete(a2);

        for (Etat e : a2.getEtats())
            a2.setAcceptant(e.getId(), !e.estAcceptant);

        return a2;
    }

    public static void testComplementaire() {
        System.out.println("--------------------------");
        System.out.println("Test de l'automate déterministe:");
        Automate a1 = getAutomateNonDeterministe();
        System.out.println(a1);

        Automate a2 = automateComplementaire(a1);
        System.out.println(a2);

    }

    /********** Automate Produit **********/
    // Calcul de l'automate produit de a1 et a2
    public static Automate automateProduit(Automate a1, Automate a2) {
        a1 = a1.estDeterministe() ? a1 : automateDeterministe(a1);
        a2 = a2.estDeterministe() ? a2 : automateDeterministe(a2);

        a1 = a1.estComplet() ? a1 : automateComplete(a1);
        a2 = a2.estComplet() ? a2 : automateComplete(a2);

        int nbEtats = a1.nbEtats() * a2.nbEtats();
        Automate a3 = new Automate(nbEtats);

        int m = a2.nbEtats();

        for (int id = 0; id < nbEtats; id++) {
            Etat e1 = a1.getEtat(id / m);
            Etat e2 = a2.getEtat(id % m);

            for (char c : a1.alphabet()) {

                Set<Integer> nextEtat1 = e1.succ(c);
                Set<Integer> nextEtat2 = e2.succ(c);

                if (nextEtat1 != null && nextEtat2 != null) {
                    int nextEtat = ensembleVersEntier(nextEtat1) * m + ensembleVersEntier(nextEtat2);
                    a3.ajouteTransition(id, nextEtat, c);
                }
            }
        }

        return a3;
    }

    /********** Intersection **********/
    // Calcul de l'automate reconnaissant l'intersection de L(a1) et L(a2)
    public static Automate automateIntersection(Automate a1, Automate a2) {
        Automate a3 = automateProduit(a1, a2);

        for (Etat e : a3.getEtats()) {
            if (e.estAcceptant) {
                int id1 = e.getId() / a2.nbEtats();
                int id2 = e.getId() % a2.nbEtats();

                if (a1.getEtat(id1).estAcceptant && a2.getEtat(id2).estAcceptant)
                    a3.setAcceptant(e.getId(), true);
            }
        }

        return a3;
    }

    public static void testIntersection() {
        System.out.println("--------------------------");
        System.out.println("Test de l'automate intersection:");
        System.out.println("A faire !");
    }

    public static void main(String[] args) {
        exemple();
        // Pensez à tester vos fonctions !
        testMiroir();
        testComplete();
        testEntierEnsemble();
        testDeterministe();
        testComplementaire();
        testIntersection();
    }
}
