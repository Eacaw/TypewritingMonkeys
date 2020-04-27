package com.davidpinchen;
import java.util.ArrayList;

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

        // Elitism Divisor to shrink population to elite only
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

    public static MatingPartners MatingPoolSelection(ArrayList<DNA> matingPool, DNA[] population){

        // Select two different indices for the parents
        int parentAIndex = (int) Math.floor((random() * matingPool.size()));
        int parentBIndex = parentAIndex;
        while(parentAIndex == parentBIndex){
            parentBIndex = (int) Math.floor((random() * matingPool.size()));
        }

        MatingPartners parents = new MatingPartners();

        parents.partnerA = matingPool.get(parentAIndex);
        parents.partnerB = matingPool.get(parentBIndex);

        return parents;
    }

    public static MatingPartners EliteMatingPoolSelection(ArrayList<DNA> matingPool, DNA[] population, int elitismPercentage){

        int matingPoolDivisor = 100 / elitismPercentage;

        // Select two different indices for the parents
        int parentAIndex = (int) Math.floor((random() * (matingPool.size() / matingPoolDivisor)));
        int parentBIndex = parentAIndex;
        while(parentAIndex == parentBIndex){
            parentBIndex = (int) Math.floor((random() * (matingPool.size() / matingPoolDivisor)));
        }

        MatingPartners parents = new MatingPartners();

        parents.partnerA = matingPool.get(parentAIndex);
        parents.partnerB = matingPool.get(parentBIndex);

        return parents;
    }








}
