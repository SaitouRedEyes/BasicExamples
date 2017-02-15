package com.cesar.nave.androidsamples;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class ArrayAdapterItem extends ArrayAdapter<String>
{

    private Context c;
    private int layoutResourceID;
    private String[] androidContents = null;

    ArrayAdapterItem(Context c, int layoutResourceID, String[] androidContents)
    {
        super(c, layoutResourceID, androidContents);

        this.c = c;
        this.layoutResourceID = layoutResourceID;
        this.androidContents = androidContents;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        //If convertView isn't null, the content of the list will not inflate again, only update.
        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity) this.c).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceID, parent, false);
        }

        TextView textViewItem = (TextView) convertView.findViewById(R.id.activityMainTextViewItem);
        textViewItem.setText(this.androidContents[pos]);

        return convertView;
    }
}