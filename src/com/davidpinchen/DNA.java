package com.davidpinchen;

import java.math.BigDecimal;
import static java.lang.Math.random;

public class DNA implements Comparable<DNA> {

    // DNA objects have two variables
    // Gene's are the objects array of characters
    // Fitness is the comparison of the genes to
    // the target phrase
    public char[] genes;
    double fitness = 0;
    double targetFitness;
    public int fitnessExponent;
    public char[] targetPhrase;

    /**
     * Constructor to generate a random sequence of characters
     */
    DNA(char[] targetPhrase, int fitnessExponent){
        this.targetPhrase = targetPhrase;
        this.genes = new char[targetPhrase.length];
        for (int i = 0; i < genes.length; i++){
            genes[i] = (char) ((random() * 128) + 32);
        }
//        System.out.println(genes);

        this.fitnessExponent = fitnessExponent;
        this.targetFitness = Math.pow(targetPhrase.length, fitnessExponent);
    }

    /**
     * @return - array of chars
     */
    protected char[] getGenes() {
        return genes;
    }



    /**
     * @return - integer fitness score
     */
    private double getFitness() {
        return fitness;
    }

    /**
     * @return - custom Array to String conversion
     */
    @Override
    public java.lang.String toString(){
        StringBuilder builder = new StringBuilder();
        for (char gene : this.getGenes()){
            builder.append(gene);
        }
        String currentDNA = builder.toString();

        double fitnessNormalise = ((this.fitness / this.targetFitness) * 100);
        BigDecimal fitnessPercentage = truncateDecimal(fitnessNormalise,2);

        return "Fitness: " + fitnessPercentage + "% \t - \t " + currentDNA;
    }

    // Method credit: https://stackoverflow.com/users/2182351/mani
    private static BigDecimal truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }

    /**
     * Comparator Interface method
     * @param o - Comparatee DNA
     * @return - New Child DNA
     */
    @Override
    public int compareTo(DNA o) {
        return (int) o.getFitness() - (int) this.getFitness();
    }





}
