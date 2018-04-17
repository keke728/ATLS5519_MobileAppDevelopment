package com.example.keke.babyface.UI;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.keke.babyface.Data.ImageAssets;
import com.example.keke.babyface.R;

public class MasterListFragment extends Fragment {

    //Mandatory empty constructor
    public MasterListFragment(){
    }

    //Inflates the GridView of all images
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.fragment_matser_list, container, false);

        //Get a reference to the GridView in the layout file
        GridView gridView = rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), ImageAssets.getAll());

        //Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        //Return the root view
        return rootView;
    }
}
