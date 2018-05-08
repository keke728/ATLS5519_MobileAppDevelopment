package com.android.keke.bookfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookAdapter extends ArrayAdapter<Book> {

  public BookAdapter(Context context, int resource){
      super(context, resource);
  }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        Book book = getItem(position);

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView title = view.findViewById(R.id.title);
        TextView author = view.findViewById(R.id.author);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());

        return view;
        }
    }

