package com.example.drugology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Drug_Category extends AppCompatActivity {

    String s1[];
    ListView recyclerView;
    ArrayList<String> antibioticDrugName,drugIDList,positionOfDrug, tempOfdrugPosition, tempList, idTempList;
    AutoCompleteTextView search;
    TextView drugname;
    Button searchButtton , visitInfoBtn;

    boolean isAntiBbioticActive = true;

    ArrayAdapter<String> searcadapter;

    Button antibiticBtn, antipyreticBtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug__category);

        positionOfDrug = new ArrayList<>();
        tempOfdrugPosition = new ArrayList<>();

        for(int i=0;i<10;i++) {
            positionOfDrug.add(String.valueOf(i));
        }

        drugIDList = new ArrayList<>();
        antibioticDrugName = new ArrayList<>();
        antibioticDrugName.add("Tetracycline");
        antibioticDrugName.add("Azithromycin");
        antibioticDrugName.add("Chloromphenicol");
        antibioticDrugName.add("Ampicilin");
        antibioticDrugName.add("Isoniazide");
        antibioticDrugName.add("Cyprofloxacin");
        antibioticDrugName.add("Streptomycin");
        antibioticDrugName.add("Acyclovir");
        antibioticDrugName.add("Metronidazole");
        antibioticDrugName.add("Sulphonamides");

        //antibiotic ids
        drugIDList.add("-M1l-7mwt_MGG1Tih5wH");
        drugIDList.add("-M1l-7n4yvEMdmNTuMJr");
        drugIDList.add("-M1l-7n6EYG393M19jzu");
        drugIDList.add("-M1l-7n9ZntmkdQsPpCD");
        drugIDList.add("-M1l-7nBztgV21e032D0");
        drugIDList.add("-M1l-7nETfHOtuokcWrS");
        drugIDList.add("-M1l-7nH0-s8xpkYOFDC");
        drugIDList.add("-M1l-7nJ4WcB-rd_tDg4");
        drugIDList.add("-M1l-7nLM5QXkh4L_Xr9");
        drugIDList.add("-M1l-7nNtU9vpAgqVN93");


        antibiticBtn = findViewById(R.id.antibiticbutton);
        antipyreticBtn = findViewById(R.id.antipyreticbutton);
        search = findViewById(R.id.searceditText);
        tempList = new ArrayList<>();
        idTempList = new ArrayList<>();
        s1 = getResources().getStringArray(R.array.drugs_name);
        recyclerView = findViewById(R.id.recyclerview);
        searchButtton = findViewById(R.id.button4);
        antipyreticBtn.setEnabled(true);
        antibiticBtn.setEnabled(false);;
        antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btninactive));
        antibiticBtn.setBackground(getDrawable(R.drawable.analgesic_btn));

        //searching button
        searchButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchButtton.getText().equals("Clear")) {
                    searchButtton.setText("Find");
                    search.setText("");
                    idTempList.clear();
                    tempList.clear();
                    tempOfdrugPosition.clear();
                    CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
                    recyclerView.setAdapter(adapter);
                } else {
                    tempList.clear();
                    String nameOfDrugtosearch = search.getText().toString().trim();
                    if (TextUtils.isEmpty(nameOfDrugtosearch)) {
                        CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
                        recyclerView.setAdapter(adapter);
                        searchButtton.setText("Find");
                        idTempList.clear();
                        tempList.clear();
                        tempOfdrugPosition.clear();
                    } else {
                        int indexofsearchElement = antibioticDrugName.indexOf(nameOfDrugtosearch);
                        if (indexofsearchElement < 0) {
                            idTempList.clear();
                            tempList.clear();
                            tempOfdrugPosition.clear();
                            Toast.makeText(getApplicationContext(), "No Result Found!", Toast.LENGTH_SHORT).show();
                            search.setText("");
                        } else {
                            searchButtton.setText("Clear");
                            idTempList.add(drugIDList.get(indexofsearchElement));
                            tempList.add(antibioticDrugName.get(indexofsearchElement));
                            tempOfdrugPosition.add(positionOfDrug.get(indexofsearchElement));
                            CustomeAdapter adapter = new CustomeAdapter(tempList, idTempList, tempOfdrugPosition);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }
        });

        //search button
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    idTempList.clear();
                    tempList.clear();
                    tempOfdrugPosition.clear();
                    String nameOfDrugtosearch = search.getText().toString().trim();
                    int indexofsearchElement = antibioticDrugName.indexOf(nameOfDrugtosearch);
                    if(indexofsearchElement<0) {
                        CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
                        recyclerView.setAdapter(adapter);
                        searchButtton.setText("Find");
                    }
                }
                return false;
            }
        });


        //swapping betwwen tabs
        antipyreticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAntiBbioticActive) {
                    antipyreticBtn.setEnabled(false);
                    antibiticBtn.setEnabled(true);
                    antibiticBtn.setBackground(getDrawable(R.drawable.analgesic_btninactive));
                    antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btn));
                    isAntiBbioticActive = false;

                    antibioticDrugName.clear();
                    antibioticDrugName.add("Naproxin");
                    antibioticDrugName.add("Mefenamic Acid");
                    antibioticDrugName.add("Pyroxicam");
                    antibioticDrugName.add("Indomethacin");
                    antibioticDrugName.add("Metamizole");
                    antibioticDrugName.add("Dicolofenac");
                    antibioticDrugName.add("Ibuprofin");
                    antibioticDrugName.add("Nimosulide");
                    antibioticDrugName.add("Celecoxib");
                    antibioticDrugName.add("Paracetamol");

                    positionOfDrug.clear();
                    for(int i=0;i<10;i++) {
                        positionOfDrug.add(String.valueOf(i));
                    }


                    //antipyretic id
                    drugIDList.clear();
                    drugIDList.add("-M1l-amNYRcISWCCHytT");
                    drugIDList.add("-M1l-amYztieXoigoqCp");
                    drugIDList.add("-M1l-amaJ4P4u0voTtO2");
                    drugIDList.add("-M1l-amcY3VrFsjlmjLu");
                    drugIDList.add("-M1l-amfwUxGhXYRP6Ln");
                    drugIDList.add("-M1l-amnP_GYp5xzehZN");
                    drugIDList.add("-M1l-amp9StAyT4QamEr");
                    drugIDList.add("-M1l-amsnTsWLI1xsJXc");
                    drugIDList.add("-M1l-amvwmysVfBwJOqO");
                    drugIDList.add("-M1l-amxP3MoKU5NtXnq");




                    CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
                    recyclerView.setAdapter(adapter);

                    searcadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, antibioticDrugName);
                    search.setAdapter(searcadapter);
                }
            }
        });
        antibiticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAntiBbioticActive) {
                    antipyreticBtn.setEnabled(true);
                    antibiticBtn.setEnabled(false);
                    antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btninactive));
                    antibiticBtn.setBackground(getDrawable(R.drawable.analgesic_btn));
                    isAntiBbioticActive = true;

                    antibioticDrugName.clear();
                    antibioticDrugName.add("Tetracycline");
                    antibioticDrugName.add("Azithromycin");
                    antibioticDrugName.add("Chloromphenicol");
                    antibioticDrugName.add("Ampicilin");
                    antibioticDrugName.add("Isoniazide");
                    antibioticDrugName.add("Cyprofloxacin");
                    antibioticDrugName.add("Streptomycin");
                    antibioticDrugName.add("Acyclovir");
                    antibioticDrugName.add("Metronidazole");
                    antibioticDrugName.add("Sulphonamides");


                    positionOfDrug.clear();
                    for(int i=0;i<10;i++) {
                        positionOfDrug.add(String.valueOf(i));
                    }

                    //antibiotic
                    drugIDList.clear();
                    drugIDList.add("-M1l-7mwt_MGG1Tih5wH");
                    drugIDList.add("-M1l-7n4yvEMdmNTuMJr");
                    drugIDList.add("-M1l-7n6EYG393M19jzu");
                    drugIDList.add("-M1l-7n9ZntmkdQsPpCD");
                    drugIDList.add("-M1l-7nBztgV21e032D0");
                    drugIDList.add("-M1l-7nETfHOtuokcWrS");
                    drugIDList.add("-M1l-7nH0-s8xpkYOFDC");
                    drugIDList.add("-M1l-7nJ4WcB-rd_tDg4");
                    drugIDList.add("-M1l-7nLM5QXkh4L_Xr9");
                    drugIDList.add("-M1l-7nNtU9vpAgqVN93");

                    CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
                    recyclerView.setAdapter(adapter);

                    searcadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, antibioticDrugName);
                    search.setAdapter(searcadapter);
                }
            }
        });





        searcadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, antibioticDrugName);
        search.setAdapter(searcadapter);



        CustomeAdapter adapter = new CustomeAdapter(antibioticDrugName, drugIDList, positionOfDrug);
        recyclerView.setAdapter(adapter);

    }


    class CustomeAdapter extends BaseAdapter {
        
        ArrayList<String> pick = new ArrayList<String>();
        ArrayList<String> idOfDrug = new ArrayList<>();
        ArrayList<String> posList = new ArrayList<>();
        
        public CustomeAdapter (ArrayList<String> tut, ArrayList<String> id, ArrayList<String> listofpos) {
            pick.clear();
            pick.addAll(tut);

            idOfDrug.clear();
            idOfDrug.addAll(id);

            posList.clear();
            posList.addAll(listofpos);
        }

        @Override
        public int getCount() {
            return pick.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.carddesign, parent, false);
            drugname = convertView.findViewById(R.id.mytext1);
            visitInfoBtn = convertView.findViewById(R.id.buttonView);

            drugname.setText(pick.get(position));

            visitInfoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Drug_Category.this , Detail.class);
                    intent.putExtra("drugid", idOfDrug.get(position));
                    intent.putExtra("drugname", pick.get(position));
                    intent.putExtra("positiondrug", posList.get(position));
                    intent.putExtra("isantibitic", isAntiBbioticActive);
                    startActivity(intent);
                }
            });


            return convertView;
        }
    }


}
