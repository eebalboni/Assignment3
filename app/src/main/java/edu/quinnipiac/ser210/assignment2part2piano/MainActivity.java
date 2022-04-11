package edu.quinnipiac.ser210.assignment2part2piano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.Listener{

    ArrayList<Chord> chords;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    public void onButtonClick(ArrayList<Chord> result) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        chords = result;
        if(fragmentContainer != null){
            ChordListFragment chordList = new ChordListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            chordList.setChordData(chords);
            ft.replace(R.id.fragment_container, chordList);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            Intent intent = new Intent(this, ChordsActivity.class);
            intent.putExtra("chords", chords);
            startActivity(intent);
        }
    }
}










