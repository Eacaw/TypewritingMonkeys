package com.davidpinchen;

import static java.lang.Math.random;

public class Mutation {

    /**
     * Mutate each character based
     * on mutation rate percentage
     * @param mutationRate - percentage chance for mutation
     */
    public static DNA mutate(DNA agent, float mutationRate){
        for (int i = 0; i < agent.genes.length; i++){
            if (random() <  mutationRate / 100){
                agent.genes[i] = (char) ((random() * 128) + 32);
            }
        }
        return agent;
    }

}
