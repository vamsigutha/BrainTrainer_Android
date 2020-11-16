package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView pointsTextView;
    TextView resultTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button1;
    Button button0;
    Button button2;
    Button button3;
    Button playAgainButton;
    boolean canGenerateQuestion = true;

    public void playAgain(View view){

        score=0;
        numberOfQuestions=0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        canGenerateQuestion=true;

        generateQuestion();

        button0.setEnabled(true);
        button0.setClickable(true);

        button1.setEnabled(true);
        button1.setClickable(true);

        button2.setEnabled(true);
        button2.setClickable(true);

        button3.setEnabled(true);
        button3.setClickable(true);

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Done !!!");
                playAgainButton.setVisibility(View.VISIBLE);
                canGenerateQuestion = false;
                button0.setEnabled(false);
                button0.setClickable(false);

                button1.setEnabled(false);
                button1.setClickable(false);

                button2.setEnabled(false);
                button2.setClickable(false);

                button3.setEnabled(false);
                button3.setClickable(false);
            }
        }.start();



    }

    public void generateQuestion(){
        answers.clear(); //this line make sures that previous options to be cleared and new ones stores, otherwise options value don't change

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer= rand.nextInt(41);

                while(incorrectAnswer==a+b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score +=1;
            resultTextView.setText("Correct !!!");
        }else{
            resultTextView.setText("Wrong !!!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

        if(canGenerateQuestion){
            generateQuestion();
        }
    }


    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView  = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton  =(Button) findViewById(R.id.playAgainButton);





        playAgain(findViewById(R.id.playAgainButton));




    }
}