package com.nak.vserveu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity  {
    TextView tv;
    Button quitbutton;
    CompoundButton previousCheckedCompoundButton;
    Button rb1,rb2,rb3,rb4;
    TextView questionNoTextView;
    AlertDialog.Builder dialog;
    int endTestTime = 6;
    int totalQuestions = 100;
    ArrayList<Integer> allNumbers = new ArrayList<>();
    String questions[] = new String[totalQuestions];
    int answers[] = new int[totalQuestions];
    int opt[] = new int[totalQuestions*4];

    TextView timerTextView;

    /***
     Countdown by Himanshu
    long startTime = 0;
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis =   System.currentTimeMillis()-startTime ;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("Time : %d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 50);
            if(minutes==endTestTime){
                endTest();
            }
        }
    };
     ***/









    int queNum=0;
    public static int marks=0,correct=0,wrong=0;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed() {
       Dialog customDailog = new Dialog(this);
       customDailog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
               WindowManager.LayoutParams.WRAP_CONTENT);
       customDailog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
       customDailog.getWindow().getAttributes().windowAnimations
               = android.R.style.Animation_Dialog;
       customDailog.setContentView(R.layout.exit_custom_layout);

        Button yesBtn= customDailog.findViewById(R.id.dialog_yes);
        Button noBtn= customDailog.findViewById(R.id.dialog_no);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDailog.dismiss();
                endTest();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDailog.cancel();
            }
        });

        customDailog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);
        Dialog customDailog1 = new Dialog(this );

        questionNoTextView=(TextView)findViewById(R.id.questionNotextView);
        timerTextView = (TextView)findViewById(R.id.textTimer);
        Intent intent = getIntent();
        String level= intent.getStringExtra("level");


        String questionText = "Question "+(1)+" of "+totalQuestions;
        questionNoTextView.setText(questionText);

        //Couuntdown Timer
        new CountDownTimer(360000  ,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timerTextView.setText(String.format("Time : %d:%02d", minutes, seconds));
            }
            @Override
            public void onFinish() {
                endTest();
            }

        }.start();


        switch (level){
            case "Level0":
                setQuestins(0);
                break;
            case "Level1":
                setQuestins(1);
                break;
            case "Level2":
                setQuestins(2);
                break;
            case "Level3":
                setQuestins(3);
                break;
            case "Level4":
                setQuestins(4);
                break;
            case "Level5":
                setQuestins(5);
                break;
            case "Level6":
                setQuestins(6);
                break;
            case "Level7":
                setQuestins(7);
                break;
            case "Level8":
                setQuestins(8);
                break;
            case "customlvl":
                setQuestins(9);
                break;
        }

        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        rb1=(Button)findViewById(R.id.option1);
        rb2=(Button)findViewById(R.id.option2);
        rb3=(Button)findViewById(R.id.option3);
        rb4=(Button)findViewById(R.id.option4);
        tv.setText(questions[queNum]);
        rb1.setText(opt[0]+"");
        rb2.setText(opt[1]+"");
        rb3.setText(opt[2]+"");
        rb4.setText(opt[3]+"");

        CompoundButton.OnCheckedChangeListener onRadioButtonCheckedListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                if (previousCheckedCompoundButton != null) {
                    previousCheckedCompoundButton.setChecked(false);
                    previousCheckedCompoundButton = buttonView;
                } else {
                    previousCheckedCompoundButton = buttonView;
                }
            }
        };

        quitbutton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                customDailog1.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                customDailog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                customDailog1.getWindow().getAttributes().windowAnimations
                        = android.R.style.Animation_Dialog;
                customDailog1.setContentView(R.layout.exit_custom_layout);

                Button yesBtn= customDailog1.findViewById(R.id.dialog_yes);
                Button noBtn= customDailog1.findViewById(R.id.dialog_no);

                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDailog1.dismiss();
                        endTest();
                    }
                });
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDailog1.cancel();
                    }
                });

                customDailog1.show();


            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAns(rb1);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAns(rb2);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAns(rb3);
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAns(rb4);
            }
        });


    }

    public void submitAns(Button submitbutton){
        String ansText = submitbutton.getText().toString();
        int ansNum = Integer.parseInt(ansText);
        if(answers[queNum] == ansNum) {
            correct++;
            }
        else {
            wrong++;
            }

        queNum++;

        if(queNum<questions.length)
        {
            String questionText = "Question "+(queNum+1)+" of "+totalQuestions;
            questionNoTextView.setText(questionText);
            tv.setText(questions[queNum]);
            rb1.setText(opt[queNum*4]+"");
            rb2.setText(opt[queNum*4 +1]+"");
            rb3.setText(opt[queNum*4 +2]+"");
            rb4.setText(opt[queNum*4 +3]+"");
        }
        else
        {
            marks=correct;
            Intent in = new Intent(getApplicationContext(), ResultActivity.class);
            startActivity(in);
        }
    }
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        int ran = random.nextInt(max - min) + min;
        for(int j=0;j<10;j++){
            if(allNumbers.contains(ran)){
                ran = random.nextInt(max - min) + min;
            }
        }
        allNumbers.add(ran);
        return ran;
    }
    

    public List<Integer> randomizeOpts1(int a){
        int len=0;
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(a);
        len++;
        while(len<4){
            int n = getRandomNumber(a-3,a+6);
            if (!intList.contains(n)) {
                intList.add(n);
                len++;
            }
        }
        Collections.shuffle(intList);
        return intList;
    }

    public void endTest(){
        //timerHandler.removeCallbacks(timerRunnable);
        marks=correct;
        Intent intent=new Intent(getApplicationContext(), ResultActivity.class);
        finish();
        startActivity(intent);
    }



    public void setQuestins(int levels){
        switch (levels){
            case 0:
                createLevel0Questions();
                break;
            case 1:
                createLevel1Questions();
                break;
            case 2:
                createLevel2Questions();
                break;
            case 3:
                createLevel3Questions();
                break;
            case 4:
                createLevel4Questions();
                break;
            case 5:
                createLevel5_6Questions();
                break;
            case 6:
                createLevel5_6Questions();
                break;
            case 7:
                createLevel7_8Questions();
                break;
            case 8:
                createLevel7_8Questions();
                break;
            case 9:
                Toast.makeText(getApplicationContext(),"In Development",Toast.LENGTH_SHORT).show();
                //createcustomLevelQuestions();
                break;
        }
    }
