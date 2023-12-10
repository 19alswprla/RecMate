package com.cnit355_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class FragmentA2 extends Fragment implements View.OnClickListener {


    SqlDBHelper myHelper;
    SQLiteDatabase db;
    Cursor c;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    Button buttonBack, buttonRemove,buttonInsert, buttonUpdate, buttonWorkoutPlan;
    View view;
    TextView error;
    EditText et1_set, et1_rep, et2_set, et2_rep, et3_set,  et3_rep, et4_set, et4_rep;

    public FragmentA2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a2, container, false);

        myHelper = new SqlDBHelper(getActivity());

        // Replace these IDs with the actual IDs used in your XML layout
        checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox4 = view.findViewById(R.id.checkBox4);
        buttonBack = view.findViewById(R.id.buttonBack1);
        buttonRemove = view.findViewById(R.id.buttonRemove);
        buttonInsert = view.findViewById(R.id.buttonInsert);
        buttonWorkoutPlan=view.findViewById(R.id.buttonWorkoutPlan);
        buttonUpdate  = view.findViewById(R.id.buttonUpdate);
        et1_set= view.findViewById(R.id.editText);

        et1_rep= view.findViewById(R.id.editText2);
        et2_set= view.findViewById(R.id.editText3);
        et2_rep= view.findViewById(R.id.editText4);

        et3_set= view.findViewById(R.id.editText5);
        et3_rep= view.findViewById(R.id.editText6);
        et4_set=view.findViewById(R.id.editText7);
        et4_rep=view.findViewById(R.id.editText8);

        error = view.findViewById(R.id.textError);

        // Set click listeners for the checkboxes
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);

        buttonBack.setOnClickListener(this);
        buttonInsert.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);
        buttonWorkoutPlan.setOnClickListener(this);


        error = view.findViewById(R.id.textError);
        ImageView imageView1= (ImageView) view.findViewById(R.id.imageView1);
        ImageView imageView2= (ImageView) view.findViewById(R.id.imageView2);
        ImageView imageView3= (ImageView) view.findViewById(R.id.imageView3);
        ImageView imageView4= (ImageView) view.findViewById(R.id.imageView4);

        Glide.with(getActivity()).load(R.drawable.bench_press).into(imageView1);
        Glide.with(getActivity()).load(R.drawable.shoulderpress).into(imageView2);
        Glide.with(getActivity()).load(R.drawable.chest_dip).into(imageView3);
        Glide.with(getActivity()).load(R.drawable.lat_raise).into(imageView4);


        return view;
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonBack1) {
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }

        // TODO: Replace the "create" button with a "remove" button since the database gets created automatically. Remove button will remove exercises from workoutplan.
        else if (v.getId() == R.id.buttonRemove) {
            if (checkBox1.isChecked()) {
                myHelper.deleteData("Bench Press");
                Log.i("FragA2", "BenchPress Deleted");
            } else {
                Log.i("FragA2", "BenchPress Doesn't Exist");

            }
            if (checkBox2.isChecked()) {
                myHelper.deleteData("Shoulder Press");
                Log.i("FragA2", "Shoulder Press Deleted");

            } else {
                Log.i("FragA2", "Shoulder Press Doesn't Exist");

            }
            if (checkBox3.isChecked()) {
                myHelper.deleteData("Shoulder Dip");
            } else {
                Log.i("FragA2", "Shoulder Dip Doesn't Exist");

            }
            if (checkBox4.isChecked()) {
                myHelper.deleteData("Lateral Raises");
            } else {
                Log.i("FragA2", "Lateral Raises Doesn't Exist");

            }
            //TODO: Repeat the above logic for the other 3 checkboxes.

            Toast.makeText(getActivity(), "Removed selections from Workout Plan.", Toast.LENGTH_SHORT).show();
        }


        else if (v.getId() == R.id.buttonInsert)
        {
            if (checkBox1.isChecked()) {

                if (checkIfAlreadyAdded("Bench Press")) {
                    Log.i("FragA2", "BenchPress Already Added");

                    error.append("\t\t\nBench Press already added to Workout Plan. ");
                } else
                    myHelper.insertData("Bench Press", et1_set.getText().toString(), et1_rep.getText().toString(), "A2");
                Log.i("FragA2", "BenchPress inserted");
                c.close();
            }
            else if (checkBox2.isChecked()) {
                if (checkIfAlreadyAdded("Shoulder Press")) {
                    Log.i("FragA2", "Shoulder Press Already Added");

                    error.append("\t\t\nShoulder Press already added to Workout Plan. ");
                } else {
                    myHelper.insertData("Shoulder Press", et2_set.getText().toString(), et2_rep.getText().toString(), "A2");
                    Log.i("FragA2", "Shoulder Press inserted");
                    c.close();
                }
            }

            else if (checkBox3.isChecked()) {

                if (checkIfAlreadyAdded("Shoulder Dip")) {
                    Log.i("FragA2", "Shoulder Dip Already Added");

                    error.append("\t\t\nShoulder Dip already added to Workout Plan. ");
                } else {
                    myHelper.insertData("Shoulder Dip", et3_set.getText().toString(), et3_rep.getText().toString(), "A2");
                    Log.i("FragA2", "Shoulder Dip inserted");
                    c.close();
                }

            }
            else if (checkBox4.isChecked()) {

                if (checkIfAlreadyAdded("Lateral Raises")) {
                    Log.i("FragA2", "Lateral Raises Already Added");

                    error.append("\t\t\nLateral Raises already added to Workout Plan. ");
                } else {
                    myHelper.insertData("Lateral Raises", et4_set.getText().toString(), et4_rep.getText().toString(), "A2");
                    Log.i("FragA2", "Lateral Raises");
                    c.close();
                }
            }
        }
        //OnUpdate
        else if (v.getId() == R.id.buttonUpdate) {
            if (checkBox1.isChecked()) {

                if (checkIfAlreadyAdded("Bench Press")) {
                    if (et1_set.getText() != null || et1_rep.getText() != null) {
                        myHelper.updateData("Bench Press", et1_set.getText().toString(), et1_rep.getText().toString());
                    }
                }
            }
            else if (checkBox2.isChecked()) {

                if (checkIfAlreadyAdded("Shoulder Press")) {
                    if (et2_set.getText() != null || et2_rep.getText() != null) {
                        myHelper.updateData("Shoulder Press",  et2_set.getText().toString(), et2_rep.getText().toString());
                    }

                }
            } else if (checkBox3.isChecked()) {

                if (checkIfAlreadyAdded("Shoulder Dip")) {
                    if (et3_set.getText() != null || et3_rep.getText() != null) {
                        myHelper.updateData("Shoulder Dip",  et3_set.getText().toString(), et3_rep.getText().toString());
                    }
                }
            }
            else if (checkBox4.isChecked()) {

                if (checkIfAlreadyAdded("Lateral Raises")) {
                    if (et4_set.getText() != null || et4_rep.getText() != null) {
                        myHelper.updateData("Lateral Raises",  et4_set.getText().toString(), et4_rep.getText().toString());
                    }
                }
            }


        }

        else if (v.getId() == R.id.buttonWorkoutPlan) {

            Intent mIntent = new Intent(v.getContext(), WorkoutPlanActivity.class);
            startActivity(mIntent);
            Log.i("Log", "Intent to WorkoutPlan Started");
        }
    }
    public boolean checkIfAlreadyAdded(String name) {
        db = myHelper.getReadableDatabase();
        c = db.rawQuery("SELECT eName FROM workoutplan WHERE eName =?", new String[]{name});

        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}