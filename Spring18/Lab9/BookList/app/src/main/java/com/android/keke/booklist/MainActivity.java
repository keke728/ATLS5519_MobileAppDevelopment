package com.android.keke.booklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference bookref = database.getReference("booklist");

    //array list of books
    List booklist = new ArrayList<>();
    //array adapter
    ArrayAdapter<BookItem> listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a vertical linear layout to hold edit texts
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                //create edit texts and add to layout
                final EditText nameEditText = new EditText(MainActivity.this);
                nameEditText.setHint("Book Name");
                layout.addView(nameEditText);
                final EditText urlEditText = new EditText(MainActivity.this);
                urlEditText.setHint("URL");
                layout.addView(urlEditText);

                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Book");
                dialog.setView(layout);
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get entered data
                        String bookName = nameEditText.getText().toString();
                        String bookURL = urlEditText.getText().toString();
                        if (bookName.trim().length() > 0) {

                            //get new id from firebase
                            String key = bookref.push().getKey();

                            //create new recipe item
                            BookItem newBook = new BookItem(key, bookName, bookURL);
                            //add to Firebase
                            bookref.child(key).child("name").setValue(newBook.getName());
                            bookref.child(key).child("url").setValue(newBook.getUrl());
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });

        ListView bookList = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<BookItem>(this, android.R.layout.simple_list_item_1, booklist);
        bookList.setAdapter(listAdapter);

    //Read from the DB
    ValueEventListener firebaseListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            booklist.clear();
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                String newId = snapshot.getKey();
                BookItem bookItem = snapshot.getValue(BookItem.class);
                BookItem newBook = new BookItem(newId, bookItem.getName(), bookItem.getUrl());
                booklist.add(newBook);
            }
            //update adapter
            listAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("oncreate", "Failed to read value", databaseError.toException());
        }
    };


        //add listener to the DB node reference
        bookref.addValueEventListener(firebaseListener);
        registerForContextMenu(bookList);

        //create listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                //get tapped book
                BookItem bookTapped = (BookItem) booklist.get(position);
                //get the url
                String bookURL = bookTapped.getUrl();
                //create new intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //add url to intent
                intent.setData(Uri.parse(bookURL));
                //start intent
                startActivity(intent);
            }
        };
        bookList.setOnItemClickListener(itemClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        //cast ContextMenu.ContextMenuInfo to AdapterView.AdapterContextMenuInfo since we're using an adapter
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //get book name that was pressed
        String bookname = ((TextView) adapterContextMenuInfo.targetView).getText().toString();
        //set the menu title
        menu.setHeaderTitle("Delete " + bookname);
        //add the choices to the menu
        menu.add(1, 1, 1, "Yes");
        menu.add(2, 2, 2, "No");
    }

    @Override public boolean onContextItemSelected(MenuItem item){
        //get the id of the item
        int itemId = item.getItemId();
        if (itemId == 1) { //if yes menu item was pressed
            //get the position of the menu item
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //get book that was pressed
            BookItem selectedRecipe = (BookItem) booklist.get(info.position);
            //get book id
            String bookid = selectedRecipe.getId();
            //delete from Firebase
            bookref.child(bookid).removeValue();
        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
