package model;

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
                if (problem.number1() != 0
                        && problem.number2() != 0
                        && problem.number1() % problem.number2() == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}

class Level0_sum extends Restriction {
    public Level0_sum() {
        name = this.getClass().getName();
        mathOperation = MathOperation.Sum;
        minNumber = 0;
        maxNumber = 10;
        minSolution = 0;
        maxSolution = 10;
    }
}