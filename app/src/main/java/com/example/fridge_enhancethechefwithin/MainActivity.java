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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
   /* public static String ip = "192.168.18.36";
    //public static String classes = "com.mysql.jdbc.Driver";
    //public static String database = "TestDB1"; //For MySQL
    //ublic static String username = "root";
    //blic static String password = "";
   // public static String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
    //public static String urlAzure = "jdbc:sqlserver://lifeless.database.windows.net:1433;database=Test1;user=admintest@lifeless;password=Six170528p;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static Connection connection = null; */
    public static String database = "db_a83a8a_fridge"; //For MS SQL
    public static String username = "db_a83a8a_fridge_admin";
    public static String password = "fridge123";
    public static String classes = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String ip = "SQL5107.site4now.net";
    public static String port = "1433";
    //public static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+"; user="+username+"; password="+password+";";
    public static String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s",ip, port, database, username, password);
    public static Connection connection = null;
    public CardView CardHome;

    TextView textView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        CardHome = (CardView) findViewById(R.id.HomeCard);

        textView = findViewById(R.id.txtTitle);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Class.forName(classes);
            //connection = DriverManager.getConnection(urlAzure, "admintest@lifeless", "PlSw0rk1");
            connection = DriverManager.getConnection(url);
            // connection = DriverManager.getConnection(url, username, password);
            textView.setText("Successfully Connected");
        } catch (ClassNotFoundException e) {
            textView.setText("Class error");
            e.printStackTrace();
        } catch (SQLException e) {
            textView.setText("SQL error");
            e.printStackTrace();
        }

    }
    public void login(View v){
        Intent i;
        i = new Intent(getBaseContext(), Home.class);
        startActivity(i);
    }
}