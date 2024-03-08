package com.example.api_spring;

import lombok.AllArgsConstructor;
import lombok.Data;

//Créer le constructeur
@Data
@AllArgsConstructor
public class Salutations {
    //final veut dire que le champs doit être initialisé dans le constructeur.
    // On les met à jour qu'une seule fois (readonly)
    private final String langue;
    private final String message;

    // Si on avait pas AllArgsConstructor on aurait du écrire le constructeur à la main
    // public Salutations(String langue, String message) {
    //     this.langue = langue;
    //     this.message = message;
    // }
}
