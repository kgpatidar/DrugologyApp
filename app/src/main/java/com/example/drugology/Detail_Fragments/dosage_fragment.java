package com.example.drugology.Detail_Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugology.IUPACImage;
import com.example.drugology.R;
import com.example.drugology.Use;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.ToLongBiFunction;

/**
 * A simple {@link Fragment} subclass.
 */
public class dosage_fragment extends Fragment {

    ArrayList<String> dosageformList = new ArrayList<>();
    ArrayList<String> tempList = new ArrayList<>();
    public String key;
    public boolean isAntibiotic;
    ListView listview;
    TextView commonInput, subCommonInput;

    public dosage_fragment(String key, boolean isAntibiotic) {
        // Required empty public constructor
        this.key = key;
        this.isAntibiotic = isAntibiotic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dosage_fragment, container, false);

        listview = view.findViewById(R.id.dosageformlistviewinfragment);
        IUPACImage image = new IUPACImage(getContext());
        if(!image.isNetworkConnected() && dosageformList.isEmpty()) {
            Toast.makeText(getContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        }
        if (isAntibiotic) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIBIOTIC-DOSAGE").child(key);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dosageformList.clear();
                    tempList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String tempText =  getResources().getString(R.string.true_tick) + " ";
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Use use = snapshot1.getValue(Use.class);
                            tempText = tempText + use.getUse() + "\n\n" + getResources().getString(R.string.true_tick) + " ";
                        }
                        dosageformList.add(snapshot.getKey());
                        tempList.add(tempText.substring(0, tempText.length()-2));
                    }
                    CustomeAdapter customeAdapter = new CustomeAdapter();
                    listview.setAdapter(customeAdapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIPYRETIC-DOSAGE").child(key);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String exceptionKey = "-M1l-amxP3MoKU5NtXnq";
                    if(key.equals(exceptionKey)) {
                        dosageformList.clear();
                        tempList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Use use = snapshot.getValue(Use.class);
                            dosageformList.add(snapshot.getKey());
                            tempList.add(getResources().getString(R.string.true_tick) + " " + use.getUse() + "\n");
                        }
                    } else {
                        dosageformList.clear();
                        tempList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String tempText = getResources().getString(R.string.true_tick) + " ";
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                Use use = snapshot1.getValue(Use.class);
                                tempText = tempText + use.getUse() + "\n\n" + getResources().getString(R.string.true_tick) + " ";
                            }
                            dosageformList.add(snapshot.getKey());
                            tempList.add(tempText.substring(0, tempText.length()-2));
                        }
                    }
                    CustomeAdapter customeAdapter = new CustomeAdapter();
                    listview.setAdapter(customeAdapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        return view;
    }

    class CustomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dosageformList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view;
            view = inflater.inflate(R.layout.dosage_card, parent, false);

            commonInput = view.findViewById(R.id.dosageheadinginlist);
            subCommonInput = view.findViewById(R.id.subheadingofdosagelist);

            commonInput.setText(dosageformList.get(position));
            subCommonInput.setText(tempList.get(position));


            return view;
        }
    }


}
