package com.example.keke.phones;


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
public class PhoneListFragment extends Fragment implements AdapterView.OnItemClickListener{


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(listener != null){
            //tells the listener an item was clicked
            listener.itemClicked(id);
        }
    }

    //create interface
    interface PhoneListListener{
        void itemClicked(long id);
    }
    //create listener
    private PhoneListListener listener;

    public PhoneListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (PhoneListListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_list, container, false);
    }

    //create listener
    private PhoneListListener listListener;

    //Load value into fragment's listview
    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view != null){
            //load data into fragment
            //get the list view
            ListView listPlatform = (ListView)view.findViewById(R.id.listView);
            //define an array adapter
            ArrayAdapter<Phones>listAdapter = new ArrayAdapter<Phones>(getActivity(), android.R.layout.simple_list_item_1, Phones.phones);
            //set the array adapter on the list view
            listPlatform.setAdapter(listAdapter);
            //attach the listener to the list view
            listPlatform.setOnItemClickListener(this);
        }

    }


}
