package com.davidpinchen;

import static java.lang.Math.random;

public class Crossover {

    /**
     *
     * @param parents
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

    /**
     *
     * @param parents
     * @return
     */
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

    /**
     *
     * @param parents
     * @return
     */
    public static DNA DoublePointCrossover(MatingPartners parents){

        DNA parentA = parents.partnerA;
        DNA parentB = parents.partnerB;

        DNA child = new DNA(parentA.genes, parentA.fitnessExponent);

        // first crossover point selected randomly
        // from the start to two from the end of the length
        int crossoverPointA = (int) (random() * (parentA.genes.length - 2));

        int remainingIndecies = (int) parentA.genes.length - crossoverPointA;

        int crossoverPointB = (int) (random() * remainingIndecies) + crossoverPointA;

        for (int i = 0; i < parentA.genes.length; i++){
            if (i < crossoverPointA || i > crossoverPointB){
                child.genes[i] = parentA.genes[i];
            } else {
                child.genes[i] = parentB.genes[i];
            }
        }
        return child;
    }

    /**
     *
     * @param parents
     * @return
     */
    public static DNA InterleavedCrossover(MatingPartners parents) {

        DNA parentA = parents.partnerA;
        DNA parentB = parents.partnerB;

        DNA child = new DNA(parentA.genes, parentA.fitnessExponent);

        for(int i = 0; i < parentA.genes.length; i++){
            if(i % 2 == 0){
                child.genes[i] = parentA.genes[i];

            } else {
                child.genes[i] = parentB.genes[i];
            }
        }

        return child;

    }

    /**
     *
     * @param parents
     * @return
     */
    public static DNA RandomSelectionCrossover(MatingPartners parents) {

        DNA parentA = parents.partnerA;
        DNA parentB = parents.partnerB;

        DNA child = new DNA(parentA.genes, parentA.fitnessExponent);

        for(int i = 0; i < parentA.genes.length; i++){

            double parentSelection = random();

            if(parentSelection > 0.5){
                child.genes[i] = parentA.genes[i];

            } else {
                child.genes[i] = parentB.genes[i];
            }
        }

        return child;

    }

}
