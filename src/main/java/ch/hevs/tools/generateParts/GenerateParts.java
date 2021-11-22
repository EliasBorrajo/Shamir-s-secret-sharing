package ch.hevs.tools.generateParts;

/**
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 *      - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 *      - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 *      - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 *                    Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 */

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * OUTIL 1 : GENERATE PARTS
 * Va génerer les parts a0, a1 ......
 *
 * C'est cette classe qui av devoir faire le SHAMIR SECRET #N fois le #Bytes du tableau de bytes !
 *
 * Le tableau de bytes va avoir un modulo de 257 (premier NbrPremier au dessus de #Bytes).
 * Et va stocker les réponses des parts de secrets dans ses cases.
 * case [0] --> Part A1
 *              Part B1
 *              Part C1
 *
 * case [1] --> Part A2
 *              Part B2
 *              part C2
 *
 * etc... pour chaques cases.
 *
 * Puis tous les parts A composeront le code du fichier de la part N°1 = A,
 * puis B pour part N°2 = B etc...
 *
 */


public class GenerateParts
{

    public GenerateParts()
    {

    }

    /**
     * 1)
     */
}
