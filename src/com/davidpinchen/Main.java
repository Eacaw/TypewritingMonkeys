package com.davidpinchen;

        import java.util.ArrayList;
        import java.util.Arrays;

public class Main {

    // Set these two variables to choose the size of the population
    // and also the phrase that you would like to try and evolve
    // population size must be divisible by 4 - protection is in place
    private static int populationSize = 4000;
    private static String targetString = "To be or not to be, that is the question!";
    private static int fitnessExponent = 2;


    private static DNA[] population = new DNA[populationSize - (populationSize % 4)];
    private static char[] targetPhrase = targetString.toCharArray();
    private static double targetLength = (double) targetString.length();

    private static ArrayList<DNA> matingPool = new ArrayList<>();

    private static int generation = 0;

    public static void main(String[] args) {

        System.out.println("------------------");
        System.out.println("Target phrase is:");
        System.out.println(targetString);
        System.out.println("------------------");

        // boolean to exit the genetic algorithm loop
        boolean complete = false;

        // Generate the initial population of
        // completely random strings of characters
        generateInitialPopulation();

        // GA Loop
        while(!complete) {
            // Apply a fitness score to each member of the population
            evaluateFitness(targetPhrase);
            // Sort the population by fitness
            sortPopulation();
            // Output the best scoring member from each generation
            System.out.println(population[0].toString()); // Comment this line out to speed up the program
            // Check to see if the top scoring member has the correct value (max fitness)
            if (population[0].fitness == Math.pow(targetLength,fitnessExponent)) {
                System.out.println("Completed in " + generation + " generations");
                // Exit loop
                complete = true;
            }
            // Select the parents for the next generation
            fillMatingPool();
            // Breed the generation to create the next generation
            breedNextGeneration();
            // Increment the generation counter
            generation++;
        }
    }

    /**
     * Generate a fully random list as initial population.
     */
    private static void generateInitialPopulation(){
        for (int i = 0; i < population.length; i++){
            population[i] = new DNA(targetPhrase, fitnessExponent);
        }
    }

    /**
     * Apply a fitness score to each member of the population
     * @param targetPhrase - phrase that the GA is trying to reach
     */
    private static void evaluateFitness(char[] targetPhrase){
        for (DNA dna : population) {
            dna.evaluateFitness(targetPhrase, fitnessExponent);
        }
    }

    /**
     * Sort the population Array based on their fitness score
     */
    private static void sortPopulation(){
        Arrays.sort(population, DNA::compareTo);
    }

    /**
     * Fill the mating pool with the top scoring half of the population
     * from the current generation
     */
    private static void fillMatingPool(){
        matingPool.clear();
        int oddValueCatch = (population.length / 2) % 2; // Catch uneven numbers to ensure mating pool always has an even population
        matingPool.addAll(Arrays.asList(population).subList(0, (population.length/2) - oddValueCatch));
    }

    /**
     * Breed the next generation of the population
     */
    private static void breedNextGeneration(){
        DNA[] nextGeneration = new DNA[populationSize - (populationSize % 4)];
        int currentIndex = 0;
        for (int i = 0; i < (matingPool.size() / 2); i ++){
            for (int j = 0; j < 4; j++) {
                DNA child = population[2 * i].crossover(population[(2 * i) + 1]);
                float mutationRate = 1.5f; // percentage amount for mutations to occur, 1 - 100
                child.mutate(mutationRate);
                nextGeneration[currentIndex] = child;
                currentIndex++;
            }
        }
        // Replace the initial population with the next generation for further processing
        population = nextGeneration;
    }

}

