package com.example.luke.phonebook.utilities;

/**
 * Created by Luke on 3/21/2017.
 */
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



public class NetworkUtils {
    public static URL buildUrl(String contactsURL){
        Uri builtUri = Uri.parse(contactsURL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(contactsURL);
        //    url = new URL(builtUri.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    } // end of function buildUrl
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) return scanner.next();
            else return null;
        }finally {
            urlConnection.disconnect();
        }
    }   // end of function
}
