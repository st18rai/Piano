package com.example.dev.piano;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SoundPool.OnLoadCompleteListener {

    private TextView mTvLog;
    final int MAX_STREAM = 7;
    SoundPool soundPool;
    private String oldText;

    int soundIDdo;
    int soundIDre;
    int soundIDmi;
    int soundIDfa;
    int soundIDsol;
    int soundIDla;
    int soundIDsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvLog = (TextView) findViewById(R.id.tv_log);

        findViewById(R.id.btn_do).setOnClickListener(this);
        findViewById(R.id.btn_re).setOnClickListener(this);
        findViewById(R.id.btn_mi).setOnClickListener(this);
        findViewById(R.id.btn_fa).setOnClickListener(this);
        findViewById(R.id.btn_sol).setOnClickListener(this);
        findViewById(R.id.btn_la).setOnClickListener(this);
        findViewById(R.id.btn_si).setOnClickListener(this);

        soundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);


        try {
            soundIDdo = soundPool.load(getAssets().openFd("do.wav"), 1);
            soundIDre = soundPool.load(getAssets().openFd("re.wav"), 1);
            soundIDmi = soundPool.load(getAssets().openFd("mi.wav"), 1);
            soundIDfa = soundPool.load(getAssets().openFd("fa.wav"), 1);
            soundIDsol = soundPool.load(getAssets().openFd("sol.wav"), 1);
            soundIDla = soundPool.load(getAssets().openFd("la.wav"), 1);
            soundIDsi = soundPool.load(getAssets().openFd("si.wav"), 1);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_do:
                soundPool.play(soundIDdo, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.do_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.do_txt));
                break;
            case R.id.btn_re:
                soundPool.play(soundIDre, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.re_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.re_txt));
                break;
            case R.id.btn_mi:
                soundPool.play(soundIDmi, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.mi_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.mi_txt));
                break;
            case R.id.btn_fa:
                soundPool.play(soundIDfa, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.fa_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.fa_txt));
                break;
            case R.id.btn_sol:
                soundPool.play(soundIDsol, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.sol_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.sol_txt));
                break;
            case R.id.btn_la:
                soundPool.play(soundIDla, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.la_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.la_txt));
                break;
            case R.id.btn_si:
                soundPool.play(soundIDsi, 1, 1, 0, 0, 1);
                writeLog(getString(R.string.si_txt));
                oldText = mTvLog.getText().toString();
                mTvLog.setText(oldText + " " + getString(R.string.si_txt));
                break;
        }
    }


    public void writeLog(String log) {

        // Get the directory for the user's public pictures directory.
        String path =
                Environment.getExternalStorageDirectory() + File.separator + "Piano";
        // Create the folder.
        File folder = new File(path);
        folder.mkdirs();

        // Create the file.
        File file = new File(folder, "log.txt");
        // Make sure the path directory exists.
        if (!folder.exists()) {
            // Make it, if it doesn't exit
            folder.mkdirs();
        }
        // Save your stream, don't forget to flush() it before closing it.

        try {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(oldText + " " + log);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }


    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {

    }
}
