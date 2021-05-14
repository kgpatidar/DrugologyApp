package com.example.drugology.Detail_Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.drugology.IUPACImage;
import com.example.drugology.R;
import com.example.drugology.Use;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 */
public class iupac_fragment extends Fragment {

    ImageView iupacImage;
    TextView iupacName;
    ArrayList<String> dosageformList = new ArrayList<>();

    public String key;
    public int isAntibiotic;
    public int position;

    public iupac_fragment(String key, int isAntibiotic, int position) {
        // Required empty public constructor
        this.key = key;
        this.isAntibiotic = isAntibiotic;
        this.position = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iupacView  = inflater.inflate(R.layout.fragment_iupac_fragment, container, false);


        iupacImage = iupacView.findViewById(R.id.iupacimageofdrug);
        iupacName = iupacView.findViewById(R.id.iupacnameofdruginiupacfragment);

        String url = "";

        IUPACImage image1 = new IUPACImage(getContext());
        if(!image1.isNetworkConnected() && dosageformList.isEmpty()) {
            Toast.makeText(getContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        }

        IUPACImage image = new IUPACImage();
        if(isAntibiotic==0) {
            url = image.AntibioticIUPAC(position);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIBIOTIC-IUPAC-NAME").child(key);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dosageformList.clear();
                    Use use = dataSnapshot.getValue(Use.class);
                    iupacName.setText(use.getUse());
                    dosageformList.add("Data");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Error In Loading", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            url = image.AntipyreticIUPAC(position);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ANTIPYRETIC-IUPAC-NAME").child(key);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dosageformList.clear();
                    Use use = dataSnapshot.getValue(Use.class);
                    iupacName.setText(use.getUse());
                    dosageformList.add("data");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        Glide.with(getContext())
                .load(url)
                .into(iupacImage);

        return iupacView;
    }



}
