package com.example.fridge_enhancethechefwithin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jtds.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   /* public static String ip = "192.168.18.36";
    public static String port = "1433";
    //public static String classes = "com.mysql.jdbc.Driver";
    public static String classes = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //public static String database = "TestDB1"; //For MySQL
    //ublic static String username = "root";
    //blic static String password = "";
    public static String database = "testdb"; //For MS SQL
    public static String username = "test";
    public static String password = "test";
   // public static String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
    public static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
    //public static String urlAzure = "jdbc:sqlserver://lifeless.database.windows.net:1433;database=Test1;user=admintest@lifeless;password=Six170528p;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static Connection connection = null; */

    public CardView CardHome;

    TextView textView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection deprecation


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txtTitle);
        /*
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName(classes);
            //connection = DriverManager.getConnection(urlAzure, "admintest@lifeless", "Six170528p");
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("Successfully Connected");
        } catch (ClassNotFoundException e) {
            textView.setText("Class error");
            e.printStackTrace();
        } catch (SQLException e) {
            textView.setText("SQL error");
            e.printStackTrace();
        }
        */
        CardHome = (CardView) findViewById(R.id.HomeCard);
        CardHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        i= new Intent(this,Home.class);
        startActivity(i);

    }
   /* @Override
    public void onClick(View v) {



        /*if (connection != null) {
            textView.setText("Connected");
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT docid FROM Doctor;");
                //TimeUnit.SECONDS.sleep(5);
                while (resultSet.next()) {
                    textView.setText(resultSet.getString(1));

                }
            } catch (SQLException e) {
                textView.setText("Disconnected");
                e.printStackTrace();
            }

        } else {
            textView.setText("Failed");
        }

    }*/
}