package com.example.expandablelistviewsecondway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expandablelistviewsecondway.CustomAdapter;
import com.example.expandablelistviewsecondway.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private CustomAdapter customAdapter;
    List<String> listDataHeader;//sob header k aksathe rakhar jonno ai list declared hoyeche..
    HashMap<String, List<String>> listDataChild;//child gulo rakhar jonno hashMap use hoyeche..<header, child>

    private int lastExpandablePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareListData();

        expandableListView = findViewById(R.id.expandableLVId);
        customAdapter = new CustomAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(customAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String groupName = listDataHeader.get(groupPosition);

                Toast.makeText(getApplicationContext(), groupName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String groupName = listDataHeader.get(groupPosition);

                Toast.makeText(getApplicationContext(), groupName + " is collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(), childString, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandablePosition != -1 && lastExpandablePosition != groupPosition)
                {
                    expandableListView.collapseGroup(lastExpandablePosition);
                }
                lastExpandablePosition = groupPosition;
            }
        });

    }

    public void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        //akhne akta akta kore header headerString theke listDataHeader String a add hobe
        //akhne akta akta kore child childString theke listDataChild String a add hobe
        //a kajta akta loop er modde korte hobe
        listDataHeader.add("1. OverView");

        List<String> overView = new ArrayList<>();

        overView.add("1.1 what is c language");
        overView.add("1.2 what is History of c");
        overView.add("1.3 what is Features of c");
        overView.add("1.4 what is Advantages of c");

        listDataChild.put(listDataHeader.get(0), overView);
    }
}