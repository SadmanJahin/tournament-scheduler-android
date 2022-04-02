package com.example.gamescheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] Gamelist;
    private Spinner spinner;
    private Button goButton;
    private LinearLayout inputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameselect_layout);
               getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Gamelist = getResources().getStringArray(R.array.game_lists);
        spinner=(Spinner) findViewById(R.id.spinnerId);
        goButton=findViewById(R.id.gobuttonId);
        inputLayout=findViewById(R.id.playerinput);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner1_layout, R.id.textviewSpinnerId,Gamelist);
        spinner.setAdapter(adapter);
      goButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.gobuttonId) {
            String gameItem =spinner.getSelectedItem().toString();
            if(gameItem.equals("Fifa 19")) {
                Intent intent = new Intent(MainActivity.this, GameSelectedActivity.class);
                startActivity(intent);
            }
            else
            {
             Toast toast = Toast.makeText(MainActivity.this,"NOT AVAILABLE",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }


    }
}
