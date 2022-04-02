package com.example.gamescheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button okButton,generateButton;
    private ImageButton addButton;
    private LinearLayout inputLayout;
    private EditText nameText, teamText;
    private HashMap<String,String> nameteam=new HashMap<>();
    private  HashMap<Integer,String> playermap=new HashMap<Integer,String>();
    private  HashMap<Integer,String> teammap=new HashMap<Integer,String>();
    private int key=1;
    private ListView playerlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addButton = findViewById(R.id.addbuttonId);
        okButton = findViewById(R.id.inputOk);
        generateButton = findViewById(R.id.generateButtonId);
        inputLayout = findViewById(R.id.playerinput);

        nameText = findViewById(R.id.nametext);
        teamText = findViewById(R.id.teamtext);

        playerlistView=findViewById(R.id.playerListview);

        addButton.setOnClickListener(this);
        okButton.setOnClickListener(this);
        generateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addbuttonId)
            inputLayout.setVisibility(LinearLayout.VISIBLE);
        if (v.getId() == R.id.inputOk) {
            inputLayout.setVisibility(LinearLayout.GONE);
            playermap.put(key,nameText.getText().toString());
            teammap.put(key,teamText.getText().toString());
            key++;
            nameteam.put("Name: "+nameText.getText().toString(),"Team: "+teamText.getText().toString());
            nameText.setText("");
            teamText.setText("");
            //Log.d("tag", nameList.toString() + teamList.toString());

            List<HashMap<String,String>> listItems= new ArrayList<>();
            SimpleAdapter adapter=new SimpleAdapter(this,listItems,R.layout.listview_layout,new String[]{"First Line","2nd Line"},
                    new int[]{R.id.nameListId,R.id.teamListId});
            Iterator itr =nameteam.entrySet().iterator();
            while(itr.hasNext())
            {
                HashMap<String,String> resultMap=new HashMap<>();
                Map.Entry pair=(Map.Entry) itr.next();
                resultMap.put("First Line",pair.getKey().toString());
                resultMap.put("2nd Line",pair.getValue().toString());
                listItems.add(resultMap);
            }
            playerlistView.setAdapter(adapter);
        }
        if (v.getId() == R.id.generateButtonId)
        {
            Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
            intent.putExtra("names",playermap);
            intent.putExtra("teams",teammap);
            startActivity(intent);
        }
    }
}