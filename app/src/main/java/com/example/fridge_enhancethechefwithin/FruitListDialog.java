package com.example.fridge_enhancethechefwithin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FruitListDialog extends DialogFragment {

    public interface onMultiChoiceListener {
        void onPositiveButtonClicked(String[] fruit_list, ArrayList<String> SelectedFruits);

        void onNegativeButtonClicked();
    }

    onMultiChoiceListener FruitListener;
    final ArrayList<String> SelectedFruits = new ArrayList<>();


    //final ArrayList<String> Fruit_list = new ArrayList<>();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            FruitListener = (onMultiChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " OnMultiChoiceListener must be implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Home.Selected.clear();
        final String[] fruit_list = getActivity().getResources().getStringArray(R.array.fruit_list);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Fruits
        //if (Home.Category.equals("Fruits")) {
        builder.setTitle("Select your ingredients")
                .setMultiChoiceItems(fruit_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            SelectedFruits.add(fruit_list[i]);
                            //Home.flist.
                        } else {
                            SelectedFruits.remove(fruit_list[i]);
                        }
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FruitListener.onPositiveButtonClicked(fruit_list, SelectedFruits);
                        Home.flist = SelectedFruits.toArray(new String[0]);
                        Home.Selected.addAll(SelectedFruits);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FruitListener.onNegativeButtonClicked();
                    }
                });
        //} else if (Home.Category.equals("Vegetables")) {

        //}
        return builder.create();
    }
}