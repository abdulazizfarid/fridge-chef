package com.example.fridge_enhancethechefwithin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
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
    CardView[] FavCard =new CardView[10];
    TextView[] RecipeText = new TextView[10];
    TextView NoMatchFound;
    ImageView[] RecipeImage = new ImageView[10];
    StringBuffer[] buffer = new StringBuffer[10];
    //String [] RecipeNames= new String[10];
    String [] RecipeInstructions = new String[10];
    Home obj = new Home();
    DBHandler DB;
    ArrayList<Integer> FavIds = new ArrayList<Integer>();
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Resources resources = getResources();
        DB = new DBHandler(this);
        mode = getIntent().getStringExtra("mode");
        TextView txt = findViewById(R.id.txtCategories);
        NoMatchFound = findViewById(R.id.txtNoMatch);
        if (mode.equals("favorite")){
            txt.setText("Favorites");
            if (MainActivity.RecipeIngredients.size()!=0){
                Home.RecipeIngredients = MainActivity.RecipeIngredients;
                Home.RecipeNames = MainActivity.RecipeNames;
                Home.RecipeInstructions = MainActivity.RecipeInstructions;
            }else{
                NoMatchFound.setText("You have not chosen any favorites");
            }
        }
        else if (mode.equals("recipe")){
            NoMatchFound.setText(resources.getText(R.string.NoMatchString));
            txt.setText("Recipes");
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

        FavCard[0]=(CardView) findViewById(R.id.favCard0);
        FavCard[1]=(CardView) findViewById(R.id.favCard1);
        FavCard[2]=(CardView) findViewById(R.id.favCard2);
        FavCard[3]=(CardView) findViewById(R.id.favCard3);
        FavCard[4]=(CardView) findViewById(R.id.favCard4);
        FavCard[5]=(CardView) findViewById(R.id.favCard5);
        FavCard[6]=(CardView) findViewById(R.id.favCard6);
        FavCard[7]=(CardView) findViewById(R.id.favCard7);
        FavCard[8]=(CardView) findViewById(R.id.favCard8);
        FavCard[9]=(CardView) findViewById(R.id.favCard9);

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

        SharedPreferences sharedPrefs = getSharedPreferences("file", MODE_PRIVATE);
        String email = sharedPrefs.getString("userEmail", "x");
        Cursor res = DB.getFavId(email);
        while(res.moveToNext()){
            Integer value = res.getInt(0);
            FavIds.add(value);
        }

        for (Integer id: FavIds) {
            FavCard[id-1].setCardBackgroundColor(Color.parseColor("#e681a0"));
        }

        for (int i = 0; i < 10; i++) {
            RecipeCard[i].setVisibility(View.GONE);

        }

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

        for (int i = 0; i < 10; i++){
            int finalI = i;
            RecipeCard[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuffer buffer = new StringBuffer();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Results.this);
                    builder.setCancelable(true);
                    String title= (String) RecipeText[finalI].getText();
                    builder.setTitle(title);
                    buffer.append("Ingredients:\n" + Home.RecipeIngredients.get(Home.RecipeNames.indexOf(title)));
                    buffer.append("\n\nInstructions: \n" + Home.RecipeInstructions.get(Home.RecipeNames.indexOf(title)));
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            });
        }
    }

    @SuppressLint("ResourceAsColor")
    public void setFav(View v){
        String a = getResources().getResourceName(v.getId()).toString();
        String name = getResources().getResourceEntryName(v.getId()).toString();
        String index = String.valueOf(name.charAt(name.length()-1));
        Log.i("id:", String.valueOf(v.getId()));
        CardView card = (CardView) v;
        Integer cardColor = card.getCardBackgroundColor().getDefaultColor();
        if(cardColor == -1)
            card.setCardBackgroundColor(Color.parseColor("#e681a0"));
        else
            card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

        SharedPreferences sharedPrefs = getSharedPreferences("file", MODE_PRIVATE);
        String email = sharedPrefs.getString("userEmail", "x");
        if (!email.equals("x") && cardColor == -1){
            DB.setFav(email, Integer.valueOf(index) + 1);
        } else if (!email.equals("x") && cardColor != -1){
            DB.removeFav(email, Integer.valueOf(index) + 1);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        i= new Intent(this,Home.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {
        Intent i;
        if (mode.equals("favorite")){
            i = new Intent(this, MainActivity.class);
        }
        else{
            i = new Intent(this,Home.class);
        }
        startActivity(i);
    }
}