package com.davidpinchen;

import static java.lang.Math.random;

public class Crossover {

    /**

     * @param parent - secondParent
     * @return - Child DNA from crossover breeding
     */

    /**
     * Using a random midpoint, breed with
     * crossover from both parents genes
     * parent DNA
     * @param parentA
     * @param parentB
     * @return
     */
    public static DNA randomMidPointCrossover(MatingPartners parents){

        DNA parentA = parents.partnerA;
        DNA parentB = parents.partnerB;

        DNA child = new DNA(parentA.genes, parentA.fitnessExponent);

        int parentGenesLength = parentA.genes.length;

        // Random midpoint selected to ensure variation
        int midpoint = (int) (random() * parentGenesLength);

        for (int i = 0; i < parentGenesLength; i++){
            if (i > midpoint){
                child.genes[i] = parentA.genes[i];
            } else {
                child.genes[i] = parentB.genes[i];
            }
        }
        return child;
    }


    public static DNA actualMidPointCrossover(MatingPartners parents){

        DNA parentA = parents.partnerA;
        DNA parentB = parents.partnerB;

        DNA child = new DNA(parentA.genes, parentA.fitnessExponent);

        int crossoverPoint = (int) parentA.genes.length / 2;

        for (int i = 0; i < parentA.genes.length; i++){
            if (i > crossoverPoint){
                child.genes[i] = parentA.genes[i];
            } else {
                child.genes[i] = parentB.genes[i];
            }
        }
        return child;
    }


}