//Question Type
    public void createAdditionQuestion(ArrayList<Integer> nums , int i){
        String q="Addition :\n";
        int a=0;
        for(Integer x : nums){
            a += x;
        }
        if(a<0){
            Collections.sort(nums);
            int firstNum = nums.get(0);
            if (firstNum<0){
                nums.remove(0);
                nums.add(-1*firstNum);
            }

            a += 2 * (-1) * firstNum;
        }

        Collections.sort(nums, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        });
        Collections.reverse(nums);

        for(Integer x : nums){
            //to show -ve sign
            if(x<0){
                q +=  x +"\n";
            }
            //to show +ve sign
            else{
                q += "+" + x +"\n";
            }
        }
        questions[i] = q;
        answers[i] = a;
        allNumbers.add(answers[i]);
        List<Integer> opts = randomizeOpts1(answers[i]);
        opt[i*4+0] = opts.get(0);
        opt[i*4+1] = opts.get(1);
        opt[i*4+2] = opts.get(2);
        opt[i*4+3] = opts.get(3);
    }

    public void createMultipleQuestion(ArrayList<Integer> nums , int i){
        if(nums.size()<2)
            return;
        String q="Multiplication :\n\n" + nums.get(0) + " x "+ nums.get(1) + " = ?";
        questions[i] = q;
        answers[i] = nums.get(0) * nums.get(1);
        allNumbers.add(answers[i]);
        List<Integer> opts = randomizeOpts1(answers[i]);
        opt[i*4+0] = opts.get(0);
        opt[i*4+1] = opts.get(1);
        opt[i*4+2] = opts.get(2);
        opt[i*4+3] = opts.get(3);
    }

    public void createDivisionQuestion(ArrayList<Integer> nums , int i){
        if(nums.size()<2)
            return;
        String q="Division :\n\n" + nums.get(0) + " / "+ nums.get(1) + " = ?" +
                "\n\nPlease choose correct Quotient";
        questions[i] = q;
        answers[i] = nums.get(0) / nums.get(1);
        allNumbers.add(answers[i]);
        List<Integer> opts = randomizeOpts1(answers[i]);
        opt[i*4+0] = opts.get(0);
        opt[i*4+1] = opts.get(1);
        opt[i*4+2] = opts.get(2);
        opt[i*4+3] = opts.get(3);
    }


