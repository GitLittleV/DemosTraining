package com.example.a11070564.apidemotraining.Activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.example.a11070564.apidemotraining.Adapter.ContactListAdapter;
import com.example.a11070564.apidemotraining.R;

/**
 * Created by 11070564 on 2017/10/9.
 */

public class AutoCompleteDemo extends Activity {
    public static final String TAG="myAutoCompleteDemo";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_1);

        ContentResolver content = getContentResolver();
        Cursor cursor = content.query(ContactsContract.Contacts.CONTENT_URI,
                CONTACT_PROJECTION, null, null, null);
        Log.d(TAG, "onCreate: ");
        ContactListAdapter adapter = new ContactListAdapter(this, cursor);

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.edit);
        textView.setAdapter(adapter);
    }

    public static final String[] CONTACT_PROJECTION = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
}