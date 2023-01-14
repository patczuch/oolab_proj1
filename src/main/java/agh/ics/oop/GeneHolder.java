package agh.ics.oop;

public class GeneHolder {
    MoveDirection gene;  // modyfikator dostępu
    int counter;  // modyfikator dostępu

    GeneHolder(MoveDirection gene, int counter) {
        this.gene = gene;
        this.counter = counter;
    }
}
