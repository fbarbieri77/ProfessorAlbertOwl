package com.etherealapps.professorowlsum0_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import model.Owl;
import model.Restriction;
import model.RestrictionFactory;


public class MainActivity extends AppCompatActivity {

    private Owl albertOwl;
    private TextView owlGreetings;
    private VideoView owlVideo;
    private TextView number1;
    private TextView number2;
    private TextView mathOperator;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestrictionFactory restrictionFactory = new RestrictionFactory();
        List<Restriction> levels;
        try {
            levels = restrictionFactory.create();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

        owlGreetings = (TextView)findViewById(R.id.owlSays);
        owlVideo = (VideoView)findViewById(R.id.owlVideo);
        number1 = (TextView)findViewById(R.id.number1);
        number2 = (TextView)findViewById(R.id.number2);
        mathOperator = (TextView)findViewById(R.id.mathOperator);
        result = (TextView)findViewById(R.id.result);
        albertOwl = new Owl(levels, owlGreetings, owlVideo);
    }

    public void sendAnswer(View view) {
        Button b = (Button)view;
        String answer = b.getText().toString();
        albertOwl.checkAnswer(Integer.parseInt(answer));
        albertOwl.updateOwlState();
    }

    public void onNextButtonClick(View view) {
        albertOwl.nextProblem();
        albertOwl.updateOwlState();
        showProblem();
    }

    private void showProblem()
    {
        if (albertOwl.isTheEnd())
        {
            number1.setText("X");
            number2.setText("?");
            mathOperator.setText("X");
            result.setText("??");
        }
        else
        {
            number1.setText(albertOwl.number1());
            number2.setText(albertOwl.number2());
            mathOperator.setText(albertOwl.mathOperator());
            result.setText("");
        }
    }
}