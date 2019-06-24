package com.davidpinchen;

public class Main {

    private static String targetString = "To be or not to be, that is the question!";
    private static char[] targetPhrase = targetString.toCharArray();

    private static DNA[] population = new DNA[100];
    private static int[] populationFitness = new int[100];

    public static void main(String[] args) {
        System.out.println("TypewritingMonkeys");
        System.out.println("------------------");
        System.out.println("Target phrase is:");
        System.out.println(targetString);
        System.out.println("------------------");
        setup();

    }

    private static void setup(){
        for (int i = 0; i < population.length; i++){
            population[i] = new DNA();
        }
    }

    private static void evaluateFitness(DNA populationInstance, int index){
        int fitness = 0;
        for (int i = 0; i < populationInstance.getGenes().length; i++){
            if (populationInstance.getGenes()[i] == targetPhrase[i]){
                fitness++;
            }
        }
        populationFitness[index] = fitness;
    }


}

