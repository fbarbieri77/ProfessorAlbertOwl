package com.etherealapps.professorowlsum0_10.presentation;

import android.widget.TextView;
import android.widget.VideoView;

import androidx.lifecycle.ViewModel;

import com.etherealapps.professorowlsum0_10.data.RestrictionFactory;
import com.etherealapps.professorowlsum0_10.data.model.Owl;
import com.etherealapps.professorowlsum0_10.data.model.OwlState;
import com.etherealapps.professorowlsum0_10.data.model.Problem;
import com.etherealapps.professorowlsum0_10.data.model.Restriction;
import com.etherealapps.professorowlsum0_10.domain.ProblemUseCase;

import java.util.ArrayList;
import java.util.List;

// aqui vao suas regras de negocio referente a  visualizacao
public class ProblemViewModel extends ViewModel {

    private final Owl albertOwl;
    private final List<Problem> problems;
    private final ProblemUseCase problemUseCase;

    public ProblemViewModel() {
        problemUseCase = new ProblemUseCase();
        List<Restriction> levels;
        albertOwl = new Owl();
        try {
            levels = new RestrictionFactory().create();
        } catch (Exception e) {
            levels = new ArrayList<>();
            albertOwl.setState(OwlState.Error);
        }
        problems = problemUseCase.createProblems(levels);
    }

    public void updateOwlState(TextView owlSays, VideoView owlDisplays) {
        OwlState state = albertOwl.getState();
        switch (state) {
            case Hello:
            case Yeah:
            case Error:
                owlSays.setText(state.displayText);
                owlDisplays.suspend();
                owlDisplays.setVideoURI(albertOwl.getOwlExpressions().get(state.name()));
                owlDisplays.start();
                break;
            case Ohoh:
                owlSays.setText(String.format(state.displayText, albertOwl.getCurrentProblem().solution()));
                owlDisplays.suspend();
                owlDisplays.setVideoURI(albertOwl.getOwlExpressions().get(state.name()));
                owlDisplays.start();
                break;
            case Bye:
            case Waiting:
                Integer solvedFinal = Math.toIntExact(problems.stream().filter(Problem::solved).count());
                Integer notSolvedFinal = problemUseCase.getExecutedProblems() - solvedFinal;
                owlSays.setText(String.format(state.displayText, solvedFinal, notSolvedFinal));
                owlDisplays.suspend();
                owlDisplays.setVideoURI(albertOwl.getOwlExpressions().get(state.name()));
                owlDisplays.start();
                break;
        }
    }


    public Boolean isAwake() {
        return problemUseCase.getExecutedProblems() != -1;
    }

    public Owl getAlbertOwl() {
        return albertOwl;
    }

    public void nextProblem() {
        problemUseCase.getNextProblem(albertOwl, problems);
    }
}
