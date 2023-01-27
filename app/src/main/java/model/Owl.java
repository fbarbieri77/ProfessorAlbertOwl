package model;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.TextView;
import android.widget.VideoView;

import com.etherealapps.professorowlsum0_10.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Owl {

    private TextView owlSays;
    private VideoView owlDisplays;
    private Integer counter;
    private OwlState state;
    private Problem currentProblem;
    private List<Problem> problems;
    private Dictionary<String, Uri> owlFile;

    public Owl(List<Restriction> levels, TextView text, VideoView video) {
        owlSays = text;
        owlDisplays = video;
        problems = new ArrayList<>();
        createProblems(levels);

        counter = -1;
        state = OwlState.Waiting;
        owlSays.setText("I am Mr. Albert Owl");

        owlFile = new Hashtable<>();
        owlFile.put("Hello",
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_hello4));
        owlFile.put("Bye",
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_bye4));
        owlFile.put("Ohoh",
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_ohoh4));
        owlFile.put("Waiting",
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_wait4));
        owlFile.put("Yeah",
                Uri.parse("android.resource://com.etherealapps.professorowlsum0_10/"
                        + R.raw.profowl_yeah4));

        owlDisplays.setVideoURI(owlFile.get("Hello"));
        owlDisplays.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        owlDisplays.start();
    }

    private void createProblems(List<Restriction> levels) {
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
                        problems.add(problem);
                    }
                }
            }
        }
    }

    public void nextProblem() {
        counter++;
        if (counter == problems.stream().count())
        {
            state = OwlState.Bye;
        }
        else if (counter > problems.stream().count())
        {
            for (Problem p : problems) {
                p.setSolved(false);
                p.setHasTried(false);
            }
            counter = 0;
        }

        if (counter < problems.stream().count())
        {
            currentProblem = problems.get(counter);
            state = OwlState.Waiting;
        }
    }

    private Boolean isCorrect(Integer answer) {
        if (currentProblem.hasTried() == false) {
            currentProblem.setSolved(answer == currentProblem.solution());
        }
        return currentProblem.solved();
    }

    public void checkAnswer(Integer answer) {
        if (currentProblem.hasTried() || isTheEnd())
            return;

        if (isCorrect(answer))
        {
            state = OwlState.Yeah;
        }
        else
        {
            state = OwlState.Ohoh;
        }
    }

    public void updateOwlState() {
        switch (state)
        {
            case Yeah:
                owlSays.setText("Ehhh");
                owlDisplays.setVideoURI(owlFile.get("Yeah"));
                break;
            case Ohoh:
                owlSays.setText(String.format("Oh oh...  \u2260 %s", currentProblem.solution()));
                owlDisplays.setVideoURI(owlFile.get("Ohoh"));
                break;
            case Bye:
                Integer solvedFinal = Math.toIntExact(problems.stream().filter(Problem::solved).count());
                Integer notSolvedFinal = counter - solvedFinal;
                owlSays.setText(String.format("Bye Bye  -  \u2713 %d  :  \u2717 %d", solvedFinal, notSolvedFinal));
                owlDisplays.setVideoURI(owlFile.get("Bye"));
                break;
            default:
                Integer solved = Math.toIntExact(problems.stream().filter(Problem::solved).count());
                Integer notSolved = counter - solved;
                owlSays.setText(String.format("\u2713 %d  :  \u2717 %d", solved, notSolved));
                owlDisplays.setVideoURI(owlFile.get("Waiting"));
                break;
        }
    }

    public Boolean isTheEnd()
    {
        return state == OwlState.Bye;
    }

    public String number1()
    {
        return currentProblem.number1().toString();
    }

    public String number2()
    {
        return currentProblem.number2().toString();
    }
    public String mathOperator()
    {
        return currentProblem.mathOperator();
    }
}

enum OwlState
{
    Waiting,
    Yeah,
    Ohoh,
    Bye
}