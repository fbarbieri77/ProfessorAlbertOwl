package com.etherealapps.professorowlsum0_10.data.model;

public abstract class Restriction {
    public String name;
    public MathOperation mathOperation;
    public Integer minNumber;
    public Integer maxNumber;
    public Integer minSolution;
    public Integer maxSolution;

    public Boolean areValidNumbers(Problem problem) {
        if (problem.solution() >= minSolution && problem.solution() <= maxSolution) {
            if (mathOperation == MathOperation.Division) {
                return problem.number1() != 0
                        && problem.number2() != 0
                        && problem.number1() % problem.number2() == 0;
            }
            return true;
        }
        return false;
    }
}

