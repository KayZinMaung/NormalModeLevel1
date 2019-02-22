package com.example.pc.testing3;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class NormalMode extends AppCompatActivity implements View.OnClickListener {
    private TextView txtOperand1;
    private TextView txtOperand2;
    private TextView txtOperator;
    private TextView txtTarget;
    private Button btnAns1;
    private Button btnAns2;
    private Button btnAns3;
    private Button btnAns4;
    private TextView timeLeft;
    private TextView questionLeft;
    private TextView txtScore;
    private int target;
    private int operand1;
    private int operand2;
    private String operator;
    private static int count=0;
    private int numOfQuestionLeft;
    int temp=0;
    private String[] operatorArr = {"+", "-", "*"};
    private int whereBlank;
    private String correctBtn;
    private static long remainingtime;
    private long timeRageToGetHighScore=5000;
    private long maxTimeRange=50000;
    private boolean isFast;
    private long RemainingtimeAfterQ1;
    private long RemainingtimeAfterQ2;
    private long RemainingtimeAfterQ3;
    private long RemainingtimeAfterQ4;
    private long RemainingtimeAfterQ5;
    private int markForFastAndCorrect=150;
    private static int score=0;
    private int markForCorrect=100;
    private int markForWrong=-20;
    private int markForFastAndWrong=-50;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createLevel1();



    }

    private void createLevel1() {
        setContentView(R.layout.activity_normal_mode);


        txtOperand1 = (TextView) findViewById(R.id.txt_operand1);
        txtOperand2 = (TextView) findViewById(R.id.txt_operand2);
        txtOperator = (TextView) findViewById(R.id.txt_operator);
        txtTarget = (TextView) findViewById(R.id.txt_target);
        btnAns1 = (Button) findViewById(R.id.btn_ans1);
        btnAns2 = (Button) findViewById(R.id.btn_ans2);
        btnAns3 = (Button) findViewById(R.id.btn_ans3);
        btnAns4 = (Button) findViewById(R.id.btn_ans4);
        timeLeft=(TextView)findViewById(R.id.time_left);
        questionLeft=(TextView)findViewById(R.id.question_left);
        txtScore = (TextView) findViewById(R.id.score);


        count=0;
        score=0;

        createQuestion();




        btnAns1.setOnClickListener(this);
        btnAns2.setOnClickListener(this);
        btnAns3.setOnClickListener(this);
        btnAns4.setOnClickListener(this);

        countDown();

        numOfQuestionLeft=4;
        questionLeft.setText("QuestionLeft:"+String.valueOf(numOfQuestionLeft+1));
        txtScore.setText("Score:"+String.valueOf(score));

    }



    private void countDown() {
        countDownTimer= new CountDownTimer(maxTimeRange, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText(" TimeLeft : " + millisUntilFinished / 1000);
                remainingtime=millisUntilFinished;

            }

            public void onFinish() {
                AlertDialog.Builder timeOutAlert = new AlertDialog.Builder(NormalMode.this);
                // Set the dialog title
                timeOutAlert.setTitle("Sorry!");
                timeOutAlert.setMessage("TimeOut!!!");
                timeOutAlert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        createLevel1();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                timeOutAlert.show();

            }

        };
       countDownTimer.start();

    }

    public void onClick(android.view.View view){


        count++;

        //fast or slow
        switch (count){

            case 1:
                RemainingtimeAfterQ1=remainingtime;
                if(RemainingtimeAfterQ1>=(maxTimeRange-timeRageToGetHighScore))
                    isFast=true;
                else
                    isFast=false;

                break;
            case 2:
                RemainingtimeAfterQ2=remainingtime;
                if(RemainingtimeAfterQ2>=(RemainingtimeAfterQ1-timeRageToGetHighScore))
                    isFast=true;
                else
                    isFast=false;

                break;
            case 3:
                RemainingtimeAfterQ3=remainingtime;
                if(RemainingtimeAfterQ3>=(RemainingtimeAfterQ2-timeRageToGetHighScore))
                    isFast=true;
                else
                    isFast=false;

                break;
            case 4:
                RemainingtimeAfterQ4=remainingtime;
                if(RemainingtimeAfterQ4>=(RemainingtimeAfterQ3-timeRageToGetHighScore))
                    isFast=true;
                else
                    isFast=false;

                break;

            case 5:
                RemainingtimeAfterQ5=remainingtime;
                if(RemainingtimeAfterQ5>=(RemainingtimeAfterQ4-timeRageToGetHighScore))
                    isFast=true;
                else
                    isFast=false;

                break;





        }





        questionLeft.setText("QuestionLeft:"+String.valueOf(numOfQuestionLeft));

        String result="" ;

        if(count<5) {
            numOfQuestionLeft--;
            Button btnObj = (Button) view;
            String toastText;
            switch (btnObj.getId()) {
                case R.id.btn_ans1:
                    if (correctBtn.equalsIgnoreCase("btnAns1")){

                        if(isFast){
                            score+=markForFastAndCorrect;
                            toastText="+"+String.valueOf(markForFastAndCorrect);
                        }


                        else{
                            score+=markForCorrect;
                            toastText="+"+String.valueOf(markForCorrect);
                        }



                    }

                    else{

                        if(isFast){
                            score+=markForFastAndWrong;
                            toastText=String.valueOf(markForFastAndWrong);
                        }

                        else{
                            score+=markForWrong;
                            toastText=String.valueOf(markForWrong);
                        }

                    }

                    Toast toastForBtn1 = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toastForBtn1.show();
                    createQuestion();
                    break;

                case R.id.btn_ans2:
                    if (correctBtn.equalsIgnoreCase("btnAns2")) {
                        if(isFast){
                            score+=markForFastAndCorrect;
                            toastText="+"+String.valueOf(markForFastAndCorrect);
                        }


                        else{
                            score+=markForCorrect;
                            toastText="+"+String.valueOf(markForCorrect);
                        }

                    }
                    else{
                        if(isFast){
                            score+=markForFastAndWrong;
                            toastText=String.valueOf(markForFastAndWrong);
                        }

                        else{
                            score+=markForWrong;
                            toastText=String.valueOf(markForWrong);
                        }
                    }

                    Toast toastForBtn2 = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toastForBtn2.show();
                    createQuestion();
                    break;

                case R.id.btn_ans3:
                    if (correctBtn.equalsIgnoreCase("btnAns3")) {
                        if(isFast){
                            score+=markForFastAndCorrect;
                            toastText="+"+String.valueOf(markForFastAndCorrect);
                        }


                        else{
                            score+=markForCorrect;
                            toastText="+"+String.valueOf(markForCorrect);
                        }

                    }
                    else
                    {
                        if(isFast){
                            score+=markForFastAndWrong;
                            toastText=String.valueOf(markForFastAndWrong);
                        }

                        else{
                            score+=markForWrong;
                            toastText=String.valueOf(markForWrong);
                        }
                    }
                    Toast toastForBtn3 = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toastForBtn3.show();
                    createQuestion();
                    break;


                case R.id.btn_ans4:
                    if (correctBtn.equalsIgnoreCase("btnAns4")){
                        if(isFast){
                            score+=markForFastAndCorrect;
                            toastText="+"+String.valueOf(markForFastAndCorrect);
                        }


                        else{
                            score+=markForCorrect;
                            toastText="+"+String.valueOf(markForCorrect);
                        }

                    }

                    else
                    {
                        if(isFast){
                            score+=markForFastAndWrong;
                            toastText=String.valueOf(markForFastAndWrong);
                        }

                        else{
                            score+=markForWrong;
                            toastText=String.valueOf(markForWrong);
                        }
                    }
                    Toast toastForBtn4 = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toastForBtn4.show();
                    createQuestion();
                    break;


            }
            txtScore.setText(String.valueOf(score));
        }
        else {
            AlertDialog.Builder showFinalScore = new AlertDialog.Builder(NormalMode.this);
            // Set the dialog title
            showFinalScore.setTitle(" ");
            showFinalScore.setMessage("Your Final Score :"+score);
            showFinalScore.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Retry
                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            showFinalScore.show();
        }






    }








    private void congrazForBtn1() {
        btnAns1.setOnClickListener(this);
    }


    private void createQuestion() {
        Random r=new Random();
        operand1=r.nextInt(20)-10;
        while(operand1==0)
            operand1=r.nextInt(20)-10;

        operand2=r.nextInt(20)-10;
        while (operand2==0)
            operand2=r.nextInt(20)-10;

        operator = operatorArr[r.nextInt(3)];
        switch (operator){
            case "+":target=operand1+operand2;
            break;

            case "-":target=operand1-operand2;
            break;

            case "*":target=operand1*operand2;
            break;


        }





            whereBlank=r.nextInt(3)+1;

            switch (whereBlank){
                case 1: BlankOperand1();
                    break;
                case 2: BlankOperand2();
                    break;
                case 3: BlankOperator();
                    break;
            }

            txtTarget.setText(String.valueOf(target));



        }

    private void setBtnWithRandomAns(int ans) {
        Random r1=new Random();
        int where=r1.nextInt(4)+1;


        int value1=r1.nextInt(20)-10;//coz containg answer as parameter is within this range

        while(value1==ans)
            value1=r1.nextInt(20)-10;

        int value2=r1.nextInt(20)-10;

        while(value2==ans||value2==value1)
            value2=r1.nextInt(20)-10;

        int value3=r1.nextInt(20)-10;

        while(value3==ans || value3==value1 || value3==value2)
            value3=r1.nextInt(20)-10;


        switch (where){
            case 1:btnAns1.setText(String.valueOf(ans));
                   btnAns2.setText(String.valueOf(value1));
                   btnAns3.setText(String.valueOf(value2));
                   btnAns4.setText(String.valueOf(value3));
                   correctBtn="btnAns1";  //note button of correct answer

            break;

            case 2:btnAns2.setText(String.valueOf(ans));
                btnAns1.setText(String.valueOf(value1));
                btnAns3.setText(String.valueOf(value2));
                btnAns4.setText(String.valueOf(value3));
                correctBtn="btnAns2";
                break;

            case 3:btnAns3.setText(String.valueOf(ans));
                btnAns2.setText(String.valueOf(value1));
                btnAns1.setText(String.valueOf(value2));
                btnAns4.setText(String.valueOf(value3));
                correctBtn="btnAns3";
                break;

            case 4:btnAns4.setText(String.valueOf(ans));
                btnAns2.setText(String.valueOf(value1));
                btnAns3.setText(String.valueOf(value2));
                btnAns1.setText(String.valueOf(value3));
                correctBtn="btnAns4";
                break;

        }
    }




    private void BlankOperator() {
        txtOperand1.setText(String.valueOf(operand1));
        txtOperand2.setText(String.valueOf(operand2));
        txtOperator.setText("?");
        btnAns1.setText("+");
        btnAns2.setText("-");
        btnAns3.setText("*");
        btnAns4.setText("/");

        //note button of correct answer
        switch (operator){
            case "+":correctBtn="btnAns1";
            break;
            case "-":correctBtn="btnAns2";
                break;
            case "*":correctBtn="btnAns3";
                break;
            case "/":correctBtn="btnAns4";
                break;


        }




    }

    private void BlankOperand2() {
        txtOperand1.setText(String.valueOf(operand1));
        txtOperand2.setText("?");
        txtOperator.setText(operator);
        setBtnWithRandomAns(operand2);

    }

    private void BlankOperand1() {
        txtOperand1.setText("?");
        txtOperand2.setText(String.valueOf(operand2));
        txtOperator.setText(operator);
        setBtnWithRandomAns(operand1);
    }


}




