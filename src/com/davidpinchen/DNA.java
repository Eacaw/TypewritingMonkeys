package com.davidpinchen;

        import static java.lang.Math.random;

class DNA {

    private char[] genes = new char[40];

    DNA(){
        for (int i = 0; i < genes.length; i++){
            genes[i] = (char) ((random() * 128) + 32);
        }
        System.out.println(genes);
    }

    public char[] getGenes() {
        return genes;
    }
}
