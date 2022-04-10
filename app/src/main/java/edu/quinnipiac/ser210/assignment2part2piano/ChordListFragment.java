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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChordListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChordListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Chord> mChordData;
    private ChordAdapter mChordAdapter;

    static interface Listener{
        boolean toolbarItemClicked(MenuItem item);
    }

    private Listener listener;



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

        Toolbar myToolbar = (Toolbar) layout.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(listener != null){
            listener.toolbarItemClicked(item);
            return true;
        }else{
            return false;
        }
    }

    public void setChordData(ArrayList<Chord> chords){
        mChordData = chords;
        mChordAdapter.filterList(mChordData);
    }



}