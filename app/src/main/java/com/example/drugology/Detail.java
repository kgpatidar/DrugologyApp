package com.example.drugology;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugology.Detail_Fragments.adverse_effect_fragment;
import com.example.drugology.Detail_Fragments.contraindication_fragment;
import com.example.drugology.Detail_Fragments.dosage_form_fragment;
import com.example.drugology.Detail_Fragments.dosage_fragment;
import com.example.drugology.Detail_Fragments.iupac_fragment;
import com.example.drugology.Detail_Fragments.mode_of_action_fragment;
import com.example.drugology.Detail_Fragments.precautions_fragment;
import com.example.drugology.Detail_Fragments.uses_fragment;

import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {

    TextView test;
    private RecyclerView recyclerView;
    TextView headingview, drugnameinhead;
    private MoviesAdapter mAdapter;
    ArrayList<String> tab;
    String drugKey, drugName;
    public static FragmentManager fragmentManager;
    ArrayList<Fragment> fragmentsList;

    int positionOfDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        drugKey = intent.getStringExtra("drugid");
        drugName = intent.getStringExtra("drugname");
        positionOfDrug = Integer.parseInt(intent.getStringExtra("positiondrug"));
        boolean isAntibiotic = intent.getBooleanExtra("isantibitic", true);



        fragmentManager = getSupportFragmentManager();
        fragmentsList = new ArrayList<>();

        fragmentsList.add(new iupac_fragment(drugKey, isAntibiotic?0:1, positionOfDrug));
        fragmentsList.add(new dosage_form_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new dosage_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new mode_of_action_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new uses_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new adverse_effect_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new contraindication_fragment(drugKey, isAntibiotic));
        fragmentsList.add(new precautions_fragment(drugKey, isAntibiotic));

        if (findViewById(R.id.fragment_container)!=null) {
            if (savedInstanceState!=null) {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            iupac_fragment iupac_fragment = new iupac_fragment(drugKey, isAntibiotic?0:1, positionOfDrug);
            fragmentTransaction.add(R.id.fragment_container,iupac_fragment,null);
            fragmentTransaction.commit();

        }


        headingview = findViewById(R.id.headingofdrug);
        drugnameinhead = findViewById(R.id.drugnameindetail);


        drugnameinhead.setText(drugName);
        headingview.setText( " - " + "IUPAC");
        recyclerView = findViewById(R.id.horizontaltaview);

        tab = new ArrayList<>();
        tab.add("IUPAC");
        tab.add("Dosage Form");
        tab.add("Dosage");
        tab.add("Mode of Action");
        tab.add("Uses");
        tab.add("Adverse Effect");
        tab.add("Contraindication");
        tab.add("Precautions");

//        mAdapter = new MoviesAdapter(tab);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(mAdapter);
        mAdapter = new MoviesAdapter(tab);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    //adapter class
    public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

        private List<String> moviesList;
        public int index = -1;
        List<View> itemViewList = new ArrayList<>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public CardView clickview;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.topicname);
                clickview = view.findViewById(R.id.cardviewtitle);
            }
        }


        public MoviesAdapter(List<String> moviesList) {
            this.moviesList = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizentalcard, parent, false);
            final MyViewHolder myViewHolder = new MyViewHolder(itemView);



            return myViewHolder;
//            return new MyViewHolder(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.title.setText(moviesList.get(position));



            holder.clickview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = position;
                    notifyDataSetChanged();
                }
            });
            if (index == position) {
                Detail.fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentsList.get(position),null).commit();
                holder.clickview.setCardBackgroundColor(getColor(R.color.cardColortitle));
                holder.title.setTextColor(getColor(android.R.color.white));
                holder.title.setTypeface(null, Typeface.BOLD);
                drugnameinhead.setText(drugName);
                headingview.setText(" - " + moviesList.get(position));
            } else {
                holder.clickview.setCardBackgroundColor(getColor(android.R.color.white));
                holder.title.setTypeface(null, Typeface.NORMAL);
                holder.title.setTextColor(getColor(R.color.backgraycolor));
            }
        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }

    }


}
