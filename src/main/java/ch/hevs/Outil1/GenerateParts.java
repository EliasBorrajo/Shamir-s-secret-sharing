package ch.hevs.Outil1;

/**
 * 1) Génerer les parts de secrets et en obtenir un fichier.
 *      - Entrées   : #Bytes (modulo) / #Parts (10 employés) / Seuil (k  <= # Parts)
 *      - Sortie    : #Fichier en fonction de #Parts. Fichier JSON contenant les parts de secrets
 *      - Process   : Le degrée du polynome sera = seuil k-1, et nous génerera les parts a0, a1,..., an-1
 *                    Puis on évalue f(x) = ax^2 + bx +c (dépend du degré) --> pour X
 */

/**
 * OUTIL 1 :
 * Ces calculs auront besoin de la classe dans MATHS pour faire ces opérations !
 *
 * Methode pour calculer : Li(x) = (x-xi)/(xm-xi)
 *
 * Methode pour calculer : f(x) = y = secret = SOMME( Li(x) * yi )
 */


public class GenerateParts
{
}
