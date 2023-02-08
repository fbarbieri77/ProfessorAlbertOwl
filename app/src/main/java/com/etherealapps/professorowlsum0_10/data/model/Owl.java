package com.etherealapps.professorowlsum0_10.data.model;

import android.net.Uri;

import com.etherealapps.professorowlsum0_10.R;

import java.util.Dictionary;
import java.util.Hashtable;

public class Owl {

    private OwlState state;
    private Problem currentProblem;
    private final Dictionary<String, Uri> owlExpressions;

    public Owl() {
        state = OwlState.Hello;
        owlExpressions = new Hashtable<>();
        owlExpressions.put(OwlState.Hello.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_hello4));
        owlExpressions.put(OwlState.Bye.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_bye4));
        owlExpressions.put(OwlState.Ohoh.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_ohoh4));
        owlExpressions.put(OwlState.Waiting.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_wait4));
        owlExpressions.put(OwlState.Yeah.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_yeah4));

        owlExpressions.put(OwlState.Error.name(),
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_ohoh4));

    }

    public Boolean gotAnswer() {
        return currentProblem.hasTried();
    }

    private Boolean isCorrect(Integer answer) {
        if (!currentProblem.hasTried()) {
            currentProblem.setSolved(answer.equals(currentProblem.solution()));
        }
        return currentProblem.solved();
    }

    public void checkAnswer(Integer answer) {
        if (currentProblem.hasTried() || isTheEnd())
            return;

        if (isCorrect(answer)) {
            state = OwlState.Yeah;
        } else {
            state = OwlState.Ohoh;
        }
    }

    public Boolean isTheEnd() {
        return state == OwlState.Bye;
    }

    public String number1() {
        return currentProblem.number1().toString();
    }

    public String number2() {
        return currentProblem.number2().toString();
    }

    public String mathOperator() {
        return currentProblem.mathOperator();
    }

    public Problem getCurrentProblem() {
        return currentProblem;
    }

    public void setCurrentProblem(Problem currentProblem) {
        this.currentProblem = currentProblem;
    }

    public Dictionary<String, Uri> getOwlExpressions() {
        return owlExpressions;
    }

    public OwlState getState() {
        return state;
    }

    public void setState(OwlState state) {
        this.state = state;
    }
}

