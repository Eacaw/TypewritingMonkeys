package com.davidpinchen;
import java.io.IOException;


public class Main {

    // Set these two variables to choose the size of the population
    // and also the phrase that you would like to try and evolve
    // population size must be divisible by 4 - protection is in place
    private static int populationSize = 1500;
    private static String targetString = "To be or not to be, that is the question";
    private static int fitnessExponent = 2;
    private static float mutationRate = 5f;
    private static int maxAverageIterations = 25;
    private static int elitism = 25;


    private static char[] targetPhrase = targetString.toCharArray();
    private static double targetLength = (double) targetString.length();


    public static void main(String[] args) {

        // OutputFileDetails
        String path = "PopulationTestingOutput.tsv";
        WriteFile outputData = new WriteFile(path, true);

        // Output target Phrase
        System.out.println("------------------");
        System.out.println("Target phrase is:");
        System.out.println(targetString);
        System.out.println("------------------");

        // Variables for computational time analysis
        double totalTime = 0;
        int totalGenerations = 0;
        double averageTime = 0;
        int averageGenerations = 0;

        // Elitism Variation Testing
        //for(int elitismMultiplier = 1; elitismMultiplier < 11; elitismMultiplier++) {
            //elitism = 24 + (elitismMultiplier);


            // Test conditions for a range of population sizes
            for (int i = 0; i < 26; i++) {
                mutationRate = i;// * 500;
                DNA[] population = new DNA[populationSize];


//                System.out.println("---- Population Size: " + populationSize + " ----");
//                StringBuilder outputLine = new StringBuilder("---- Population Size: " + populationSize + " ----");
//                try {
//                    outputData.writeToFile(outputLine.toString());
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }


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

                    while (!complete) {
                        population = GALoop.performSingleGALoop(population, fitnessExponent, mutationRate, targetPhrase, elitism);

                        if (population[0].fitness == Math.pow(targetLength, fitnessExponent)) {
                            complete = true;
                        }
                        count++;
                    }

                    totalGenerations += count;

                    long totaltimeEnd = System.currentTimeMillis();
                    int interationTime = (int) (totaltimeEnd - totaltimestart);

                    totalTime += interationTime;

                    // --------------------------- END ----------------------------------//
                    //-------------------------------------------------------------------//

                    // Display Progress bar for each averaging test
                    StringBuilder progress = new StringBuilder("|");
                    for (int pro = 0; pro < (averageTestIteration + 1); pro++) {
                        progress.append("=");
                    }
                    for (int pro = 0; pro < (maxAverageIterations - averageTestIteration); pro++) {
                        progress.append(" ");
                    }
                    progress.append("|\r");
                    System.out.print(progress.toString());

                    // End Progress Bar

                } // End Averaging Testing
                averageTime = (int) totalTime / maxAverageIterations;
                averageGenerations = (int) totalGenerations / maxAverageIterations;
                System.out.println(mutationRate + "\t" + averageGenerations + "\t" + averageTime);
                StringBuilder outputLine2 = new StringBuilder(mutationRate + "\t" + averageGenerations + "\t" + averageTime);
                try {
                    outputData.writeToFile(outputLine2.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                //Reset Counters
                totalTime = 0;
                totalGenerations = 0;

            } // End of Different population size testing



       // } // End Elitism Change Testing

    }

    /**
     * Generate a fully random list as initial population.
     */
    private static DNA[] generateInitialPopulation() {
        DNA[] population = new DNA[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = new DNA(targetPhrase, fitnessExponent);
            GALoop.evaluateFitness(population[i],fitnessExponent,targetPhrase);
        }

        population = GALoop.sortPopulation(population);

        return population;
    }

}

