package com.example.fridge_enhancethechefwithin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements View.OnClickListener{
    CardView BackCard;
    CardView[] RecipeCard=new CardView[10];
    TextView[] RecipeText = new TextView[10];
    TextView NoMatchFound;
    ImageView[] RecipeImage = new ImageView[10];
    StringBuffer[] buffer = new StringBuffer[10];
    //String [] RecipeNames= new String[10];
    String [] RecipeInstructions = new String[10];
    Home obj = new Home();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Resources resources = getResources();
        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("favorite")){
            TextView txt = findViewById(R.id.txtCategories);
            txt.setText("Favorites");
            if (MainActivity.RecipeIngredients.size()!=0){
                Home.RecipeIngredients = MainActivity.RecipeIngredients;
                Home.RecipeNames = MainActivity.RecipeNames;
                Home.RecipeInstructions = MainActivity.RecipeInstructions;
            }else{
                NoMatchFound.setText("You have not chosen any favorites");
            }
        }
        final String[] recipe_list = resources.getStringArray(R.array.recipe_list);
       // for (int i=0;i<=9;i++) RecipeNames[i]="";


        BackCard = (CardView) findViewById(R.id.BackCard);
        BackCard.setOnClickListener(this);
        RecipeCard[0]=(CardView) findViewById(R.id.Card0);
        RecipeCard[1]=(CardView) findViewById(R.id.Card1);
        RecipeCard[2]=(CardView) findViewById(R.id.Card2);
        RecipeCard[3]=(CardView) findViewById(R.id.Card3);
        RecipeCard[4]=(CardView) findViewById(R.id.Card4);
        RecipeCard[5]=(CardView) findViewById(R.id.Card5);
        RecipeCard[6]=(CardView) findViewById(R.id.Card6);
        RecipeCard[7]=(CardView) findViewById(R.id.Card7);
        RecipeCard[8]=(CardView) findViewById(R.id.Card8);
        RecipeCard[9]=(CardView) findViewById(R.id.Card9);

        RecipeText[0]=(TextView) findViewById(R.id.txt0);
        RecipeText[1]=(TextView) findViewById(R.id.txt1);
        RecipeText[2]=(TextView) findViewById(R.id.txt2);
        RecipeText[3]=(TextView) findViewById(R.id.txt3);
        RecipeText[4]=(TextView) findViewById(R.id.txt4);
        RecipeText[5]=(TextView) findViewById(R.id.txt5);
        RecipeText[6]=(TextView) findViewById(R.id.txt6);
        RecipeText[7]=(TextView) findViewById(R.id.txt7);
        RecipeText[8]=(TextView) findViewById(R.id.txt8);
        RecipeText[9]=(TextView) findViewById(R.id.txt9);
        NoMatchFound=(TextView) findViewById(R.id.txtNoMatch);

       /* RecipeImage[0]=(ImageView) findViewById(R.id.Img0);
        RecipeImage[1]=(ImageView) findViewById(R.id.Img1);
        RecipeImage[2]=(ImageView) findViewById(R.id.Img2);
        RecipeImage[3]=(ImageView) findViewById(R.id.Img3);
        RecipeImage[4]=(ImageView) findViewById(R.id.Img4);
        RecipeImage[5]=(ImageView) findViewById(R.id.Img5);
        RecipeImage[6]=(ImageView) findViewById(R.id.Img6);
        RecipeImage[7]=(ImageView) findViewById(R.id.Img7);
        RecipeImage[8]=(ImageView) findViewById(R.id.Img8);
        RecipeImage[9]=(ImageView) findViewById(R.id.Img9);*/
        //String str = new String();
        //for (int i=0;i<=9;i++) RecipeNames[i]="";


        for (int i = 0; i < 10; i++) RecipeCard[i].setVisibility(View.GONE);

        if (Home.RecipeNames.isEmpty()){
            NoMatchFound.setVisibility(View.VISIBLE);
        }
        else {
            NoMatchFound.setVisibility(View.GONE);
            for (String str : Home.RecipeNames) {
                for (int i = 0; i <= 9; i++) {
                    if (str.equals(recipe_list[i])) {
                        RecipeText[i].setText(str);
                        RecipeCard[i].setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        RecipeCard[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[0].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[1].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[2].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[3].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[4].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[5].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[6].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[7].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                builder.setCancelable(true);
                String title= (String) RecipeText[8].getText();
                builder.setTitle(title);
                buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        RecipeCard[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               StringBuffer buffer = new StringBuffer();
               AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
               builder.setCancelable(true);
               String title= (String) RecipeText[9].getText();
               builder.setTitle(title);
               buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
               buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
               builder.setMessage(buffer.toString());
               builder.show();
            }
        });
    }

    public void setFav(View v){
        String a = getResources().getResourceName(v.getId()).toString();
        String b = getResources().getResourceEntryName(v.getId()).toString();
        Log.i("id:", String.valueOf(v.getId()));
    }

    @Override
    public void onClick(View v) {
        Intent i;
        i= new Intent(this,Home.class);
        startActivity(i);
    }
}