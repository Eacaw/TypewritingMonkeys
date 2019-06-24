package com.davidpinchen;

        import java.util.ArrayList;
        import java.util.Arrays;

public class Main {


    private static int populationSize = 100;
    private static DNA[] population = new DNA[populationSize];
    private static String targetString = "To be or not to be, that is the question!";
    private static char[] targetPhrase = targetString.toCharArray();
    private static int targetLength = targetString.length();

    private static ArrayList<DNA> matingPool = new ArrayList<>();

    private static int generation = 0;

    public static void main(String[] args) {
        System.out.println("TypewritingMonkeys");
        System.out.println("------------------");
        System.out.println("Target phrase is:");
        System.out.println(targetString);
        System.out.println("------------------");
        generateInitialPopulation(targetPhrase);

        boolean complete = false;

        while(!complete) {
            evaluateFitness(targetPhrase);
            sortPopulation();
            System.out.println(population[0].toString());
            if (population[0].fitness == targetLength) {
                System.out.println("Completed in " + generation + " generations");
                complete = true;
            }
            fillMatingPool();
            breedNextGeneration();
            generation++;
        }
        System.out.println("------------------");

    }

    private static void generateInitialPopulation(char[] targetPhrase){
        for (int i = 0; i < population.length; i++){
            population[i] = new DNA(targetLength);
        }
    }

    private static void evaluateFitness(char[] targetPhrase){
        for (DNA dna : population) {
            dna.evaluateFitness(targetPhrase);
        }
    }

    private static void sortPopulation(){
        Arrays.sort(population, DNA::compareTo);
    }

    private static void fillMatingPool(){
        matingPool.clear();
        int oddValueCatch = (population.length / 2) % 2; // Catch uneven numbers to ensure mating pool always has an even population
        matingPool.addAll(Arrays.asList(population).subList(0, (population.length/2) - oddValueCatch));
    }

    private static void breedNextGeneration(){
        DNA[] nextGeneration = new DNA[populationSize];
        int currentIndex = 0;
        for (int i = 0; i < (matingPool.size() / 2); i ++){
            for (int j = 0; j < 4; j++) {
                DNA child = population[2 * i].crossover(population[(2 * i) + 1]);
                int mutationRate = 1; // percentage amount for mutations to occur, 1 - 100
                child.mutate(mutationRate);
                nextGeneration[currentIndex] = child;
                currentIndex++;
            }
        }
        population = nextGeneration;
    }

}

