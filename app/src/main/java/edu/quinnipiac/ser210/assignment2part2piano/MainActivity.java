package edu.quinnipiac.ser210.assignment2part2piano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.Listener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void itemClicked(long id){
        View fragmentContainer = findViewById(R.id.fragment_chord_container);
        if(fragmentContainer != null){
            //this code is creating a new chordlist fragment and is supposed to update it when the user
            //clicks in the main activity fragment
            ChordListFragment chordList = new ChordListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_chord_container,chordList);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            //activity runs as normal
            //intent intent new intent(this,ChordActivity)
            //put extra(detailactivity, pass along the item selected from the spinner
            //startactivity(intent)
        }
    }
}










