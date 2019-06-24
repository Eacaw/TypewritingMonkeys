package com.davidpinchen;

        import static java.lang.Math.random;

class DNA implements Comparable<DNA> {

    private char[] genes;
    public int fitness = 0;

    DNA(int targetLength){
        this.genes = new char[targetLength];
        for (int i = 0; i < genes.length; i++){
            genes[i] = (char) ((random() * 128) + 32);
        }
    }

    public char[] getGenes() {
        return genes;
    }

    void evaluateFitness(char[] targetPhrase){
        this.fitness = 0;
        for (int i = 0; i < this.getGenes().length; i++){
            if (this.getGenes()[i] == targetPhrase[i]){
                this.fitness++;
            }
        }
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public java.lang.String toString(){
        StringBuilder builder = new StringBuilder();
        for (char gene : this.getGenes()){
            builder.append(gene);
        }
        String currentDNA = builder.toString();

        return "" + currentDNA + "\t\t Fitness: " + this.fitness;
    }

    @Override
    public int compareTo(DNA o) {
        return o.getFitness() - this.getFitness();
    }

    public DNA crossover(DNA parent2){
        DNA child = new DNA(this.genes.length);

        int midpoint = (int) (random() * genes.length);

        for (int i = 0; i < genes.length; i++){
            if (i > midpoint){
                child.genes[i] = this.genes[i];
            } else {
                child.genes[i] = parent2.genes[i];
            }
        }

        return child;
    }

    public void mutate(int mutationRate){
        for (int i = 0; i < genes.length; i++){
            if (random() < (float) mutationRate / 100){
                genes[i] = (char) ((random() * 128) + 32);
            }
        }


    }


}
