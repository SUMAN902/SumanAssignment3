package com.example.sumanassignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Candidate_class> candidate_list;
    private TextView candidate_list1, candidate_list2, candidate_list3;
    private Button voterbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidate_list1 = findViewById(R.id.txtViewCandidate1);
        candidate_list2 = findViewById(R.id.txtViewCandidate2);
        candidate_list3 = findViewById(R.id.txtViewCandidate3);

        voterbutton = findViewById(R.id.voting_button);

        candidate_list = new ArrayList<Candidate_class>();
        Intent intent = getIntent();

        ArrayList<Candidate_class> candidates = (ArrayList<Candidate_class>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidate_list.add(new Candidate_class(1,"Suman",0));
            candidate_list.add(new Candidate_class(2,"Kaur",0));
            candidate_list.add(new Candidate_class(3,"Preet",9));
        }
        else{
            candidate_list = candidates;
        }

        candidate_list1.setText(candidate_list.get(0).getName()+" : " + candidate_list.get(0).getVotes());
        candidate_list2.setText(candidate_list.get(1).getName()+" : " + candidate_list.get(1).getVotes());
        candidate_list3.setText(candidate_list.get(2).getName()+" : " + candidate_list.get(2).getVotes());

        voterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                intent.putExtra("candidates", candidate_list);
                startActivity(intent);
            }
        });


    }
}