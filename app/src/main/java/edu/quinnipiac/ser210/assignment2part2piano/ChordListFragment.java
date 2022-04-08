package edu.quinnipiac.ser210.assignment2part2piano;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChordListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChordListFragment extends Fragment implements Toolbar.OnMenuItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Chord> mChordData;
    private ChordAdapter mChordAdapter;
    private Menu menu;
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_shuffle:{
                shuffle();
                return true;
            }
            case R.id.action_random:{
                random();
                return true;
            }
            default:
                return false;
        }
    }

    static interface Listener{
        boolean toolbarItemClicked(MenuItem item);
    }

    private Listener listener;



    // TODO: Rename and change types and number of parameters
    public static ChordListFragment newInstance(String param1, String param2) {
        ChordListFragment fragment = new ChordListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChordListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_chord_list,container,false);

        //code from online https://stackoverflow.com/questions/29020935/using-toolbar-with-fragments#comment103001314_54361849
        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        menu = toolbar.getMenu();

        mRecyclerView = layout.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(layout.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //temp data
        mChordData = new ArrayList<Chord>();
        String[] b = {"b"};
        mChordData.add(new Chord("b",b,b));

        mChordAdapter = new ChordAdapter(this.getActivity(), mChordData);
        mRecyclerView.setAdapter(mChordAdapter);

        return layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }


    public void setChordData(ArrayList<Chord> chords){
        mChordData = chords;
        mChordAdapter.filterList(mChordData);
    }


    //Searches for a chord using a given string from the Search View
    public void filter(String text){
        ArrayList<Chord> filteredList = new ArrayList<>();

        for(Chord item : mChordData){
            if(item.getRoot().toLowerCase().contains(text.toLowerCase(Locale.ROOT))){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Chords Found", Toast.LENGTH_SHORT).show();
        }else{
            mChordAdapter.filterList(filteredList);
        }
    }

    //Shuffles the recycle view
    public void shuffle(){
        Collections.shuffle(mChordData, new Random());
        mChordAdapter.filterList(mChordData);
    }

    //Scrolls to a random position in the recycle view, resulting a random chord being shown
    public void random(){
        int pos = (int) (Math.random() * mChordData.size());
        linearLayoutManager.scrollToPositionWithOffset(pos, 0);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        //Listener for the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



}