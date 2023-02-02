package com.etherealapps.professorowlsum0_10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.etherealapps.professorowlsum0_10.presentation.ProblemViewModel;


public class MainActivity extends AppCompatActivity {

    ProblemViewModel problemViewModel;
    private TextView number1;
    private TextView number2;
    private TextView mathOperator;
    private TextView result;
    private Button clickedButton;
    private Bundle savedInstanceState;
    TextView owlGreetings;
    VideoView owlVideo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        problemViewModel = new ViewModelProvider(this).get(ProblemViewModel.class);


        owlGreetings = findViewById(R.id.owlSays);
        owlVideo = findViewById(R.id.owlVideo);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        mathOperator = findViewById(R.id.mathOperator);
        result = findViewById(R.id.result);

        findViewById(R.id.owlVideo).setOnClickListener(nextVideoListener());
        findViewById(R.id.owlSays).setOnClickListener(nextVideoListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        problemViewModel.updateOwlState(owlGreetings, owlVideo);
    }

    private View.OnClickListener nextVideoListener() {
        return view -> {
            problemViewModel.nextProblem();
            problemViewModel.updateOwlState(owlGreetings, owlVideo);
            showProblem();
     /*   if (clickedButton != null) {
        clickedButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(com.google.android.material.R.color.material_dynamic_primary20)));
             }
     */
            //    app:backgroundTint="@color/material_dynamic_primary20"
        };
    }

    public void sendAnswer(View view) {
        if (problemViewModel.isAwake() && !problemViewModel.getAlbertOwl().gotAnswer()) {
            // convert to local variable if not changing background
            clickedButton = (Button) view;
            String answer = clickedButton.getText().toString();
            //   clickedButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(com.google.android.material.R.color.material_dynamic_primary60)));
            result.setText(answer);
            problemViewModel.getAlbertOwl().checkAnswer(Integer.parseInt(answer));
            problemViewModel.updateOwlState(owlGreetings, owlVideo);
        }
    }

    private void showProblem() {
        if (problemViewModel.getAlbertOwl().isTheEnd()) {
            number1.setText("X");
            number2.setText("?");
            mathOperator.setText("X");
            result.setText("??");
        } else {
            number1.setText(problemViewModel.getAlbertOwl().number1());
            number2.setText(problemViewModel.getAlbertOwl().number2());
            mathOperator.setText(problemViewModel.getAlbertOwl().mathOperator());
            result.setText("");
        }
    }
}