package com.example.fridge_enhancethechefwithin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Objects;

public class IngredientListDialog extends DialogFragment {

    public interface onMultiChoiceListener {
        //void onPositiveButtonClicked(String[] ingredient_list, ArrayList<String> SelectedFruits);
        void onPositiveButtonClicked(ArrayList<String> SelectedIngredients);
        void onNegativeButtonClicked();
    }

    onMultiChoiceListener Listener;
    final ArrayList<String> selectedIngredients = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            Listener = (onMultiChoiceListener) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " OnMultiChoiceListener must be implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String title;
        final String[] ingredient_list;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Fruits
        switch (Home.Category) {
            case "Fruits":
                title = "Fruits";
                ingredient_list = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.fruit_list);
                //Vegetables
                break;
            case "Vegetables":
                title = "Vegetables";
                ingredient_list = getActivity().getResources().getStringArray(R.array.vegetables_list);
                //Meat
                break;
            case "Meat":
                title = "Meat items";
                ingredient_list = getActivity().getResources().getStringArray(R.array.meat_list);
                //Dairy
                break;
            case "Dairy":
                title = "Dairy products";
                ingredient_list = getActivity().getResources().getStringArray(R.array.dairy_list);
                //DryFruits
                break;
            case "DryFruits":
                title = "Dry Fruits";
                ingredient_list = getActivity().getResources().getStringArray(R.array.dry_fruits_list);
                //Spices
                break;
            case "Spices":
                title = "Spices";
                ingredient_list = getActivity().getResources().getStringArray(R.array.spices_list);
                //SeasoningsSauces
                break;
            case "SeasoningSauces":
                title = "Seasonings and sauces";
                ingredient_list = getActivity().getResources().getStringArray(R.array.ss_list);
                //Herbs
                break;
            case "Herbs":
                title = "Herbs";
                ingredient_list = getActivity().getResources().getStringArray(R.array.herbs_list);
                //Powder and Pastes
                break;
            case "PowderPastes":
                title = "Powders and Pastes";
                ingredient_list = getActivity().getResources().getStringArray(R.array.pp_list);
                //Others
                break;
            case "Others":
                title = "other ingredients";
                ingredient_list = getActivity().getResources().getStringArray(R.array.others_list);
                break;
            default:
                title = "";
                ingredient_list = new String[]{};
                try {
                    throw new Exception("Invalid choice");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
            builder.setTitle(String.format("Select your %s", title))
                    .setMultiChoiceItems(ingredient_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int index, boolean checked) {
                            if (checked) {
                                selectedIngredients.add(ingredient_list[index]);
                                //Home.flist.
                            } else {
                                selectedIngredients.remove(ingredient_list[index]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(selectedIngredients);
                            Home.Selected.addAll(selectedIngredients);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        return builder.create();
    }
}