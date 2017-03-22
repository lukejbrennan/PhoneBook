package com.example.luke.phonebook;

/**
 * Created by Luke on 3/20/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends BaseAdapter{

    private Context mContext;
    private List<Contact> mListPhoneBook;

    public ContactAdapter(Context context, List<Contact> list){
        mContext = context;
        mListPhoneBook = list;
    }

    @Override
    public int getCount(){
        return mListPhoneBook.size();
    }
    @Override
    public Object getItem(int arg0){
        return mListPhoneBook.get(arg0);
    }

    @Override
    public long getItemId(int arg0){
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2){
        Contact entry = mListPhoneBook.get(arg0);

        if(arg1 == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            arg1 = inflater.inflate(R.layout.contact_row, null);
        }

        ImageView ivAvatar = (ImageView)arg1.findViewById(R.id.imgAvatar);
        Picasso.with(mContext)
                .load(entry.getmSmallImageURL())
                .placeholder(mContext.getDrawable(R.drawable.droidbug))
                .into(ivAvatar);

        TextView tvName = (TextView)arg1.findViewById(R.id.tvName);
        tvName.setText(entry.getmName());

        TextView tvPhone = (TextView)arg1.findViewById(R.id.tvPhone);
        tvPhone.setText(entry.getmWork());



        return arg1;
    }


}
