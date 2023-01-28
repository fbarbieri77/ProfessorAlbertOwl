package com.etherealapps.professorowlsum0_10;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
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
    private TextView number1;
    private TextView number2;
    private TextView mathOperator;
    private TextView result;
    private Button clickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestrictionFactory restrictionFactory = new RestrictionFactory();
        List<Restriction> levels;
        try {
            levels = restrictionFactory.create();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        TextView owlGreetings = findViewById(R.id.owlSays);
        VideoView owlVideo = findViewById(R.id.owlVideo);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        mathOperator = findViewById(R.id.mathOperator);
        result = findViewById(R.id.result);
        albertOwl = new Owl(levels, owlGreetings, owlVideo);
    }

    public void sendAnswer(View view) {
        if (albertOwl.isAwake() && !albertOwl.gotAnswer()) {
            clickedButton = (Button)view;
            String answer = clickedButton.getText().toString();
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(com.google.android.material.R.color.material_dynamic_primary60)));
            albertOwl.checkAnswer(Integer.parseInt(answer));
            albertOwl.updateOwlState();
        }
    }

    public void onNextButtonClick(View view) {
        albertOwl.nextProblem();
        albertOwl.updateOwlState();
        showProblem();
        if (clickedButton != null) {
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(com.google.android.material.R.color.material_dynamic_primary20)));
        }

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