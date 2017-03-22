package com.example.luke.phonebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.luke.phonebook.utilities.NetworkUtils;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvPhone;
    private List<Contact> listPhoneBook;
    ContactAdapter adapter;
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
        listPhoneBook = new ArrayList<Contact>();
        //Populate phone book with JSON data//
        adapter = new ContactAdapter(this, listPhoneBook);
        lvPhone.setAdapter(adapter);
        new FetchNetworkData().execute("https://s3.amazonaws.com/technical-challenge/Contacts_v2.json");
        adapter.notifyDataSetChanged();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        lvPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact", listPhoneBook.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int position;
        //String name;
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                position = data.getIntExtra("position", 0);
                listPhoneBook.get(position).setmName(data.getStringExtra("editName"));
                listPhoneBook.get(position).setmCompany(data.getStringExtra("editCompany"));
                listPhoneBook.get(position).setmWork(data.getStringExtra("editWork"));
                listPhoneBook.get(position).setmHome(data.getStringExtra("editHome"));
                listPhoneBook.get(position).setmMobile(data.getStringExtra("editMobile"));
                listPhoneBook.get(position).setmStreet(data.getStringExtra("editStreet"));
                listPhoneBook.get(position).setmCity(data.getStringExtra("editCity"));
                listPhoneBook.get(position).setmCountry(data.getStringExtra("editCountry"));
                listPhoneBook.get(position).setmZip(data.getStringExtra("editZip"));
            }
        }

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
            String mobile;
            if(phoneJSON.has("mobile")){
                mobile = phoneJSON.getString("mobile");
            } else {
                mobile = "";
            }
            //Get Address
            JSONObject addrJSON = contactJSON.getJSONObject("address");
            String street = addrJSON.getString("street");
            String city = addrJSON.getString("city");
            String state = addrJSON.getString("state");
            String country = addrJSON.getString("country");
            String zip = addrJSON.getString("zip");
            //... and so on with address...//

            listPhoneBook.add(new Contact(name, company, smallImageUrl, largeImageURL, email, website, work, home, mobile, street, city, state, country, zip));

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
