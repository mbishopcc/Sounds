package com.example.mbishop4950.sounds;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    //PERSISTENCE
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String dataName = "MyData";
    String stringName = "MyString";
    String defaultString = ":-(";
    String currentString = ""; //empty
    Button button4;
    //PERSISTENCE (end)

    //SOUND BUTTONS
    private SoundPool soundPool;
    int sample1 = -1;
    int sample2 = -1;
    int sample3 = -1;
    //SOUND BUTTONS (end)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PERSISTENCE
        prefs = getSharedPreferences(dataName, MODE_PRIVATE);
        editor = prefs.edit();

        //either load our string or if not available our default string
        currentString = prefs.getString(stringName, defaultString);

        button4 = (Button) findViewById(R.id.button);
        button4.setOnClickListener(this);
        button4.setText(currentString);
        //PERSISTENCE (end)


        //SOUND BUTTONS
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        try{
            //create objects of the 2 required classes
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            //create our three fx in memory ready to use
            descriptor = assetManager.openFd("sample1.ogg");
            sample1 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("sample2.ogg");
            sample2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("sample3.ogg");
            sample3 = soundPool.load(descriptor, 0);

        }catch (IOException e) {
            //catch exceptions here
        }


        //Make button from each of the buttons in the layout
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        //Make each of them listen for clicks
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        //SOUND BUTTONS (end)

    }

    @Override
    public void onClick(View view) {

        //PERSISTENCE
        //Random randInt = new Random();
        //int ourRandom = randInt.nextInt(10);

        //currentString = currentString + ourRandom;

        //save the currentString into a file in case the user suddenly quits or takes a phone call
        //editor.putString(stringName, currentString);
        //editor.commit();

       // button4.setText(currentString);
        //PERSISTENCE (end)


        //SOUND BUTTONS
        switch (view.getId()) {
            case R.id.button1:
                soundPool.play(sample1,1,1,0,10,5);
                break;

            case R.id.button2:
                soundPool.play(sample2,1,1,0,4,1);
                break;

            case R.id.button3:
                soundPool.play(sample3,1,1,0,5,1);
                break;

            case R.id.button:
                Random randInt = new Random();
                int ourRandom = randInt.nextInt(10);

                currentString = currentString + ourRandom;
                editor.putString(stringName, currentString);
                editor.commit();
                button4.setText(currentString);
                break;

        }//SOUND BUTTONS (end)

    }
}
