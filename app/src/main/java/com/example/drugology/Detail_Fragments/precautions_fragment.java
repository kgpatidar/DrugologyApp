package com.example.drugology.Detail_Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class precautions_fragment extends Fragment {

    ArrayList<String> dosageformList = new ArrayList<>();
    public String key;
    public boolean isAntibiotic;
    ListView listview;
    TextView commonInput;

    public precautions_fragment(String key, boolean isAntibiotic) {
        // Required empty public constructor
        this.key = key;
        this.isAntibiotic = isAntibiotic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_precautions_fragment, container, false);

        listview = view.findViewById(R.id.dosageformlistviewinfragment);
        IUPACImage image = new IUPACImage(getContext());
        if(!image.isNetworkConnected()&& dosageformList.isEmpty()) {
            Toast.makeText(getContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        }
        if(isAntibiotic) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIBIOTIC-PRECAUTIONS").child(key);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dosageformList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Use use = snapshot.getValue(Use.class);
                        dosageformList.add(use.getUse());
                    }
                    CustomeAdapter adapter = new CustomeAdapter();
                    listview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIPYRETIC-PRECAUTIONS").child(key);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dosageformList.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Use use = snapshot.getValue(Use.class);
                        dosageformList.add(use.getUse());
                    }
                    CustomeAdapter adapter = new CustomeAdapter();
                    listview.setAdapter(adapter);
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
            view = inflater.inflate(R.layout.common_card, parent, false);

            commonInput = view.findViewById(R.id.commoninputtextview);

            commonInput.setText(dosageformList.get(position));

            return view;
        }
    }
}
