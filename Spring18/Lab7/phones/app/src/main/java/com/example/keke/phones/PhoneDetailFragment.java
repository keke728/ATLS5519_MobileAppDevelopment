package com.example.keke.phones;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */


public class PhoneDetailFragment extends Fragment implements View.OnClickListener {



    //create interface
    interface ButtonClickListener{
        void addphoneclicked(View view);
    }
    //create listener
    private ButtonClickListener listener;

    @Override public void onAttach(Context context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (ButtonClickListener)context;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.addphoneclicked(view);
        }
    }


    //array adapter
    private ArrayAdapter<String>adapter;

    //id of the platform chosen
    private long platformId;
    //set the platform id
    public void setPlatform(long id){
        this.platformId = id;
    }

    public PhoneDetailFragment() {
        // Required empty public constructor
    }


    //addPhone method
    public void addphone(){
        //create alert dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        //create edit text
        final EditText editText = new EditText(getActivity());
        //add edit text to dialog
        dialog.setView(editText);
        //set dialog title
        dialog.setTitle("Add Phone");
        //set OK action
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get phone name entered
                String phoneName = editText.getText().toString();
                if(!phoneName.isEmpty()){
                    //add phone
                    Phones.phones[(int)platformId].getMobilephones().add(phoneName);
                    //refresh the list view
                    PhoneDetailFragment.this.adapter.notifyDataSetChanged();
                }
            }
        });
        //sets cancel action
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //cancel
            }
        });
        //present alert dialog
        dialog.show();
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        ListView listPhones = (ListView)view.findViewById(R.id.phonelistView);

        //get phone data
        ArrayList<String>phonelist = new ArrayList<String>();
        phonelist = Phones.phones[(int)platformId].getMobilephones();

        //set array adapter
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, phonelist);
        //bind array adapter to the list view
        listPhones.setAdapter(adapter);

        //attach the listener to button
        Button addPhoneButton = (Button)view.findViewById(R.id.addButton);
        addPhoneButton.setOnClickListener(this);

        //register the context menu
        registerForContextMenu(listPhones);
    }

    //save state when rotate
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("platformId", platformId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //check if there was a saved instance and retrieve the id
        if(savedInstanceState !=null){
            platformId = savedInstanceState.getLong("platformId");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_detail, container, false);
    }

    //A context menu on the long press
    @Override public void onCreateContextMenu(ContextMenu menu,View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        //cast ContextMenu.ContextMenuInfo to AdapterView.AdapterContextMenuInfo
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        //get phone name that was pressed
        String phoneName = adapter.getItem(adapterContextMenuInfo.position);
        //set the menu title
        menu.setHeaderTitle("Delete" + phoneName);
        //add the choices to the menu
        menu.add(1,1,1, "Yes");
        menu.add(2,2,2,"No");

    }

    //handle the delete when choosing a menu item
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //get the id of the item
        int itemId = item.getItemId();
        if(itemId == 1){ //if yes menu item was pressed
            //get the position of the menu item
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //remove the phone
            Phones.phones[(int)platformId].getMobilephones().remove(info.position);
            //refresh the list view
            PhoneDetailFragment.this.adapter.notifyDataSetChanged();
        }
        return true;
    }
}
