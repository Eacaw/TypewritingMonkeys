package com.davidpinchen;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class Main {

    // Set these two variables to choose the size of the population
    // and also the phrase that you would like to try and evolve
    // population size must be divisible by 4 - protection is in place
    private static int populationSize = 512;
    private static String targetString = "To be or not to be, that is the question";
    private static int fitnessExponent = 2;
    private static float mutationRate = 5f;
    private static int maxAverageIterations = 10;
    private static int elitism = 20;


    private static char[] targetPhrase = targetString.toCharArray();
    private static double targetLength = (double) targetString.length();


    // OutputFileDetails

    private static String path = "output.tsv";

    public static void main(String[] args) {

        WriteFile outputData = new WriteFile(path, true);

        System.out.println("------------------");
        System.out.println("Target phrase is:");
        System.out.println(targetString);
        System.out.println("------------------");


        double totalTime = 0;
        int totalGenerations = 0;
        double averageTime = 0;
        int averageGenerations = 0;

        for(int elitismMultiplier = 1; elitismMultiplier < 10; elitismMultiplier++) {
            elitism = 5 * elitismMultiplier;
            System.out.println("---- Elitism Value: " + elitism + " ----");
            StringBuilder outputLine = new StringBuilder("---- Elitism Value: " + elitism + " ----");
            try {
                outputData.writeToFile(outputLine.toString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // Test conditions for a range of population sizes
            for (int i = 7; i < 21; i++) {
                populationSize = (int) Math.pow(2, i);
                DNA[] population = new DNA[populationSize];


                // Run each population size test 10 times and take averages
                for (int averageTestIteration = 0; averageTestIteration < maxAverageIterations; averageTestIteration++) {

                    // -----------------------------BEGIN ------------------------//
                    // -----------------------------------------------------------//
                    // boolean to exit the genetic algorithm loop
                    boolean complete = false;
                    // Generation Counter
                    int count = 0;
                    // Start Generation Timer
                    long totaltimestart = System.currentTimeMillis();


                    population = generateInitialPopulation();

                    for (int j = 0; j < populationSize; j++) {
                        GALoop.evaluateFitness(population[j], fitnessExponent, targetPhrase);
                    }

                    population = GALoop.sortPopulation(population);

                    //printPopulation();

                    while (!complete) {

                        population = GALoop.performSingleGALoop(population, fitnessExponent, mutationRate, targetPhrase, elitism);


                        //printPopulation();
                        //System.out.println("Gen " + count + ": " + population[0].toString());
                        if (population[0].fitness == Math.pow(targetLength, fitnessExponent)) {
                            complete = true;
                        }
                        count++;


                    }

                    totalGenerations += count;

                    //System.out.print(populationSize);
                    //System.out.print("\t" + count);

                    long totaltimeEnd = System.currentTimeMillis();
                    int interationTime = (int) (totaltimeEnd - totaltimestart);

                    totalTime += interationTime;


                    //System.out.print("\t" + totalTime + "\n");

                    // --------------------------- END ----------------------------------//
                    //-------------------------------------------------------------------//

                    // Display Progress bar for each averaging test
                    StringBuilder progress = new StringBuilder("|");
                    for (int pro = 0; pro < (averageTestIteration + 1); pro++) {
                        progress.append("=");
                    }
                    for (int pro = 0; pro < (10 - averageTestIteration); pro++) {
                        progress.append(" ");
                    }
                    progress.append("|\r");
                    System.out.print(progress.toString());

                    // End Progress Bar

                } // End Averaging Testing
                averageTime = (int) totalTime / maxAverageIterations;
                averageGenerations = (int) totalGenerations / maxAverageIterations;
                System.out.println(populationSize + "\t" + averageGenerations + "\t" + averageTime);
                StringBuilder outputLine2 = new StringBuilder(populationSize + "\t" + averageGenerations + "\t" + averageTime);
                try {
                    outputData.writeToFile(outputLine2.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                totalTime = 0;
                totalGenerations = 0;
            } // End Different population size testing



        } // End Elitism Change Testing

    }

    /**
     * Generate a fully random list as initial population.
     */
    private static DNA[] generateInitialPopulation() {
        DNA[] population = new DNA[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = new DNA(targetPhrase, fitnessExponent);
        }
        return population;
    }

}

