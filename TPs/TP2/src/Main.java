import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void testAutomate0() {
		Etat[] etats = new Etat[3];
		etats[0] = new Etat(0, false);
		etats[1] = new Etat(1, false);
		etats[2] = new Etat(2, true);
		etats[0].ajouteTransition('a', etats[1]);
		etats[0].ajouteTransition('a', etats[2]);
		etats[1].ajouteTransition('a', etats[1]);
		etats[1].ajouteTransition('c', etats[1]);
		etats[1].ajouteTransition('b', etats[2]);
		Automate a = new Automate(etats[0]);
		System.out.println(a);
		System.out.println("L'automate a " + a.nombreTransitions() + " transitions.");
		System.out.println("L'alphabet est : " + a.alphabet());
		System.out.println("L'automate " + (a.estDeterministe() ? "est" : "n'est pas") + " déterministe.");

		String s = "aaab";
		System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		Set<Etat> s1 = new HashSet<Etat>();
		s1.add(etats[0]);
		Set<Etat> s2 = new HashSet<Etat>();
		s2.add(etats[1]);
		s2.add(etats[2]);
		Set<Etat> s3 = new HashSet<Etat>();
		s3.add(etats[2]);
		s3.add(etats[1]);
		s3.add(etats[2]);

		System.out.println(" s1 == s2 ? " + s1.equals(s2));
		System.out.println(" s2 == s3 ? " + s2.equals(s3));

	}

	public static void testAutomate1() {
		// écrire ici le premier automate de l'énoncé
		Etat[] etats = new Etat[11];

		etats[0] = new Etat(0, false);
		etats[1] = new Etat(1, false);
		etats[2] = new Etat(2, false);
		etats[3] = new Etat(3, false);
		etats[4] = new Etat(4, false);
		etats[5] = new Etat(5, false);
		etats[6] = new Etat(6, false);
		etats[7] = new Etat(7, false);
		etats[8] = new Etat(8, false);
		etats[9] = new Etat(9, false);
		etats[10] = new Etat(10, true);

		etats[0].ajouteTransition('a', etats[0]);
		etats[0].ajouteTransition('a', etats[1]);
		etats[0].ajouteTransition('b', etats[0]);

		for (int i = 1; i < 10; i++) {
			etats[i].ajouteTransition('a', etats[i + 1]);
			etats[i].ajouteTransition('b', etats[i + 1]);
		}

		Automate a = new Automate(etats[0]);
		System.out.println("------- Automate 2 -------");
		System.out.println(a);
		System.out.println("L'automate a " + a.nombreTransitions() + " transitions.");
		System.out.println("L'alphabet est : " + a.alphabet());
		System.out.println("L'automate " + (a.estDeterministe() ? "est" : "n'est pas") + " déterministe.");

		System.out.println();

		String s = "aaab";
		System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "babbbbbbaba";
		System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "babbbbbaba";
		System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

	}

	public static void testAutomate2() {
		// écrire ici le second automate de l'énoncé
		Etat[] etats = new Etat[31];

		for (int i = 0; i < 30; i++) {
			etats[i] = new Etat(i, false);

		}
		etats[30] = new Etat(30, true);

		for (int i = 0; i < 30; i++) {
			etats[i].ajouteTransition('a', etats[i]);
			etats[i].ajouteTransition('b', etats[i]);

			etats[i].ajouteTransition('a', etats[i + 1]);
			etats[i].ajouteTransition('b', etats[i + 1]);

		}

		etats[30].ajouteTransition('a', etats[30]);
		etats[30].ajouteTransition('b', etats[30]);

		Automate a = new Automate(etats[0]);
		System.out.println("------- Automate 2 -------");
		System.out.println(a);
		System.out.println("L'automate a " + a.nombreTransitions() + " transitions.");
		System.out.println("L'alphabet est : " + a.alphabet());
		System.out.println("L'automate " + (a.estDeterministe() ? "est" : "n'est pas") + " déterministe.");

		System.out.println();

		String s = "a".repeat(29);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "ab".repeat(15);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "aba".repeat(15);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

	}

	public static void testAutomate3() {
		// écrire ici le second automate de l'énoncé
		Etat[] etats = new Etat[32];

		for (int i = 0; i < 32; i++) {
			etats[i] = new Etat(i, false);

		}
		etats[31] = new Etat(31, true);

		for (int i = 0; i < 30; i++) {
			etats[i].ajouteTransition('a', etats[i]);
			etats[i].ajouteTransition('b', etats[i]);

			etats[i].ajouteTransition('a', etats[i + 1]);
			etats[i].ajouteTransition('b', etats[i + 1]);

		}

		etats[29].ajouteTransition('a', etats[31]);
		etats[29].ajouteTransition('b', etats[31]);
		etats[29].ajouteTransition('c', etats[30]);

		etats[30].ajouteTransition('a', etats[30]);

		etats[31].ajouteTransition('a', etats[31]);
		etats[31].ajouteTransition('b', etats[31]);

		Automate a = new Automate(etats[0]);
		System.out.println("------- Automate 3 -------");
		System.out.println(a);
		System.out.println("L'automate a " + a.nombreTransitions() + " transitions.");
		System.out.println("L'alphabet est : " + a.alphabet());
		System.out.println("L'automate " + (a.estDeterministe() ? "est" : "n'est pas") + " déterministe.");

		System.out.println("------- Automate 3' -------");
		// a.printCO();
		a.enleverEtatsNonCoAccessibles();
		// a.test();
		System.out.println(a);
		System.out.println("L'automate a " + a.nombreTransitions() + " transitions.");
		System.out.println("L'alphabet est : " + a.alphabet());
		System.out.println("L'automate " + (a.estDeterministe() ? "est" : "n'est pas") + " déterministe.");

		System.out.println();

		String s = "a".repeat(29);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "ab".repeat(15);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));

		s = "aba".repeat(15);
		// System.out.println("Mot " + s + " accepté ? " + a.accepte(s));
		System.out.println("Mot " + s + " accepté2 ? " + a.accepte2(s));
	}

	public static void main(String[] args) {
		testAutomate0();
		testAutomate1();
		testAutomate2();
		testAutomate3();

		/*
		 * Question 5:
		 * 
		 * L'automate A1 et A2 ne sont pas déterministes, ci qui implique que le temps
		 * de calcul est trop élevé pour des input larges, contrairement aux automates
		 * déterministes qui ont une complexité linéaire.
		 */
	}
}
