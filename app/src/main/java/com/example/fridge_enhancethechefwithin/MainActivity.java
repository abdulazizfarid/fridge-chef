package com.example.fridge_enhancethechefwithin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

    /*
    public static String database = "db_a83a8a_fridge"; //For MS SQL
    public static String username = "db_a83a8a_fridge_admin";
    public static String password = "fridge123";
    public static String classes = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String ip = "SQL5107.site4now.net";
    public static String port = "1433";
    public static String protocol = "TLS";
    public static String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;sslProtocol=TLSv1.2",ip, port, database, username, password);
    public static Connection connection = null; */
    //public static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+"; user="+username+"; password="+password+";";
    ArrayList<String> list;
    DBHandler DB;
    boolean auth = false;
    Cursor usr;
    String mode = "";
    public static ArrayList<String> RecipeNames = new ArrayList<>();
    public static ArrayList<String> RecipeInstructions = new ArrayList<>();
    public static ArrayList<String> RecipeIngredients = new ArrayList<>();
    public CardView CardHome;
    EditText etEmail;
    EditText etPassword;
    public TextView txtHome;
    CardView logoutCard;
    CardView favCard;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor spEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DB = new DBHandler(this);
        CardHome = (CardView) findViewById(R.id.HomeCard);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        txtHome = (TextView) findViewById(R.id.txtHome);
        logoutCard = (CardView) findViewById(R.id.logoutCard);
        favCard = (CardView) findViewById(R.id.favCard);
        sharedPrefs = getSharedPreferences("file", MODE_PRIVATE);
        spEdit = sharedPrefs.edit();

        boolean valueFromSharedPrefs = sharedPrefs.getBoolean("isUserLoggedIn", false);
        if (valueFromSharedPrefs) {
            logoutCard.setVisibility(View.VISIBLE);
            favCard.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Welcome back!", Toast.LENGTH_SHORT).show();
            etEmail.setVisibility(View.INVISIBLE);
            etPassword.setVisibility(View.INVISIBLE);
        }

        /*textView = findViewById(R.id.txtTitle);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/

        /*try {
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Class.forName(classes).newInstance();
            //connection = DriverManager.getConnection(urlAzure, "admintest@lifeless", "PlSw0rk1");
            //--Main-- // connection = DriverManager.getConnection(url);
            // connection = DriverManager.getConnection(url, username, password);
            textView.setText("Successfully Connected");
        } *//*catch (ClassNotFoundException e) {
            textView.setText("Class error");
            e.printStackTrace();
        }*//* catch (SQLException e) {
            textView.setText("SQL error");
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "Resumed", Toast.LENGTH_SHORT).show();
        boolean valueFromSharedPrefs = sharedPrefs.getBoolean("isUserLoggedIn", false);
        if (valueFromSharedPrefs) {
            logoutCard.setVisibility(View.VISIBLE);
            favCard.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.INVISIBLE);
            etPassword.setVisibility(View.INVISIBLE);
        }
        txtHome.setText("Let's Get Started");
    }

    public void logout(View v) {
        spEdit.putBoolean("isUserLoggedIn", false);
        spEdit.putString("userEmail", "");
        spEdit.apply();
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    //Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_SHORT).show();
                    String email = etEmail.getText().toString();
                    if (email != null && email.contains("@"))
                        usr = DB.getAuth(email);
                    else if(email == null)
                        Toast.makeText(getBaseContext(),"Email can not be empty", Toast.LENGTH_SHORT).show();
                    else if(!email.contains("@"))
                        Toast.makeText(getBaseContext(),"Please enter a valid email", Toast.LENGTH_SHORT).show();

                    if (usr.getCount()!=0){
                        etPassword.setHint("Enter your password");
                        etPassword.setText("");
                        etPassword.setVisibility(View.VISIBLE);
                        txtHome.setText("Login");
                        mode = "login";
                    }
                    else{
                        etPassword.setHint("Enter new password");
                        etPassword.setText("");
                        etPassword.setVisibility(View.VISIBLE);
                        txtHome.setText("Signup");
                        mode = "signup";
                    }

                }
            }
        });
        Toast.makeText(getBaseContext(),"Logged out!", Toast.LENGTH_SHORT).show();
        etEmail.setVisibility(View.VISIBLE);
        //etPassword.setVisibility(View.VISIBLE);
        logoutCard.setVisibility(View.INVISIBLE);
        favCard.setVisibility(View.INVISIBLE);
    }

    public void getFavs(View v) {
        Cursor res = DB.getFavorites(sharedPrefs.getString("userEmail", "x"));
        while (res.moveToNext()){
            RecipeNames.add(res.getString(0));
            RecipeInstructions.add(res.getString(1));
            RecipeIngredients.add(res.getString(2));
        }
        Intent i = new Intent(getBaseContext(), Results.class);
        i.putExtra("mode", "favorite");
        startActivity(i);
    }

    public void next(View v){

        SharedPreferences sharedPrefs = getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sharedPrefs.edit();
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        if (mode.equals("login")){
            usr.moveToFirst();
            if (etPassword.getText().toString() != null && usr.getString(1).equals(etPassword.getText().toString())){
                auth = true;
            }
            else{
                Toast.makeText(this,"Incorrect password!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (mode.equals("signup")){
            String pass = etPassword.getText().toString();
            if (pass.length()<6){
                Toast.makeText(this,"Password can't be less than 6 characters", Toast.LENGTH_SHORT).show();
            }
            else{
                DB.signup(etEmail.getText().toString(), pass);
                auth = true;
            }
        }

        if(auth){
            spEdit.putString("userEmail", etEmail.getText().toString());
            spEdit.putBoolean("isUserLoggedIn", true);
            spEdit.apply();
            Intent i = new Intent(getBaseContext(), Home.class);
            startActivity(i);

        }
    }

    //Copy pasted code to remove focus on touch outside EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    @Override
    public void onBackPressed()
    {
        //Do nothing
    }
}