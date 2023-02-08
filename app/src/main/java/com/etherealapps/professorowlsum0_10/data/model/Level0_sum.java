package com.etherealapps.professorowlsum0_10.data.model;

public class Level0_sum extends Restriction {
    public Level0_sum() {
        name = this.getClass().getName();
        mathOperation = MathOperation.Sum;
        minNumber = 0;
        maxNumber = 10;
        minSolution = 0;
        maxSolution = 10;
    }
}
