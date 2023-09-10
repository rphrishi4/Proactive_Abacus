package com.nak.vserveu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {

    Button level0,level1,level2,level3,level4,level5,level6,level7,level8,level9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Intent intent = getIntent();
        String level= intent.getStringExtra("level");

        level0 =  (Button) findViewById(R.id.btn_lvl0);
        level1 =  (Button) findViewById(R.id.btn_lvl1);
        level2 =  (Button) findViewById(R.id.btn_lvl2);
        level3 =  (Button) findViewById(R.id.btn_lvl3);
        level4 =  (Button) findViewById(R.id.btn_lvl4);
        level5 =  (Button) findViewById(R.id.btn_lvl5);
        level6 =  (Button) findViewById(R.id.btn_lvl6);
        level7 =  (Button) findViewById(R.id.btn_lvl7);
        level8 =  (Button) findViewById(R.id.btn_lvl8);
        level9 =  (Button) findViewById(R.id.btn_customlvl);

        ArrayList<Button> levelButtons = new ArrayList<>();
        levelButtons.add(level0);
        levelButtons.add(level1);
        levelButtons.add(level2);
        levelButtons.add(level3);
        levelButtons.add(level4);
        levelButtons.add(level5);
        levelButtons.add(level6);
        levelButtons.add(level7);
        levelButtons.add(level8);
        levelButtons.add(level9);

        int levelNum = Integer.parseInt(level.substring(level.length()-1));
        for(int i=levelNum+1;i<levelButtons.size();i++){
            levelButtons.get(i).setFocusable(false);
            levelButtons.get(i).setEnabled(false);
            levelButtons.get(i).setBackgroundResource(R.mipmap.lock);
            levelButtons.get(i).setTextColor(Color.parseColor("#000000"));
            }
        level0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level0";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level1";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level2";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level3";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level4";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level5";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level6";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level7";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "Level8";
                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("level",name);
                startActivity(intent);
            }
        });
        level9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= "level9";
                Toast.makeText(getApplicationContext(),"In Development",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getApplicationContext(), QuestionsActivity.class);
//                intent.putExtra("level",name);
//                startActivity(intent);
            }
        });
    }
}