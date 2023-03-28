package com.example.thecaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;
import com.example.thecaps.Game;


public class MainActivity extends AppCompatActivity {
    private Game game;
    private String question;
    private String answer;
    private int score=0;
    private int qNum=1;
    private String gameLog="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.score)).setText("Score = "+score);
        ((TextView) findViewById(R.id.qNum)).setText("Q# "+qNum);
        ask();
    }

    private void ask(){
        Game game = new Game();
        String result = game.qa();
        String[]QA=result.split("\n");
        this.question=QA[0];
        this.answer=QA[1];
        ((TextView) findViewById(R.id.question)).setText(question);
    }
    public void onDone(View view){
        if(qNum>=9){
            ((TextView)findViewById(R.id.qNum)).setText("Game Over!");
            Button b=(Button) findViewById(R.id.done);
            b.setEnabled(false);

        }
        else{
            String ans=((EditText) findViewById(R.id.answer)).getText().toString();
            ans=ans.toUpperCase();
            ((EditText)findViewById(R.id.answer)).setText("");

            if(ans.equalsIgnoreCase(this.answer)){
                this.score++;
                ((TextView)findViewById(R.id.score)).setText("Score = "+ score);
            }
            gameLog+="\nQ# "+qNum+":"+question+"\nYour answer: "+ans+"\nCorrect answer: "+answer+"\n";
            ((TextView)findViewById(R.id.log)).setText(gameLog);
            ((TextView)findViewById(R.id.qNum)).setText("Q# "+(++qNum));
            ask();
        }
    }
}