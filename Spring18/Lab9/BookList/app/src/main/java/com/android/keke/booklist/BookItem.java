package com.android.keke.booklist;

public class BookItem {

    private String id;
    private String name;
    private String url;

    public BookItem(){
        // Default constructor required for calls to DataSnapshot.getValue(RecipeItem.class)
    }

    public BookItem(String newid, String newName, String newURL){
        id = newid;
        name = newName;
        url = newURL;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    //The string representation of a book item
    public String toString(){
        return this.name;
    }
}
