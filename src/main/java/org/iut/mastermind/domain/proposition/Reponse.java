package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        if(resultat.size() <= position){
            return Lettre.NON_PLACEE;
        }
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        resultat.clear();

        position = 0;

        while(this.position < essai.length()) {
            resultat.add(evaluationCaractere(essai.charAt(position), position));
            position++;
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(c -> c.equals(Lettre.PLACEE));
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    private Lettre evaluationCaractere(char carCourant, int position) {
        if (estPlace(carCourant, position)) return Lettre.PLACEE;
        else if (estPresent(carCourant)) return Lettre.NON_PLACEE;
        else return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return motSecret.chars().anyMatch(c -> c == carCourant);
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant, int position) {
        return motSecret.charAt(position) == carCourant;
    }
}
