package com.davidpinchen;

import java.util.Arrays;

public class GALoop {

    public static DNA[] performSingleGALoop(DNA[] population, int fitnessExponent, float mutationRate, char[] targetPhrase, int elitism){

        int populationSize = population.length;

        DNA[] newGeneration = new DNA[populationSize];

        // Ensure that the current generation has the most accurate
        // Fitness Score and sort the list accordingly
        for (int i = 0; i < populationSize; i++){
            evaluateFitness(population[i], fitnessExponent, targetPhrase);
        }
        population = sortPopulation(population);

        //Loop child creation until population is back to size
        for(int populationIndex = 0; populationIndex < populationSize; populationIndex++){
            // Select the parents -- uncomment as needed to test
            //MatingPartners parents = Selection.randomParentSelection(population); // Random Parents from population
            MatingPartners parents = Selection.elitismSelection(population, 30);


            // Perform Crossover on parents --  uncomment as needed to test
            DNA childAgent = Crossover.randomMidPointCrossover(parents); // Random Midpoint Crossover
            //DNA childAgent = Crossover.actualMidPointCrossover(parents);

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
