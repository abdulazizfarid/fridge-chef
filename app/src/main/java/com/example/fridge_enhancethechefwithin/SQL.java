package com.example.fridge_enhancethechefwithin;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SQL extends AppCompatActivity {
    public static String ip = "192.168.18.36";
    public static String port = "1433";
    public static String classes = "net.sourceforge.jtds.jdbc.Driver";
    public static String database = "Uni_Database_Updated";
    public static String username = "admin";
    public static String password = "admin123";
    public static String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;
    public static String urlAzure = "jdbc:sqlserver://lifeless.database.windows.net:1433;database=Test;user=admintest@lifeless;password={Six170528p};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public Connection connection = null;
    public TextView textView;
    public ArrayList<String> list;

    public SQL() {
        this.onCreate(null);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        textView = findViewById(R.id.txtCategories);
        try {
            Class.forName(classes);
            connection = DriverManager.getConnection(urlAzure, username, password);
            textView.setText("SuccessConnected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*public String[] getList(){
        textView.setText("SuccessConnected");
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT name FROM Sample;");
                //TimeUnit.SECONDS.sleep(5);
                while (resultSet.next()){
                    list.add(resultSet.getString(1));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {

        }
        String[] array = list.toArray(new String[0]);
        return array;
    }*/
    public void getList() {
        textView.setText("SuccessConnected");
        if (connection != null) {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT name FROM Sample;");
                //TimeUnit.SECONDS.sleep(5);
                while (resultSet.next()) {
                    textView.setText(resultSet.getString(1));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {

        }
        //String[] array = list.toArray(new String[0]);
        //return array;
    }
}