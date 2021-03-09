package com.example.sumanassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    private Spinner candidateSpinner;
    ToggleButton toggleButton;
    Button submitVote;
    EditText Takename, Takeid;
    private ArrayList<Candidate_class> candidate_classArrayList;
    ArrayList<voterClass> voterClassArrayList;
    private boolean Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        voterClassArrayList = new ArrayList<voterClass>();
        candidateSpinner = findViewById(R.id.Spinner);
        toggleButton = findViewById(R.id.toggleButton);
        submitVote = findViewById(R.id.button);
        Takename = findViewById(R.id.editTextTextPersonName);
        Takeid = findViewById(R.id.editTextTextPersonID);


        Intent intent = getIntent();
        ArrayList<Candidate_class> candidates = (ArrayList<Candidate_class>) intent.getSerializableExtra("candidates");
        candidate_classArrayList = candidates;
        ArrayAdapter<Candidate_class> adapter = new ArrayAdapter<Candidate_class>(this,
                android.R.layout.simple_spinner_item, candidate_classArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidateSpinner.setAdapter(adapter);


        submitVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Takename.getText().toString().isEmpty()){
                    Toast.makeText(secondActivity.this, " Fill  name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Takeid.getText().toString().isEmpty()){
                    Toast.makeText(secondActivity.this, " Fill the Id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (voterClass V : voterClassArrayList) {
                    if(V.getId() == Integer.parseInt(Takeid.getText().toString())){
                        Toast.makeText(secondActivity.this, "Ohh! Id already avaliable ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!toggleButton.isChecked()){
                    Toast.makeText(secondActivity.this, "Accept the terms and condition firstly", Toast.LENGTH_SHORT).show();
                    return;
                }

                voterClassArrayList.add(new voterClass(Integer.parseInt(Takeid.getText().toString()), Takename.getText().toString()));
                int selectedCandidateIndex = candidateSpinner.getSelectedItemPosition();
                Candidate_class selectedCandidate = candidate_classArrayList.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(secondActivity.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    toggleButton.setTextOn("Declined");

                } else {

                    toggleButton.setTextOff("Accepted Terms");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(secondActivity.this, MainActivity.class);
        intent.putExtra("candidates", candidate_classArrayList);
        startActivity(intent);
    }
}
