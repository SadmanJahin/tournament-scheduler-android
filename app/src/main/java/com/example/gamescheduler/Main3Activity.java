package com.example.gamescheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.Random;

import java.util.HashMap;



public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button nextButton;
   private HashMap<Integer, String> nameMap,teamMap,fixtureMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuffledlist_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
         nameMap = (HashMap<Integer, String>) intent.getSerializableExtra("names");
        teamMap = (HashMap<Integer, String>) intent.getSerializableExtra("teams");
        fixtureMap=new HashMap<Integer, String>();
        TableLayout table = (TableLayout) findViewById(R.id.fixtureTableId);
        String name1,name2;
        int array[] = new int[100];
        int k=1;
        for(int i=0;i<array.length;i++)
            array[i] = 0;

                int mapSize=nameMap.size();
                if(mapSize %2 !=0)
                  mapSize+=1;
                for (int i = 1; i <= mapSize / 2; i++) {
                    TableRow row = new TableRow(this);

                    for (int j = 1; j <= 2; j++) {
                        TextView playerName1 = new TextView(this);
                        Random rand=new Random();
                       int key=rand.nextInt(mapSize)+1;
                       if(array[key]==0)
                       array[key]=1;
                       else
                       {
                           j--;
                           continue;
                       }
                        name1=nameMap.get(key);
                       fixtureMap.put(k,name1);
                       k++;
                        playerName1.setText(name1);
                        playerName1.setTextSize(20);
                        row.addView(playerName1);
                    }

                    table.addView(row);
                }
                nextButton= findViewById(R.id.nextbuttonId);
                nextButton.setOnClickListener(this);
            }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextbuttonId) {
            Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
            intent.putExtra("fixture", fixtureMap);
            intent.putExtra("teams", teamMap);
            startActivity(intent);
        }
    }
}
