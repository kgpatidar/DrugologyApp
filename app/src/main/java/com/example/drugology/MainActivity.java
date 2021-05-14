package com.example.drugology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.drugology.Drug_Category;

public class MainActivity extends AppCompatActivity {

Button drug_category,drug_information,marketed_drug_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drug_category=findViewById(R.id.drug_category);
        drug_information=findViewById(R.id.drug_information);
        marketed_drug_list=findViewById(R.id.marketed_drug_list);

        drug_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Drug_Category.class);
                startActivity(i);
            }
        });

        drug_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Drug_Category.class);
                startActivity(i);
            }
        });

        marketed_drug_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Marketeddrug.class);
                intent.putExtra("isantibiotic", false);
                startActivity(intent);
            }
        });

        findViewById(R.id.aboutus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for about U
            }
        });

    }
}
