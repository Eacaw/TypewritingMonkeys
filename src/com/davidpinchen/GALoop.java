package com.davidpinchen;

import java.util.ArrayList;
import java.util.Arrays;

public class GALoop {

    public static DNA[] performSingleGALoop(DNA[] population, int fitnessExponent, float mutationRate, char[] targetPhrase, int elitism){

        int populationSize = population.length;

        DNA[] newGeneration = new DNA[populationSize];

        // Generate the mating pool for linear fitness selection
//        ArrayList<DNA> matingPoolLinearFitness = new ArrayList<>();
//        for(int popIndex = 0; popIndex < populationSize; popIndex++){
//            int DNAFitness = (int) population[popIndex].fitness;
//            for(int fitnessCount = 0; fitnessCount < DNAFitness; fitnessCount++){
//                matingPoolLinearFitness.add(population[popIndex]);
//            }
//        }

        // Generate the mating pool for linear rank selection
        ArrayList<DNA> matingPoolLinearRank = new ArrayList<>();
        for(int popIndex = 0; popIndex < populationSize; popIndex++){
            int DNAFitness = populationSize - popIndex;
            for(int fitnessCount = 0; fitnessCount < DNAFitness; fitnessCount++){
                matingPoolLinearRank.add(population[popIndex]);
            }
        }


        //Loop child creation until population is back to size
        for(int populationIndex = 0; populationIndex < populationSize; populationIndex++){


            // Select the parents -- uncomment as needed to test
            //MatingPartners parents = Selection.randomParentSelection(population); // Random Parents from population
            MatingPartners parents = Selection.elitismSelection(population, elitism); // Random parents from elite selection
            //MatingPartners parents = Selection.MatingPoolSelection(matingPoolLinearFitness, population); // Random parents from mating pool based on fitness score
            //MatingPartners parents = Selection.MatingPoolSelection(matingPoolLinearRank, population); // Random parents from mating pool based on population rank
            //MatingPartners parents = Selection.EliteMatingPoolSelection(matingPoolLinearFitness, population, elitism); // Random parents from mating pool based on fitness and elitist value
            //MatingPartners parents = Selection.EliteMatingPoolSelection(matingPoolLinearRank, population, elitism); // Random parents from mating pool based on population rank and elitist value



            // Perform Crossover on parents --  uncomment as needed to test
//            DNA childAgent = Crossover.randomMidPointCrossover(parents); // Random Midpoint Crossover
//            DNA childAgent = Crossover.actualMidPointCrossover(parents);
//            DNA childAgent = Crossover.DoublePointCrossover(parents);
//            DNA childAgent = Crossover.InterleavedCrossover(parents);
            DNA childAgent = Crossover.RandomSelectionCrossover(parents);


            // Perform Mutation on the child
            childAgent = Mutation.mutate(childAgent,mutationRate);

            // Evaluate the fitness of the child
            evaluateFitness(childAgent, fitnessExponent, targetPhrase);

            // Add child to the new population
            newGeneration[populationIndex] = childAgent;

        }

        // Sort the new Generation
        newGeneration = sortPopulation(newGeneration);

        return newGeneration;
    }



    /**
     * Sort the population Array based on their fitness score
     */
    static DNA[] sortPopulation(DNA[] population) {
        Arrays.sort(population, DNA::compareTo);
        return population;
    }

    /**
     * Compare the genes to the target phrase
     * to provide a fitness score
     */
    protected static void evaluateFitness(DNA agent, int fitnessExponent, char[] targetPhrase){
        agent.fitness = 0;
        for (int i = 0; i < agent.genes.length; i++){
            if (agent.genes[i] == targetPhrase[i]){
                agent.fitness++;
                //System.out.println("AddFitness");
            }
        }
        agent.fitness = Math.pow(agent.fitness, fitnessExponent);
    }

}
