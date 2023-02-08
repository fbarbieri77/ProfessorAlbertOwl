package com.etherealapps.professorowlsum0_10.domain;

import com.etherealapps.professorowlsum0_10.data.model.Owl;
import com.etherealapps.professorowlsum0_10.data.model.OwlState;
import com.etherealapps.professorowlsum0_10.data.model.Problem;
import com.etherealapps.professorowlsum0_10.data.model.Restriction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//aqui vao as regras de negocio que nao tem haver com tela
public class ProblemUseCase {

    public void getNextProblem(Owl albertOwl, Integer counter, List<Problem> problems) {

        if (counter == problems.size()) {
            albertOwl.setState(OwlState.Bye);
        } else if (counter > problems.size()) {
            for (Problem p : problems) {
                p.setSolved(false);
                p.setHasTried(false);
            }
            counter = 0;
        }

        if (counter < problems.size()) {
            albertOwl.setCurrentProblem(problems.get(counter));
            albertOwl.setState(OwlState.Waiting);
        }
    }

    public List<Problem> createProblems(List<Restriction> levels) {
        List<Problem> response = new ArrayList<>();
        for (Restriction level : levels) {
            List<Integer> number1List = IntStream.range(level.minNumber, level.maxNumber + 1)
                    .boxed().collect(Collectors.toList());
            List<Integer> number2List = IntStream.range(level.minNumber, level.maxNumber + 1)
                    .boxed().collect(Collectors.toList());
            Collections.shuffle(number2List);
            for (Integer number1 : number1List) {
                for (Integer number2 : number2List) {
                    Problem problem = new Problem(level.mathOperation, number1, number2);
                    if (level.areValidNumbers(problem)) {
                        response.add(problem);
                    }
                }
            }
        }
//        return response; just to see it finish
        return response.subList(0, 5);
    }
}
