package com.example.gamescheduler;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;


public class Main4Activity extends AppCompatActivity {

    private HashMap<Integer, String> teamMap,fixtureMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuffledlist2_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        fixtureMap = (HashMap<Integer, String>) intent.getSerializableExtra("fixture");
        teamMap = (HashMap<Integer, String>) intent.getSerializableExtra("teams");

        TableLayout table = (TableLayout) findViewById(R.id.fixtureTable2Id);
        int groupsize = fixtureMap.size() / 2;
        int matchNumber = (fixtureMap.size() - groupsize) * groupsize;
        String[][] finalFixture = new String[matchNumber + 1][2];
        int i, j, x = 1, y = 2;

        for (i = 1; i <= matchNumber; i++) {


            for (j = 0; j < 2; j++) {
                if (j == 0)
                    finalFixture[i][j] = fixtureMap.get(x);
                else {
                    finalFixture[i][j] = fixtureMap.get(y);
                    y += 2;
                }


            }
            if (i % groupsize == 0) {
                x += 2;
            }
            if (y > fixtureMap.size())
                y = 2;

        }
       /* for(i=1;i<=matchNumber;i++)
        {
            for(j=0;j<2;j++)
            { Log.d("tag",finalFixture[i][j] );
            }

        }*/
        int array[] = new int[50];
        for(int k=0;k<array.length;k++)
            array[k] = 0;

        for (i = 1; i <= matchNumber; i++)
        {
            TableRow row = new TableRow(this);
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=5;
            int topMargin=18;
            int rightMargin=0;
            int bottomMargin=0;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            row.setLayoutParams(tableRowParams);

            TableRow.LayoutParams tableRowparam = new TableRow.LayoutParams();
            tableRowparam.weight=0.50f;
            TextView playerName1 = new TextView(this);
            TextView teamName1 = new TextView(this);
            TextView playerName2 = new TextView(this);
            TextView teamName2 = new TextView(this);
            Random rand=new Random();

            int[] array2 = new int[30];
            for(int k=0;k<array2.length;k++)
                array2[k] = 0;

            int num1=rand.nextInt(matchNumber)+1;
            if(array[num1]==0)
                array[num1]=1;
            else
            {
                i--;
                continue;
            }

            playerName1.setText("      "+finalFixture[num1][0]);
            playerName1.setTextSize(15);
            playerName1.setTypeface(Typeface.DEFAULT_BOLD);
            playerName1.setLayoutParams(tableRowparam);
            row.addView(playerName1);
            int num2=rand.nextInt(teamMap.size())+1;
            array2[num2]=1;

            teamName1.setText(teamMap.get(num2)+"     ");
            teamName1.setTextSize(15);
            teamName1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            row.addView(teamName1);

            playerName2.setText("        "+finalFixture[num1][1]);
            playerName2.setTextSize(15);
            playerName2.setTypeface(Typeface.DEFAULT_BOLD);
            playerName2.setLayoutParams(tableRowparam);
            row.addView(playerName2);

            while(array2[num2]!=0)
            num2=rand.nextInt(teamMap.size())+1;

            teamName2.setText(teamMap.get(num2));
            teamName2.setTextSize(15);
            teamName2.setLayoutParams(tableRowparam);
            teamName2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            if(i%2==0)
            row.setBackgroundResource(R.color.colorAccent);
            row.addView(teamName2);



           table.isStretchAllColumns();
            table.addView(row);
        }


    }
}
