package com.example.keke.phones;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends Activity implements PhoneListFragment.PhoneListListener, PhoneDetailFragment.ButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load data
        if (Phones.phones.isEmpty()) {
            loadPhones(this);
        }
    }

    public void loadPhones(Context context) {
        //get access to a shared preferences file
        SharedPreferences sharedPreferences = context.getSharedPreferences("MobilePhones", Context.MODE_PRIVATE);
        //create an editor to read from the shared preferences file
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //load all data into a Map
        Map<String, ?> alldata = sharedPreferences.getAll();
        //if there's data in tge map
        if (alldata.size() > 0) {
            for (Map.Entry<String, ?> entry : alldata.entrySet()) {
                //key is the platform name
                String newplatform = (String) entry.getKey();
                ArrayList<String> phonelist = new ArrayList<String>();
                //add the phones String set to the ArrayList
                phonelist.addAll(sharedPreferences.getStringSet(newplatform, null));
                //create new Phones object
                Phones new_phone = new Phones(newplatform, phonelist);
                //add phones
                Phones.phones.add(new_phone);
            }
        } else //load XML file
            try {
                loadXmL(this);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void loadXmL(Activity activity) throws XmlPullParserException, IOException {
        Log.d("xml", "inloadxml");
        String new_platform = new String();
        ArrayList<String> new_phones = new ArrayList<String>();
        //string for debugging purposes
        StringBuffer stringBuffer = new StringBuffer();
        //get xml file
        XmlResourceParser xpp = getResources().getXml(R.xml.phones);
        //advances the parser to the next event
        xpp.next();
        //gets the event type of the parser
        int eventType = xpp.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    //start of document
                    break;
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("platform")){
                        stringBuffer.append("\nSTART_TAG: "+ xpp.getName());
                    }
                    if(xpp.getName().equals("name")) {
                        stringBuffer.append("\nSTART_TAG: " +xpp.getName());
                        eventType = xpp.next();
                        new_platform = xpp.getText(); //gets the name of the platform
                    }else if(xpp.getName().equals("phones")){
                        stringBuffer.append("\nSTART_TAG: " + xpp.getName());
                        eventType = xpp.next();
                        //add to arraylist
                        new_phones.add(xpp.getText());//gets the name of the phone
                    }
                    break;
                case XmlPullParser.END_TAG:
                    //if end of platform add the new phone
                    if(xpp.getName().equals("platform")){
                        //at the end of the platform
                        //create new phone object
                        Phones new_phone = new Phones(new_platform, new_phones);
                        //add phones
                        Phones.phones.add(new_phone);
                        //clear platform name and list of phones
                        new_platform= "";
                        new_phones.clear();
                    }
                    break;
                case XmlPullParser.TEXT:
                    break;
            }
            eventType = xpp.next();
        }
        System.out.println(stringBuffer.toString());
    }


    //create a new fragment when user clicks
    @Override
    public void itemClicked(long id){
        //get a reference to the frame layout that contains PhoneDetailFragment
        View fragmentContainer = findViewById(R.id.fragment_container);
        //large layout device
        if(fragmentContainer != null){
            //create new fragment instance
            PhoneDetailFragment frag = new PhoneDetailFragment();
            //set the id of the platform selected
            frag.setPlatform(id);
            //create new fragment transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //replace the fragment in the fragment container
            ft.replace(R.id.fragment_container,frag);
            //add fragment to the back stack
            ft.addToBackStack(null);
            //set the transition animation-optional
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //commit the transaction
            ft.commit();
        }else{ //app running on a smaller screen
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", (int)id);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void addphoneclicked(View view) {
        PhoneDetailFragment fragment = (PhoneDetailFragment)getFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.addphone();
    }
}
