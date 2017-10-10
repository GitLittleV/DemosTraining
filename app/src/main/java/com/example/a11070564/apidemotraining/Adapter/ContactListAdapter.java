package com.example.a11070564.apidemotraining.Adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.a11070564.apidemotraining.Activity.AutoCompleteDemo.TAG;

/**
 * Created by 11070564 on 2017/10/9.
 */

public class ContactListAdapter  extends CursorAdapter implements Filterable{

    private ContentResolver mContent;

    public ContactListAdapter(Context context, Cursor cursor){
        super(context,cursor);
        mContent=context.getContentResolver();
    }
public static final int COLUMN_DISPLAY_NAME=1;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final TextView view = (TextView) inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        view.setText(cursor.getString(COLUMN_DISPLAY_NAME));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView)view).setText(cursor.getString(COLUMN_DISPLAY_NAME));
    }

 @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor.getString(COLUMN_DISPLAY_NAME);
    }

    public static final String[] CONTACT_PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        FilterQueryProvider filter=getFilterQueryProvider();
//        Log.d(TAG, "filter?: "+filter);
//        if (filter!=null){
//            Log.d(TAG, "runQueryOnBackgroundThread:filter="+filter.runQuery(constraint));
//            return filter.runQuery(constraint);
//
//    }
        Uri uri=Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,
                Uri.decode(constraint.toString()));

        return mContent.query(uri,CONTACT_PROJECTION,null,null,null);

    }


}
