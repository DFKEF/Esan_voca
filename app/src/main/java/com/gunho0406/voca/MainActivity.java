    package com.gunho0406.voca;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        private ArrayList<String> englist = new ArrayList<>();
        private ArrayList<String> korlist = new ArrayList<>();
        private ArrayList<String> list = new ArrayList<>();
        RecyclerView recyclerView;
        String english;
        String url = "http://smart0406.dothome.co.kr/voca";
        String fileUrl = url;
        TextView kor;
        String name;
        private ArrayList<String> namelist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FAFAFA"));
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        new htmlTask().execute();
        final FloatingActionButton fab = findViewById(R.id.fab);
        new FileTask().execute();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fab.show();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }


        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            // TODO Auto-generated method stub
            super.onConfigurationChanged(newConfig);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final TextView kor = (TextView) findViewById(R.id.kor);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_who) {
            Intent intent = new Intent(getApplicationContext(),Info.class);
            startActivity(intent);
        }else if(id==R.id.quiz) {
            int diff = korlist.size();
            if (diff > 0) {
                Intent intent = new Intent(getApplicationContext(), quiz.class);
                intent.putExtra("englist", englist);
                intent.putExtra("korlist", korlist);
                startActivity(intent);
            } else {
                Snackbar.make(getWindow().getDecorView(), "먼저 단어장을 로드해주세요", Snackbar.LENGTH_LONG).show();
            }
        }/*else if(id==R.id.adj) {
            CustomDialog dialog = new CustomDialog(this);
            dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                @Override
                public void Clicked(Float value) {
                }

            });
            dialog.show();
        }*/
        return super.onOptionsItemSelected(item);
    }

        public void Dialog() {

            String[] items = namelist.toArray(new String[namelist.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("단어장 로드");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fileUrl = url+"/file/"+list.get(which);
                    new htmlTask().execute();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }




        private class FileTask extends AsyncTask<Void,Void,Void> {

            Document document;
            int elementSize;

            @Override
            public void onPreExecute() {
                super.onPreExecute();
                list.clear();

            }
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    document = Jsoup.connect(url).get();
                    Elements ele = document.select("li");
                    elementSize = ele.size();
                    for (int i=0; i < elementSize; i++) {
                        Elements elements = document.select("li").eq(i);
                        String file = elements.text();
                        list.add(file);
                        name = elements.text();
                        name = name.replace("(Grade1)","[1학년] ");
                        name = name.replace("(Grade2)","[2학년] ");
                        name = name.replace("(Grade3)","[3학년] ");
                        namelist.add(name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

    private class htmlTask extends AsyncTask<Void,Void,Void> {

        int elementsSize;
        Document doc;

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            englist.clear();
            korlist.clear();
            elementsSize = 0;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                doc = Jsoup.connect(fileUrl).get();
                Elements wow = doc.select("tr");
                elementsSize = wow.size();
                for(int i=0; i<elementsSize; i++) {
                    Elements menglish = doc.select("th").eq(i);
                    english = menglish.text();
                    Elements mkorean = doc.select("table tbody tr td").eq(i);
                    String korean = mkorean.text();
                    englist.add(english);
                    korlist.add(korean);
                }
                Log.d("debug: ","List" +elementsSize);

            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rview);
            RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this, englist, korlist);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

}
