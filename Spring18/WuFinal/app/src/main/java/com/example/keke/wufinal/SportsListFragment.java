package com.example.keke.wufinal;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportsListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private SportsListListener listener;

    public SportsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart(){
         super.onStart();
         View view = getView();
         if(view != null){
             //load data into fragment
             //get the listview
             ListView listTypes = view.findViewById(R.id.listView);
             // define an array adapter
             ArrayAdapter<Sports> listAdapter = new ArrayAdapter<Sports>(getActivity(), android.R.layout.simple_list_item_1, Sports.sports);
             // set the array adapter on the list view
             listTypes.setAdapter(listAdapter);
             // attach the listener to the listview
             listTypes.setOnItemClickListener(this);
         }
    }

    // Create interface
    interface SportsListListener{
        void itemClicked(long id);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (SportsListListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports_list, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(listener != null){
            //tells the listener an item was clicked
            listener.itemClicked(id);
        }
    }
}
