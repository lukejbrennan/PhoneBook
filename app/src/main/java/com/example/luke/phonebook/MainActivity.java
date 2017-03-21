package com.example.luke.phonebook;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.luke.phonebook.utilities.NetworkUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvPhone;
    private List<PhoneBook> listPhoneBook;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPhone = (ListView) findViewById(R.id.listPhone);
        listPhoneBook = new ArrayList<PhoneBook>();
        //Populate phone book with JSON data//
        listPhoneBook.add(new PhoneBook("Luke", "784", "lbrenna4"));
        listPhoneBook.add(new PhoneBook("Pat", "784", "patttyyyy"));
        PhoneBookAdapter adapter = new PhoneBookAdapter(this, listPhoneBook);
        lvPhone.setAdapter(adapter);
        new FetchNetworkData().execute("https://s3.amazonaws.com/technical-challenge/Contacts_v2.json");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public class FetchNetworkData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            if (params.length == 0) return null;
            URL url = NetworkUtils.buildUrl(params[0]);
            String responseString = null;
            try {
                responseString = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            listPhoneBook.add(new PhoneBook("Drew", "84", "heyhey"));
            return responseString;
        }

        @Override
        protected void onPostExecute(String responseData) {
            //set all of the contact information
            listPhoneBook.add(new PhoneBook(responseData, "8", "hahah"));
            try {
                listPhoneBook.add(new PhoneBook("Sarah", "84", "srah"));
                JSONArray ContactData = new JSONArray(responseData);
                for (int i = 0; i < ContactData.length(); i++) {
                    JSONObject contactJSON = ContactData.getJSONObject(i);
                    String name = contactJSON.getString("name");
                    //String company = contactJSON.getString("company");
                    //boolean favorite = contactJSON.getBoolean("favorite");
                    //"smallImageURL"//
                    //"largeImageURL"//
                    String email = contactJSON.getString("email");
                    String website = contactJSON.getString("website");
                    //Get Phone
                    JSONObject phoneJSON = contactJSON.getJSONObject("phone");
                    //String work = phoneJSON.getString("work");
                    //String home = phoneJSON.getString("home");
                    String mobile = phoneJSON.getString("mobile");
                    //Get Address
                    JSONObject addrJSON = contactJSON.getJSONObject("address");
                    String street = addrJSON.getString("street");
                    //... and so on with address...//

                    listPhoneBook.add(new PhoneBook(name, mobile, email));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
