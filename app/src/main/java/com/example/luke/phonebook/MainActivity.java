package com.example.luke.phonebook;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.luke.phonebook.utilities.NetworkUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvPhone;
    private List<PhoneBook> listPhoneBook;
    PhoneBookAdapter adapter;
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
        adapter = new PhoneBookAdapter(this, listPhoneBook);
        lvPhone.setAdapter(adapter);
        new FetchNetworkData().execute("https://s3.amazonaws.com/technical-challenge/Contacts_v2.json");
        adapter.notifyDataSetChanged();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }
    protected void populateList(JSONArray contactArray) throws JSONException {
        for (int i = 0; i < contactArray.length(); i++) {
            JSONObject contactJSON = contactArray.getJSONObject(i);
            String name = contactJSON.getString("name");
            String company = contactJSON.getString("company");
            //boolean favorite = contactJSON.getBoolean("favorite");
            String smallImageUrl = contactJSON.getString("smallImageURL");
            String largeImageURL = contactJSON.getString("largeImageURL");
            String email = contactJSON.getString("email");
            String website = contactJSON.getString("website");

            //Get Phone
            JSONObject phoneJSON = contactJSON.getJSONObject("phone");
            String work = phoneJSON.getString("work");
            String home = phoneJSON.getString("home");
            String mobile = phoneJSON.getString("mobile");
            //Get Address
            JSONObject addrJSON = contactJSON.getJSONObject("address");
            String street = addrJSON.getString("street");
            String city = addrJSON.getString("city");
            String state = addrJSON.getString("state");
            String country = addrJSON.getString("country");
            String zip = addrJSON.getString("zip");
            //... and so on with address...//
            listPhoneBook.add(new PhoneBook(name, company, smallImageUrl, largeImageURL, email, website, work, home, mobile, street, city, state, country, zip));
/*
            ImageView imageView = (ImageView) findViewById(R.id.imgAvatar);

            Picasso.with(this)
                    .load(smallImageUrl)
                    .into(imageView);
*/
        }

        adapter.notifyDataSetChanged();
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
            return responseString;
        }

        @Override
        protected void onPostExecute(String responseData) {
            //set all of the contact information
            try {
                JSONArray ContactData = new JSONArray(responseData);
                populateList(ContactData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
