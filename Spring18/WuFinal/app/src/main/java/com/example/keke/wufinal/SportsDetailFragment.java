package com.example.keke.wufinal;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
public class SportsDetailFragment extends Fragment implements View.OnClickListener{

    private ArrayAdapter<String> adapter;
    private long typeId;

    //create listener
    private ButtonClickListener listener;


    public SportsDetailFragment() {
        // Required empty public constructor
    }

    //set the id
    public void setType(long id){
        this.typeId = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
           Handling the web intent
         */
        View view = inflater.inflate(R.layout.fragment_sports_detail, container, false);
        ListView lv = view.findViewById(R.id.detailListView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });


        if(savedInstanceState != null){
            typeId = savedInstanceState.getLong("typeId");
        }

        //if the list is empty, load it
        if(Sports.sports[0].getActivities().size() == 0){
            Sports.sports[0].loadSports(getActivity(), 0);
        }
        if(Sports.sports[1].getActivities().size() == 0){
            Sports.sports[1].loadSports(getActivity(), 1);
        }
        if(Sports.sports[2].getActivities().size() == 0){
            Sports.sports[2].loadSports(getActivity(), 2);
        }

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onStart(){
        super.onStart();

        View view = getView();
        ListView listSports = view.findViewById(R.id.detailListView);

        //get data
        ArrayList<String> sportList = new ArrayList<String>();
        sportList = Sports.sports[(int) typeId].getActivities();

        //set array adapter
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sportList);

        //bind array adapter to the list view
        listSports.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        //register context menu
        registerForContextMenu(listSports);
    }

    @Override public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("typeId", typeId);
    }


    //create interface
    interface ButtonClickListener{
        void addactivityclicked(View view);
    }

    @Override public void onAttach(Context context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (ButtonClickListener)context;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.addactivityclicked(v);
        }
    } public void addActivity(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Add Sport");
        dialog.setCancelable(true);
        final EditText editText = dialog.findViewById(R.id.editTextActivity);
        Button button = dialog.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String sportsName = editText.getText().toString();
                //add sports
                Sports.sports[(int) typeId].getActivities().add(sportsName);
                //refresh the listview
                SportsDetailFragment.this.adapter.notifyDataSetChanged();
                Sports.sports[(int) typeId].storeSports(getActivity(),typeId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        //caset ContextMenu.ContextMenuInfo to AdapterView.Adapter
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //get item that was pressed
        String itemname = adapter.getItem(adapterContextMenuInfo.position);
        //set the menu  title
        menu.setHeaderTitle("Delete  " + itemname + "?");
        //add the chocies to the menu
        menu.add(1,1,1,"YES");
        menu.add(2,2,2,"NO");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //get the id of the item
        int itemId = item.getItemId();
        if (itemId == 1) {
            //get the position of the menu item
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //remove the item
            Sports.sports[(int) typeId].getActivities().remove(info.position);
            //refresh the list view
            SportsDetailFragment.this.adapter.notifyDataSetChanged();
            Sports.sports[(int) typeId].storeSports(getActivity(), typeId);
        }
        return true;
    }
}
