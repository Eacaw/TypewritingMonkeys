package com.davidpinchen;

import static java.lang.Math.random;

class DNA implements Comparable<DNA> {

    // DNA objects have two variables
    // Gene's are the objects array of characters
    // Fitness is the comparison of the genes to
    // the target phrase
    private char[] genes;
    int fitness = 0;

    /**
     * Constructor to generate a random sequence of characters
     * @param targetLength - length of target phrase
     */
    DNA(int targetLength){
        this.genes = new char[targetLength];
        for (int i = 0; i < genes.length; i++){
            genes[i] = (char) ((random() * 128) + 32);
        }
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
    void evaluateFitness(char[] targetPhrase){
        this.fitness = 0;
        for (int i = 0; i < this.getGenes().length; i++){
            if (this.getGenes()[i] == targetPhrase[i]){
                this.fitness++;
            }
        }
    }

    /**
     * @return - integer fitness score
     */
    private int getFitness() {
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

        return "" + currentDNA + "\t\t Fitness: " + this.fitness;
    }

    /**
     * Comparator Interface method
     * @param o - Comparatee DNA
     * @return - New Child DNA
     */
    @Override
    public int compareTo(DNA o) {
        return o.getFitness() - this.getFitness();
    }

    /**
     * Using a random midpoint, breed with
     * crossover from current instance and second
     * parent DNA
     * @param parent - secondParent
     * @return - Child DNA from crossover breeding
     */
    DNA crossover(DNA parent){
        DNA child = new DNA(this.genes.length);

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
