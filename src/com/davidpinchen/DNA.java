package com.davidpinchen;

import java.text.DecimalFormat;
import static java.lang.Math.random;

class DNA implements Comparable<DNA> {

    // DNA objects have two variables
    // Gene's are the objects array of characters
    // Fitness is the comparison of the genes to
    // the target phrase
    private char[] genes;
    double fitness = 0;
    private double targetFitness;
    private int fitnessExponent;

    /**
     * Constructor to generate a random sequence of characters
     */
    DNA(char[] targetPhrase, int fitnessExponent){
        this.genes = new char[targetPhrase.length];
        for (int i = 0; i < genes.length; i++){
            genes[i] = (char) ((random() * 128) + 32);
        }
        this.fitnessExponent = fitnessExponent;
        this.targetFitness = Math.pow(targetPhrase.length, fitnessExponent);
    }

    /**
     * @return - array of chars
     */
    private char[] getGenes() {
        return genes;
    }

    /**
     * Compare the genes to the target phrase
     * to provide a fitness score
     * @param targetPhrase -
     */
    void evaluateFitness(char[] targetPhrase, int fitnessExponent){
        this.fitness = 0;
        for (int i = 0; i < this.getGenes().length; i++){
            if (this.getGenes()[i] == targetPhrase[i]){
                this.fitness++;
            }
        }
        this.fitness = Math.pow(this.fitness, fitnessExponent);
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
        String fitnessPercentage = new DecimalFormat("#.##").format((fitnessNormalise));

        return "" + currentDNA + "\t\t\t Fitness: " + fitnessPercentage + "%";
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

    /**
     * Using a random midpoint, breed with
     * crossover from current instance and second
     * parent DNA
     * @param parent - secondParent
     * @return - Child DNA from crossover breeding
     */
    //TODO Find a new crossover function to improve the GA
    DNA crossover(DNA parent){
        DNA child = new DNA(this.genes, this.fitnessExponent);

        // Random midpoint selected to ensure variation
        int midpoint = (int) (random() * genes.length);

        for (int i = 0; i < genes.length; i++){
            if (i > midpoint){
                child.genes[i] = this.genes[i];
            } else {
                child.genes[i] = parent.genes[i];
            }
        }
        return child;
    }

    /**
     * Mutate each character based
     * on mutation rate percentage
     * @param mutationRate - percentage chance for mutation
     */
    void mutate(float mutationRate){
        for (int i = 0; i < genes.length; i++){
            if (random() <  mutationRate / 100){
                genes[i] = (char) ((random() * 128) + 32);
            }
        }
    }

}
