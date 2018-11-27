package com.example.pc.brain_trainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int correct_pos;
    ArrayList<Integer> answers= new ArrayList<>();
    Boolean playable = true;

    public void playAgain(View view)
    {
        display = findViewById(R.id.display);
        display.setVisibility(View.INVISIBLE);
        score_num = 0;
        outof = 0;

        playable = true;

       timerFunction();

       repeat();

    }

   public void repeat()
   {
       Random rand = new Random();

       int a = rand.nextInt(21);
       int b = rand.nextInt(21);
       int correct_ans = a + b;
       int incorrect_ans;

       correct_pos = rand.nextInt(4);

       Button button0 = findViewById(R.id.button0);
       Button button1 = findViewById(R.id.button1);
       Button button2 = findViewById(R.id.button2);
       Button button3 = findViewById(R.id.button3);

       TextView question = findViewById(R.id.question);

       question.setText(a+" + "+b);

       for(int i = 0; i<4; i++)
       {
           if(i == correct_pos)
           {
               answers.add(correct_ans);
           }
           else
           {
               incorrect_ans = rand.nextInt(21);
               while(incorrect_ans== correct_ans )
               {
                   incorrect_ans = rand.nextInt(41);
               }
               answers.add(incorrect_ans);
           }


       }

       button0.setText(Integer.toString(answers.get(0)));
       button1.setText(Integer.toString(answers.get(1)));
       button2.setText(Integer.toString(answers.get(2)));
       button3.setText(Integer.toString(answers.get(3)));

   }

    int score_num = 0;
    int outof = 0;
    TextView output;
    public void isitCorrect( View view)
    {
        if(playable) {

            output = findViewById(R.id.output);
            TextView right = findViewById(R.id.score);

            outof++;

            if (view.getTag().toString().equals(Integer.toString(correct_pos))) {
                output.setText("Correct!");
                score_num++;
            } else {
                output.setText("Wrong!");
            }
            right.setText(score_num + "/" + outof);
            answers.clear();
            repeat();
        }

    }

    LinearLayout display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repeat();

        timerFunction();

    }
    public void timerFunction()
    {
        final CountDownTimer timer = new CountDownTimer(30100,1000) {

            TextView timeleft = findViewById(R.id.timeleft);

            @Override
            public void onTick(long millisUntilFinished) {

                timeleft.setText((int) millisUntilFinished/1000 +"s");

            }

            @Override
            public void onFinish() {

                timeleft.setText("0s");
                display = findViewById(R.id.display);
                output = findViewById(R.id.output);
                output.setText("");
                display.setVisibility(View.VISIBLE);
                TextView yourscore = findViewById(R.id.yourscore);
                yourscore.setText("Your Score:"+score_num+ "/"+ outof);
                playable = false;


            }
        }.start();
    }
}
