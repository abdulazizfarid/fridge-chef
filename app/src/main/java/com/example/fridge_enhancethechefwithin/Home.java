package com.example.fridge_enhancethechefwithin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity implements View.OnClickListener, MultipleList.onMultiChoiceListener {

    public CardView CardFruits, CardVegetables, CardMeat, CardHerb, CardSpices, CardSeasoningSauces,
            CardDairy, CardDryFruits, CardPowderPaste, CardOthers;
    DBHelper DB = new DBHelper(this);
    CardView SubmitCard;
    public LinearLayout HomeLayout;
    public static String Category = new String();
    public static String [] flist= new String[20];
    //public static String [] RecipeNames= new String[10];
    //public static String [] RecipeInstructions = new String[10];
    MultipleList.onMultiChoiceListener FruitListener;
    public static ArrayList<String> Selected = new ArrayList<>();
    public static ArrayList<String> SelectedID = new ArrayList<>();
    public static StringBuffer buffer = new StringBuffer();
    public static ArrayList<String> RecipeNames = new ArrayList<>();
    public static ArrayList<String> RecipeInstructions = new ArrayList<>();
    public static ArrayList<String> RecipeIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeLayout=findViewById(R.id.HomeLayout);
        CardFruits = (CardView) findViewById(R.id.CardFruits);
        CardVegetables = (CardView) findViewById(R.id.CardVegetables);
        CardMeat = (CardView) findViewById(R.id.CardMeat);
        CardHerb = (CardView) findViewById(R.id.CardHerbs);
        CardSpices = (CardView) findViewById(R.id.CardSpices);
        CardSeasoningSauces = (CardView) findViewById(R.id.CardSeasoningSauces);
        CardDairy = (CardView) findViewById(R.id.CardDairy);
        CardDryFruits = (CardView) findViewById(R.id.CardDryFruits);
        CardPowderPaste = (CardView) findViewById(R.id.CardPowderPaste);
        CardOthers = (CardView) findViewById(R.id.CardOthers);
        SubmitCard = (CardView) findViewById(R.id.SubmitCard);
        SubmitCard.setOnClickListener(this);

        CardFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category="Fruits";
                MultipleList multiChoiceDialog = new MultipleList();
                multiChoiceDialog.setCancelable(false);
                multiChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");

            }
        });

        CardVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category="Vegetables";
                DialogFragment HerbChoiceDialog = new MultipleList();
                HerbChoiceDialog.setCancelable(false);
                HerbChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");

            }
        });

        CardMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category="Meat";
                DialogFragment MeatChoiceDialog = new MultipleList();
                MeatChoiceDialog.setCancelable(false);
                MeatChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardHerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="Herbs";
                DialogFragment HerbChoiceDialog = new MultipleList();
                HerbChoiceDialog.setCancelable(false);
                HerbChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardSpices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="Spices";
                DialogFragment SpicesChoiceDialog = new MultipleList();
                SpicesChoiceDialog.setCancelable(false);
                SpicesChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardSeasoningSauces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="SeasoningSauces";
                DialogFragment SeasoningSaucesDialog = new MultipleList();
                SeasoningSaucesDialog.setCancelable(false);
                SeasoningSaucesDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="Dairy";
                DialogFragment DairyChoiceDialog = new MultipleList();
                DairyChoiceDialog.setCancelable(false);
                DairyChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardDryFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="DryFruits";
                DialogFragment DryFruitChoiceDialog = new MultipleList();
                DryFruitChoiceDialog.setCancelable(false);
                DryFruitChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardPowderPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="PowderPastes";
                DialogFragment PowderPastesDialog = new MultipleList();
                PowderPastesDialog.setCancelable(false);
                PowderPastesDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });
        CardOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category="Others";
                DialogFragment OtherChoiceDialog = new MultipleList();
                OtherChoiceDialog.setCancelable(false);
                OtherChoiceDialog.show(getSupportFragmentManager(), "Multichoice Dialog: ");
            }
        });

    }

    @Override
    public void onClick(View view) {
        Cursor res = DB.getID();
        if(res.getCount()==0){
            Snackbar.make(HomeLayout,"No Entry",Snackbar.LENGTH_LONG).show();
            //Toast.makeText(Home.this,"No Entry",Toast.LENGTH_LONG);
            return;
        }


        while (res.moveToNext()){
            buffer.append("ID: "+res.getString(0)+"\n");
            SelectedID.add(res.getString(0));
            //buffer.append("Name: "+res.getString(3)+"\n\n");
        }
        //
        //
        //

        res = DB.getRecipes();
        //if(res.getCount()==0){
       //     Snackbar.make(HomeLayout,"No Entry",Snackbar.LENGTH_LONG).show();
     //       return;
   //     }
        buffer = new StringBuffer();
        int count=0;
        RecipeNames.clear();
        RecipeInstructions.clear();
        RecipeIngredients.clear();
        while (res.moveToNext()){
            //
            //SelectedID.add(res.getString(0));
            RecipeNames.add(res.getString(0));
            RecipeInstructions.add(res.getString(1));
            RecipeIngredients.add(res.getString(2));
    //        RecipeNames[count]=res.getString(0);
      //      RecipeInstructions[count]=res.getString(1);
            //buffer.append("Name: "+res.getString(0)+"\n");
            buffer.append("Instructions: "+res.getString(1)+"\n\n");
            count++;
        }

        //StringBuffer buffer = new StringBuffer();
        //for (String str:SelectedID) buffer.append(str);
        SelectedID.clear();
        Selected.clear();
        //if (buffer.length()==0) Snackbar.make(HomeLayout,"No Entry",Snackbar.LENGTH_LONG).show();
        //else {
            Intent i;
            i= new Intent(this,Results.class);
            startActivity(i);
        //}
    }

    @Override
    public void onPositiveButtonClicked(String[] fruit_list, ArrayList<String> SelectedFruits) {

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}