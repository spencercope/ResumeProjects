package com.example.ajb8c4.eval;

import android.database.Cursor;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{

    Database myDb;

    EditText comments;
    EditText teamNameTxt;
    private static SeekBar techBar, useBar, clarBar, overBar;
    private static TextView textView1, textView2, textView3, textView4;
    private int techScore, useScore, clarScore, overScore;
    private Button viewBtn,submitBtn;
    private String avgScor,commentInput,teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new Database(this);

        runSeekbar();

        submitBtn = (Button) findViewById(R.id.submitBtn);
        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        viewBtn=(Button)findViewById(R.id.button);

        submitBtn.setEnabled(false);

        viewBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll();
            }
        }) );

        calcBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                comments=(EditText)findViewById(R.id.commentsBox);
                teamNameTxt=(EditText) findViewById(R.id.teamNameTxt);
                techScore= techBar.getProgress();
                useScore= useBar.getProgress();
                clarScore= clarBar.getProgress();
                overScore= overBar.getProgress();
                commentInput= comments.getText().toString();
                teamName=teamNameTxt.getText().toString();
                avgScor= String.valueOf((double)(techScore+useScore+clarScore+overScore)/4);


                Toast.makeText(getApplicationContext(), avgScor, Toast.LENGTH_LONG).show();

                //textView6 = (TextView)findViewById(R. id.textView6);
                //textView6.setText("Avg: " + avgScore);

                submitBtn.setEnabled(true);

            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveTeam();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deleteMenu)
        {
            myDb.deleteData();
            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }



    public void viewAll()
    {

                Cursor res = myDb.getData();
                if (res.getCount()==0)
                {
                    showMessage("error","Nothing");
                    return;
                }


                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Team Name:  "+ res.getString(7)+"\n");
                    buffer.append("Q1:  "+ res.getString(1)+"\n");
                    buffer.append("Q2:  "+ res.getString(2)+"\n");
                    buffer.append("Q3:  "+ res.getString(3)+"\n");
                    buffer.append("Q4:  "+ res.getString(4)+"\n");
                    buffer.append("AVG:  "+ res.getString(5)+"\n");
                    buffer.append("Comments:  "+res.getString(6)+"\n");
                }

                showMessage("Data",buffer.toString());

            }




    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    public void saveTeam()
    {


        boolean inserted= myDb.insertData(techScore,useScore,clarScore,overScore,avgScor,commentInput,teamName);
        if (inserted==true)
        {
            Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Data not Saved",Toast.LENGTH_LONG).show();

        }

        submitBtn.setEnabled(false);
        resetValue();
    }


    public void resetValue()
    {
        techBar.setProgress(1);
        useBar.setProgress(1);
        clarBar.setProgress(1);
        overBar.setProgress(1);
        comments.setText(" Comments");
        teamNameTxt.setText(" Team Name");

    }






    public void runSeekbar()
    {
        techBar = (SeekBar)findViewById(R. id.techBar);
        textView1 = (TextView)findViewById(R. id.textView);
        textView1.setText("Technicality: " + getGrade(techBar.getProgress()));

        techBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser)
                    {
                        textView1.setText("Technicality: " + getGrade(progress1));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {
                    }
                }
        );

        useBar = (SeekBar)findViewById(R. id.useBar);
        textView2 = (TextView)findViewById(R. id.textView2);
        textView2.setText("Usefulness: " + getGrade(useBar.getProgress()));

        useBar.setOnSeekBarChangeListener
                (
                        new SeekBar.OnSeekBarChangeListener()
                        {
                            @Override
                            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser)
                            {
                                textView2.setText("Usefulness: " + getGrade(progress2 ));
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar)
                            {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar)
                            {
                            }
                        }
                );

        clarBar = (SeekBar)findViewById(R. id.clarBar);
        textView3 = (TextView)findViewById(R. id. textView3);
        textView3.setText("Clarity: " + getGrade(clarBar.getProgress()));

        clarBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar3, int progress3, boolean fromUser)
                    {
                        textView3.setText("Clarity: " + getGrade(progress3 ));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {
                    }
                }
        );

        overBar = (SeekBar)findViewById(R. id.overBar);
        textView4 = (TextView)findViewById(R. id. textView4);
        textView4.setText("Overall: " + getGrade(clarBar.getProgress()));

        overBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar4, int progress4, boolean fromUser)
                    {
                        textView4.setText("Overall: " + getGrade(progress4));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {
                    }
                }
        );
    }

    String getGrade(int gradeNum)
    {
        String gradeLetter;

        if (gradeNum == 1)
            gradeLetter = "C-";
        else if (gradeNum == 2)
            gradeLetter = "C";
        else if (gradeNum == 3)
            gradeLetter = "C+";
        else if (gradeNum == 4)
            gradeLetter = "B-";
        else if (gradeNum == 5)
            gradeLetter = "B";
        else if (gradeNum == 6)
            gradeLetter = "B+";
        else if (gradeNum == 7)
            gradeLetter = "A-";
        else if (gradeNum == 8)
            gradeLetter = "A";
        else
            gradeLetter = "";

        return gradeLetter;
    }

}

