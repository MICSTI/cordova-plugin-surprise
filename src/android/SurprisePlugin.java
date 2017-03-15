import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.provider.Settings.Secure;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class SurprisePlugin extends CordovaPlugin {
 
public static final String TAG = "SurprisePlugin";
 
/**
* Constructor.
*/
public SurprisePlugin() {}
 
/**
* Sets the context of the Command. This can then be used to do things like
* get file paths associated with the Activity.
*
* @param cordova The context of the main Activity.
* @param webView The CordovaWebView Cordova is running in.
*/

	ArrayList<String> contactList;
    Cursor cursor;
 
 
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.v(TAG,"Init SurprisePlugin");
    }
 
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
         
        // Shows a toast
        Log.v(TAG,"DevID received:"+ action);
        
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {

                /*String s = "Device ID= "+ Secure.getString(cordova.getActivity().getApplicationContext().getContentResolver(), Secure.ANDROID_ID);

                Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action +" from Dev:" + s, Toast.LENGTH_LONG);
                toast.show();*/
				
				Toast.makeText(cordova.getActivity().getApplicationContext(), "fetching contact", Toast.LENGTH_SHORT).show();
				
				getContacts();
				
				int size = contactList.size();
				
				Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), contactList.get(3), Toast.LENGTH_LONG);
                toast.show();
				
            }
        });
 
        return true;
    }
	
	public void getContacts() {
        contactList = new ArrayList<String>();
        String phoneNumber = null;
        String email = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
        StringBuffer output;
        ContentResolver contentResolver = cordova.getActivity().getApplicationContext().getContentResolver();
        cursor = contentResolver.query(CONTENT_URI, null,null, null, null);
        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
                
                // Add the contact to the ArrayList
                contactList.add(name);
            }
        }
    }
}
