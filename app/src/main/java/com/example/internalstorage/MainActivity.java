package com.example.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSaveData, btnReadData;
	// test git tren ubuntu
    TextView tvData;
    private final String fileName = "nguyentiendung";
    private final String content = "lap trinh vien";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWight();
        setOnClick();
    }

    private void setOnClick() {
        btnSaveData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);
    }

    private void setWight() {
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        tvData= (TextView) findViewById(R.id.tv_data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_data:
                SaveData();
           //     SaveDataByCache();
                break;
            case R.id.btn_read_data:
                readData();
                break;
            default:
                break;
        }
    }


    public void SaveData() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            //noi dung luu xuong
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveDataByCache() {
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            file = new File(getCacheDir(), fileName);
            fileOutputStream = new FileOutputStream(file);
            //noi dung luu xuong
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            //doc du lieu luu
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            //readLine doc tung dong
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
                Toast.makeText(this, "roi", Toast.LENGTH_SHORT).show();
                tvData.setText(stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readData2() {
        try {
            File file=new File(getCacheDir(),fileName);
            //FileInputStream fileInputStream = openFileInput(fileName);
//doc du lieu luu
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            //readLine doc tung dong
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
                Log.d("MainActivity", stringBuilder.toString());
                tvData.setText(stringBuilder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
