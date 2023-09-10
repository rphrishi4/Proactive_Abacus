package com.nak.vserveu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


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
                finish();
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
        setContentView(R.layout.activity_main);

        Button startbutton=(Button)findViewById(R.id.btn_start);
        Button aboutbutton=(Button)findViewById(R.id.btn_about);
        final EditText accessCodeText=(EditText)findViewById(R.id.inp_Name);
        Map<String,String> levelCodes = new HashMap<String, String>();
        levelCodes.put("0","level0");
        levelCodes.put("1","level1");
        levelCodes.put("2","level2");
        levelCodes.put("3","level3");
        levelCodes.put("4","level4");
        levelCodes.put("5","level5");
        levelCodes.put("6","level6");
        levelCodes.put("7","level7");
        levelCodes.put("8","level8");

        levelCodes.put("9","level9");





        startbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String accessCode=accessCodeText.getText().toString();
                        if(levelCodes.containsKey(accessCode)) {
                            Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
                            intent.putExtra("level", levelCodes.get(accessCode));
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Access code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                aboutbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),About.class);
                    startActivity(intent);
            }
        });



    }
}
