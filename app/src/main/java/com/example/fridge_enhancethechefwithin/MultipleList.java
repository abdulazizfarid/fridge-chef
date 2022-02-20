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

public class MultipleList extends DialogFragment {

    public interface onMultiChoiceListener {
        void onPositiveButtonClicked(String[] fruit_list, ArrayList<String> SelectedFruits);

        void onNegativeButtonClicked();
    }

    onMultiChoiceListener Listener;
    final ArrayList<String> SelectedFruits = new ArrayList<>();

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
        //Home.Selected.clear();
        final String[] fruit_list = getActivity().getResources().getStringArray(R.array.fruit_list);
        final String[] dairy_list = getActivity().getResources().getStringArray(R.array.dairy_list);
        final String[] dry_fruits_list = getActivity().getResources().getStringArray(R.array.dry_fruits_list);
        final String[] herb_list = getActivity().getResources().getStringArray(R.array.herbs_list);
        final String[] meat_list = getActivity().getResources().getStringArray(R.array.meat_list);
        final String[] others_list = getActivity().getResources().getStringArray(R.array.others_list);
        final String[] pp_list = getActivity().getResources().getStringArray(R.array.pp_list);
        final String[] ss_list = getActivity().getResources().getStringArray(R.array.ss_list);
        final String[] spices_list = getActivity().getResources().getStringArray(R.array.spices_list);
        final String[] vegetable_list = getActivity().getResources().getStringArray(R.array.vegetables_list);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Fruits
        if (Home.Category.equals("Fruits")) {
            builder.setTitle("Select your Fruits")
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
                            Listener.onPositiveButtonClicked(fruit_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Vegetables
        } else if (Home.Category.equals("Vegetables")) {
            builder.setTitle("Select your Vegetables")
                    .setMultiChoiceItems(vegetable_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(vegetable_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(vegetable_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(vegetable_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Meat
        } else if (Home.Category.equals("Meat")) {
            builder.setTitle("Select your Meat")
                    .setMultiChoiceItems(meat_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(meat_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(meat_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(meat_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Dairy
        } else if (Home.Category.equals("Dairy")) {
            builder.setTitle("Select your Dairy")
                    .setMultiChoiceItems(dairy_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(dairy_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(dairy_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(dairy_list, SelectedFruits);
                            Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //DryFruits
        } else if (Home.Category.equals("DryFruits")) {
            builder.setTitle("Select your Dry Fruits")
                    .setMultiChoiceItems(dry_fruits_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(dry_fruits_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(dry_fruits_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(dry_fruits_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Spices
        } else if (Home.Category.equals("Spices")) {
            builder.setTitle("Select your Spices")
                    .setMultiChoiceItems(spices_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(spices_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(spices_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(spices_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //SeasoningsSauces
        } else if (Home.Category.equals("SeasoningSauces")) {
            builder.setTitle("Select your Seasonings and Sauces")
                    .setMultiChoiceItems(ss_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(ss_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(ss_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(ss_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Herbs
        } else if (Home.Category.equals("Herbs")) {
            builder.setTitle("Select your Herbs")
                    .setMultiChoiceItems(herb_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(herb_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(herb_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(herb_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Powder and Pastes
        } else if (Home.Category.equals("PowderPastes")) {
            builder.setTitle("Select your Powders and Pastes")
                    .setMultiChoiceItems(pp_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(pp_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(pp_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(pp_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        //Others
        } else if (Home.Category.equals("Others")) {
            builder.setTitle("Select your Other items")
                    .setMultiChoiceItems(others_list, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                SelectedFruits.add(others_list[i]);
                                //Home.flist.
                            } else {
                                SelectedFruits.remove(others_list[i]);
                            }
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onPositiveButtonClicked(others_list, SelectedFruits);
                            //Home.flist = SelectedFruits.toArray(new String[0]);
                            Home.Selected.addAll(SelectedFruits);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.onNegativeButtonClicked();
                        }
                    });
        }
        return builder.create();
    }
}