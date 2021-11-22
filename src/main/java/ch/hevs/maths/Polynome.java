package ch.hevs.maths;

public class Polynome {

    private final static int MODULO = 257;
    private byte[] bytes;
    private int[] coefficients;
    private int[] xCoordinates;
    private int[] yCoordinates;
    private int nbParts;
    private int threshold;
    private int degree;

    public Polynome(int bytes, int nbParts, int threshold)
    {
        this.nbParts = nbParts;
        this.threshold = threshold;

        degree = threshold - 1;
        coefficients = new int[degree];
        xCoordinates = new int[nbParts];
        yCoordinates = new int[nbParts];
    }

    public void generateCoefficient()
    {
        // @TODO secureRandom()
        for (int i = 0; i < coefficients.length; i++)
        {
            coefficients[i] = (int) Math.random() * modulo + 1;
        }

    }

    public void generatePolynome()
    {
        // @TODO génère les valeurs de x et f(x) pour un polynome
    }

    public int[] generateParts()
    {
        // génére les parts pour une personne
        for (int i = 0; i < modulo; i++)
        {
            generatePolynome();
        }
    }

}