int cal_random(int min,int max)
{
    return (int)(getRandomNumber(min,max));
}

    //Levels

    //Lvl0
    public void createLevel0Questions() {
        int i=0;
        //Addition
        for(;i<1*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(1,30)));
            q.add((int)(cal_random(1,5)));
            q.add((int)(cal_random(-9,9)));
            createAdditionQuestion(q,i);
        }
        for(;i<2*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(1,50)));
            q.add((int)(cal_random(-9,9)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));
            createAdditionQuestion(q,i);
        }
        for(;i<4*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(19,99)));
            q.add((int)(cal_random(10,29)));
            q.add((int)(cal_random(-19,1)));
            q.add((int)(cal_random(-9,9)));
            createAdditionQuestion(q,i);
        }
        for(;i<6*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(50,99)));
            q.add((int)(cal_random(-49,1)));
            q.add((int)(cal_random(10,99)));
            q.add((int)(cal_random(-19,1)));
            createAdditionQuestion(q,i);
        }


        for(;i<8*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(50,99)));
            q.add((int)(cal_random(-49,1)));
            q.add((int)(cal_random(10,99)));
            q.add((int)(cal_random(-19,1)));
            q.add((int)(cal_random(-9,1)));
            createAdditionQuestion(q,i);
        }
        for(;i<10*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(50,99)));
            q.add((int)(cal_random(50,99)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));
            createAdditionQuestion(q,i);
        }
    }

    //Lvl1
    public void createLevel1Questions() {
        int i=0;
        //Addition
        for(;i<1*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(10,19)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));
            createAdditionQuestion(q,i);
        }
        for(;i<2*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(10,49)));
            q.add((int)(cal_random(10,99)));
            q.add((int)(cal_random(-19,9)));
            createAdditionQuestion(q,i);
        }
        for(;i<3*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(40,79)));
            q.add((int)(cal_random(-19,1)));
            q.add((int)(cal_random(-19,19)));
            q.add((int)(cal_random(-10,19)));
            createAdditionQuestion(q,i);
        }
        for(;i<5*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(50,99)));
            q.add((int)(cal_random(-49,9)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));
            createAdditionQuestion(q,i);
        }

        for(;i<7*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(79,99)));
            q.add((int)(cal_random(-50,99)));
            q.add((int)(cal_random(-9,9)));
            q.add((int)(cal_random(-9,9)));
            q.add((int)(cal_random(-9,9)));
            createAdditionQuestion(q,i);
        }

        for(;i<totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(79,99)));
            q.add((int)(cal_random(-50,1)));
            q.add((int)(cal_random(-9,9)));
            q.add((int)(cal_random(-9,9)));
            q.add((int)(cal_random(-9,9)));
            createAdditionQuestion(q,i);
        }
    }

    //Lvl2
    public void createLevel2Questions() {
        int i=0;
        //Addition
        for(;i<1*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(10,49)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(10,19)));
            createAdditionQuestion(q,i);
        }
        for(;i<2*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(49,99)));
            q.add((int)(cal_random(-49,19)));
            q.add((int)(cal_random(9,19)));
            q.add((int)(cal_random(-9,19)));
            createAdditionQuestion(q,i);
        }
        for(;i<4*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(49,99)));
            q.add((int)(cal_random(-49,19)));
            q.add((int)(cal_random(9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));

            createAdditionQuestion(q,i);
        }
        for(;i<5*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(10,99)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));

            createAdditionQuestion(q,i);
        }
        for(;i<7*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(20,99)));
            q.add((int)(cal_random(10,19)));
            q.add((int)(cal_random(-40,99)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));

            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(39,99)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(10,19)));

            createAdditionQuestion(q,i);
        }

         }

    //Lvl3
    public void createLevel3Questions() {
        int i=0;
        //Addition
        for(;i<1*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(10,99)));
            q.add((int)(cal_random(1,9)));
            q.add((int)(cal_random(1,9)));

            createAdditionQuestion(q,i);
        }
        for(;i<3*totalQuestions/10;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(20,99)));
            q.add((int)(cal_random(10,19)));
            q.add((int)(cal_random(-40,99)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));

            createAdditionQuestion(q,i);
        }
        for(;i<5*totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add((int)(cal_random(-39,99)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(-9,19)));
            q.add((int)(cal_random(10,19)));

            createAdditionQuestion(q,i);
        }
        //Multiplication
        for(;i<totalQuestions*70/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(1,99));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(1,499));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*90/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(501,999));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }

    }

    //Lvl4
    public void createLevel4Questions() {
        int i=0;
        //Addition
        for(;i<totalQuestions/5;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-9,49)));
            createAdditionQuestion(q,i);
        }
        for(;i<2*totalQuestions/5;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(-val,99)));
            q.add((int)( cal_random(-9,-1)));
            q.add((int)( cal_random(1,9)));
            createAdditionQuestion(q,i);
        }



        //Multiplication
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(101,499));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*90/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(501,999));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
    }

    //Lvl5  Commenting as we created Level 5_6
    /* public void createLevel5Questions() {
        int i=0;
        //Addition
        for(;i<totalQuestions*10/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(-val,69)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*30/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,399);
            q.add(val);
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-9,29)));
            q.add((int)( cal_random(-9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*50/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,299);
            q.add(val);
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-9,29)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }



        //Multiplication
        for(;i<totalQuestions*60/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(50,199));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*70/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(200,699));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(11,99));
            q.add( getRandomNumber(11,49));
            createMultipleQuestion(q,i);
        }

        //Division
        for(;i<totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(100,999));
            q.add( getRandomNumber(1,9));
            createDivisionQuestion(q,i);
        }
    } */

    //LVL6
    public void createLevel5_6Questions(){
        int i=0;
        //Addition
        for(;i<totalQuestions*10/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(-val,69)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*30/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,399);
            q.add(val);
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-9,29)));
            q.add((int)( cal_random(-9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*50/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,299);
            q.add(val);
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-val,-1)));
            q.add((int)( cal_random(-9,29)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }



        //Multiplication
        for(;i<totalQuestions*60/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(50,199));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*70/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(100,199));
            q.add( getRandomNumber(5,15));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(100,199));
            q.add( getRandomNumber(10,19));
            createMultipleQuestion(q,i);
        }

        //Division
        for(;i<totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(30,99));
            q.add( getRandomNumber(1,9));
            createDivisionQuestion(q,i);
        }

    }

    //LVL7 Commenting as we created Level 7_8
   /* public void createLevel7Questions(){
        int i=0;
        //Addition
        for(;i<totalQuestions*10/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(69-val,69)));
            q.add((int)( cal_random(49-val,49)));
            q.add((int)( cal_random(9,49)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*30/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,399);
            q.add(val);
            q.add((int)( cal_random(69-val,69)));
            q.add((int)( cal_random(49-val,49)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*50/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(201,499);
            q.add(val);
            q.add((int)( cal_random(-19,19)));
            q.add((int)( cal_random(-19,49)));
            q.add((int)( cal_random(-9,29)));
            q.add((int)( cal_random(-9,29)));
            createAdditionQuestion(q,i);
        }

        //Multiplication
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(1,199));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }

        //Division
        for(;i<totalQuestions*90/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(10,199));
            q.add( getRandomNumber(1,9));
            createDivisionQuestion(q,i);
        }
        for(;i<totalQuestions;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(200,499));
            q.add( getRandomNumber(1,9));
            createDivisionQuestion(q,i);
        }
    } */

    //LVL8
    public void createLevel7_8Questions(){
        int i=0;
        //Addition
        for(;i<totalQuestions*10/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,99);
            q.add(val);
            q.add((int)( cal_random(-val,69)));
            q.add((int)( cal_random(-val,49)));
            q.add((int)( cal_random(-9,99)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*30/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(1,399);
            q.add(val);
            q.add((int)( cal_random(-val,69)));
            q.add((int)( cal_random(-val,49)));
            q.add((int)( cal_random(9,29)));
            q.add((int)( cal_random(9,29)));
            createAdditionQuestion(q,i);
        }
        for(;i<totalQuestions*50/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            int val=cal_random(201,499);
            q.add(val);
            q.add((int)( cal_random(-9,19)));
            q.add((int)( cal_random(-9,19)));
            q.add((int)( cal_random(-19,49)));
            q.add((int)( cal_random(-9,29)));
            createAdditionQuestion(q,i);
        }

        //Multiplication
        for(;i<totalQuestions*70/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(101,999));
            q.add( getRandomNumber(1,9));
            createMultipleQuestion(q,i);
        }
        for(;i<totalQuestions*80/100;i++){
            ArrayList<Integer> q = new ArrayList<>();
            q.add( getRandomNumber(11,99));
            q.add( getRandomNumber(11,99));
            createMultipleQuestion(q,i);
        }

        //Division
        for(;i<totalQuestions*85/100;i++) {
            ArrayList<Integer> q = new ArrayList<>();
            q.add(getRandomNumber(101, 999));
            q.add(getRandomNumber(1, 5));
            createDivisionQuestion(q, i);
        }
        for(;i<totalQuestions;i++) {
            ArrayList<Integer> q = new ArrayList<>();
            q.add(getRandomNumber(101, 999));
            q.add(getRandomNumber(5, 9));
            createDivisionQuestion(q, i);
        }
    }


    //Custom Level
    public void createcustomLevelQuestions(){
        long abc=360000;
        new CountDownTimer(abc  ,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timerTextView.setText(String.format("Time : %d:%02d", minutes, seconds));
            }
            @Override
            public void onFinish() {
                endTest();
            }

        }.start();


        int i=0;

        //Easy
       // if(difficulty="Easy"){
            //Addition
            for(;i<totalQuestions/3;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(-9,9);
                if(val<0){
                    val*=-1;
                    q.add(val);
                }
                else{
                    q.add(val);
                }
                q.add((int)( cal_random(-val-1,9)));
                createAdditionQuestion(q,i);
            }
            for(;i<2*totalQuestions/3;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(-9,9);
                if(val<0){
                    val*=-1;
                    q.add(val);
                }
                else{
                    q.add(val);
                }
                q.add((int)( cal_random(-val-1,9)));
                q.add((int)( cal_random(1,9)));
                createAdditionQuestion(q,i);
            }
            for(;i<totalQuestions;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(-9,9);
                if(val<0){
                    val*=-1;
                    q.add(val);
                }
                else{
                    q.add(val);
                }
                q.add((int)( cal_random(-val-1,9)));
                q.add((int)( cal_random(1,9)));
                q.add((int)( cal_random(-val,9)));
                createAdditionQuestion(q,i);
            }
       // }
        /**
        //Medium
        else if(difficulty="Medium"){

            //Addition
            for(;i<totalQuestions*10/100;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(9,19);
                if(val<0){
                    val*=-1;
                    q.add(val);
                    val-=10;
                }
                else{
                    q.add(val);
                    val-=10;
                }
                q.add((int)( cal_random(-val-1,9)));
                q.add((int)( cal_random(1,9)));
                q.add((int)( cal_random(1,9)));
                createAdditionQuestion(q,i);
            }
            for(;i<totalQuestions*30/100;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(10,40);
                if(val<0){
                    val*=-1;
                    q.add(val);
                }
                else{
                    q.add(val);
                }
                q.add((int)( cal_random(-9,9)));
                q.add((int)( cal_random(1,9)));
                q.add((int)( cal_random(-9,9)));
                createAdditionQuestion(q,i);
            }
            for(;i<totalQuestions*60/100;i++){
                ArrayList<Integer> q = new ArrayList<>();
                int val=cal_random(41,99);
                if(val<0){
                    val*=-1;
                    q.add(val);
                }
                else{
                    q.add(val);
                }
                q.add((int)( cal_random(-9,9)));
                q.add((int)( cal_random(1,9)));
                q.add((int)( cal_random(-9,9)));
                q.add((int)( cal_random(-9,9)));
                createAdditionQuestion(q,i);
            }

            //Multiplication
            for(;i<totalQuestions*70/100;i++){
                ArrayList<Integer> q = new ArrayList<>();
                q.add( getRandomNumber(101,499));
                q.add( getRandomNumber(1,9));
                createMultipleQuestion(q,i);
            }
            for(;i<totalQuestions*80/100;i++){
                ArrayList<Integer> q = new ArrayList<>();
                q.add( getRandomNumber(501,999));
                q.add( getRandomNumber(1,9));
                createMultipleQuestion(q,i);
            }

            //Division
            for(;i<totalQuestions;i++){
                ArrayList<Integer> q = new ArrayList<>();
                q.add( getRandomNumber(10,19));
                q.add( getRandomNumber(1,9));
                createDivisionQuestion(q,i);
            }
        }
        //Hard
        else if(difficulty="Hard") {
            //Addition
            for (; i < totalQuestions * 10 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                int val = cal_random(9, 19);
                if (val < 0) {
                    val *= -1;
                    q.add(val);
                    val -= 10;
                } else {
                    q.add(val);
                    val -= 10;
                }
                q.add((int) (cal_random(-val - 1, 9)));
                q.add((int) (cal_random(val - 2, 9)));
                q.add((int) (cal_random(val - 2, 9)));
                createAdditionQuestion(q, i);
            }
            for (; i < totalQuestions * 30 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                int val = cal_random(10, 40);
                if (val < 0) {
                    val *= -1;
                    q.add(val);
                } else {
                    q.add(val);
                }
                q.add((int) (cal_random(-9, 9)));
                q.add((int) (cal_random(1, 9)));
                q.add((int) (cal_random(-9, 9)));
                createAdditionQuestion(q, i);
            }
            for (; i < totalQuestions * 50 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                int val = cal_random(41, 99);
                if (val < 0) {
                    val *= -1;
                    q.add(val);
                } else {
                    q.add(val);
                }
                q.add((int) (cal_random(-9, 9)));
                q.add((int) (cal_random(1, 9)));
                q.add((int) (cal_random(-9, 9)));
                q.add((int) (cal_random(-9, 9)));
                createAdditionQuestion(q, i);
            }

            //Multiplication
            for (; i < totalQuestions * 70 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                q.add(getRandomNumber(101, 499));
                q.add(getRandomNumber(1, 9));
                createMultipleQuestion(q, i);
            }
            for (; i < totalQuestions * 80 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                q.add(getRandomNumber(501, 999));
                q.add(getRandomNumber(1, 9));
                createMultipleQuestion(q, i);
            }

            //Division
            for (; i < totalQuestions * 90 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                q.add(getRandomNumber(10, 99));
                q.add(getRandomNumber(1, 9));
                createDivisionQuestion(q, i);
            }
            for (; i < totalQuestions * 95 / 100; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                q.add(getRandomNumber(99, 199));
                q.add(getRandomNumber(1, 9));
                createDivisionQuestion(q, i);
            }
            for (; i < totalQuestions; i++) {
                ArrayList<Integer> q = new ArrayList<>();
                q.add(getRandomNumber(201, 999));
                q.add(getRandomNumber(1, 9));
                createDivisionQuestion(q, i);
            }
        }
         **/
    }

}
