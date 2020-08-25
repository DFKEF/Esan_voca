package com.gunho0406.voca;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.Random;

public class quiz extends AppCompatActivity {

    int lim;
    String answer;
    private int num = 0;
    Button buttons[] = new Button[4];
    int bool;
    Button fake;

    ArrayList<String> umnyalist = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<Item> list = new ArrayList<>();
    ArrayList<String> korea = new ArrayList<>();
    ArrayList<String> english = new ArrayList<>();
    ArrayList<String> rankor = new ArrayList<>();
    ArrayList<String> raneng = new ArrayList<>();
    String text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Random random = new Random();
        korea = (ArrayList<String>) intent.getSerializableExtra("korlist");
        english = (ArrayList<String>) intent.getSerializableExtra("englist");
        for(int i=0; i<korea.size(); i++) {
            String kor, eng;
            kor = String.valueOf(korea.get(i));
            eng = String.valueOf(english.get(i));
            list.add(new Item(kor,eng));
            lim = random.nextInt(korea.size());
            rankor.add(korea.get(lim));
            raneng.add(english.get(lim));
            lim = 0;
        }
        buttons[0] = (Button) findViewById(R.id.btn1);
        buttons[1] = (Button) findViewById(R.id.btn2);
        buttons[2] = (Button) findViewById(R.id.btn3);
        buttons[3] = (Button) findViewById(R.id.btn4);
        final TextView dict = (TextView) findViewById(R.id.dict);

        fake = (Button) findViewById(R.id.fake);
        final String[] text = {setDict(num)};
        fake.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDict(num);
                text[0] = setDict(num);
                dict.setText(text[0]);
                arrayList.clear();
                background(text[0]);
                arrayList = background(text[0]);
                String ans = null;
                String fu = setDict(num);


                for(int i=0; i<list.size(); i++) {
                    if(fu==korea.get(i)) {
                        answer = english.get(i);
                        //ggview.setText(answer);
                        break;
                    }
                }
                text1 = arrayList.get(0);
                text2 = arrayList.get(1);
                text3 = arrayList.get(2);
                text4 = arrayList.get(3);

                buttons[0].setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        click(answer, text1, view, text[0]);
                    }
                });

                buttons[1].setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        click(answer, text2, view, text[0]);

                    }
                });

                buttons[2].setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        click(answer, text3, view, text[0]);
                    }
                });

                buttons[3].setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        click(answer, text4, view, text[0]);
                    }
                });
                if(num==list.size()-1) {
                    onBackPressed();
                }
                num++;
            }
        });

        fake.performClick();




    }

    public String setDict(int num) {
        String one = null;
        String text = raneng.get(num);
        int hi;
        Random random = new Random();
        hi = random.nextInt(4);
        for(int q=0; q<list.size(); q++) {
            if(text==english.get(q)) {
                one = String.valueOf(korea.get(q));
                break;
            }else {

            }
        }
        return one;
    }

    public String answerDict(String kor) {
        String ans = null;
        for(int i=0; i<list.size(); i++) {
            if(kor==korea.get(i)) {
                ans = String.valueOf(english.get(i));
                break;
            }else {

            }
        }
        return ans;
    }

    public ArrayList<String> btnDict(String answer) {
        String hi = null;
        ArrayList<String> arrayList = new ArrayList<>();
        int neinom;
        for(int i=0; i<list.size(); i++) {
            if(answer==korea.get(i)) {
                hi = String.valueOf(english.get(i));
                neinom = i;
                break;
            }else {

            }
        }
        Random random = new Random();
        for(int t=0; t<=3; t++) {
            int loop = random.nextInt(list.size());
            String test = english.get(loop);
            if(test==hi) {
                t=t-1;
            }else {
                arrayList.add(english.get(loop));
            }

        }
        //dkdk


        return arrayList;
    }
    public ArrayList<String> background(String text) {
        ArrayList<String> arrayList = new ArrayList<>();
        umnyalist.clear();
        buttons[0] = (Button) findViewById(R.id.btn1);
        buttons[1] = (Button) findViewById(R.id.btn2);
        buttons[2] = (Button) findViewById(R.id.btn3);
        buttons[3] = (Button) findViewById(R.id.btn4);
        TextView dict = (TextView) findViewById(R.id.dict);




        Random random = new Random();
        int what = random.nextInt(4);
        buttons[what].setText(answerDict(text));
        for(int u=0; u<=3; u++) {
            if(u==what) {
                umnyalist.add(answerDict(text));
            }else{
                String hello = btnDict(text).get(u);
                buttons[u].setText(hello);
                umnyalist.add(hello);
            }
        }

        return umnyalist;
    }

    public int click(String answer, String text1, View view, String text) {
        int u = 0;
        if(answer==text1) {
            Snackbar.make(view,"정답",Snackbar.LENGTH_SHORT).setBackgroundTint(Color.parseColor("#4caf50")).show();
            u = 10;
            if(num==list.size()-1) {
                onBackPressed();
            }else{
                fake = (Button) findViewById(R.id.fake);
                fake.performClick();
            }

        }else{
            Snackbar.make(view,"오답",Snackbar.LENGTH_SHORT).setBackgroundTint(Color.parseColor("#f44336")).show();
            u = 5;
        }
        return u;
    }
}
