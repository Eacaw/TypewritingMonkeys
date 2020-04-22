package com.davidpinchen;
import static java.lang.Math.random;

public class Selection {


    public static MatingPartners randomParentSelection(DNA[] population){

            // Select two different indices for the parents
            int parentAIndex = (int) Math.floor((random() * population.length));
            int parentBIndex = parentAIndex;
            while(parentAIndex == parentBIndex){
                parentBIndex = (int) Math.floor((random() * population.length));
            }

            MatingPartners parents = new MatingPartners();

            parents.partnerA = population[parentAIndex];
            parents.partnerB = population[parentBIndex];

            return parents;
        }

    public static MatingPartners elitismSelection(DNA[] population, int elitismPercentage){

        double populationDivisor = 100 / elitismPercentage;

        // Select two different indices for the parents
        int parentAIndex = (int) Math.floor((random() * (population.length / populationDivisor)));
        int parentBIndex = parentAIndex;
        while(parentAIndex == parentBIndex){
            parentBIndex = (int) Math.floor((random() * (population.length / populationDivisor)));
        }

        MatingPartners parents = new MatingPartners();

        parents.partnerA = population[parentAIndex];
        parents.partnerB = population[parentBIndex];

        return parents;
    }






}
