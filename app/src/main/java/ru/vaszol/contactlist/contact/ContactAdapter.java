package ru.vaszol.contactlist.contact;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.vaszol.contactlist.R;
import ru.vaszol.contactlist.contact.model.*;

/**
 * Created by vas on 08.08.2015.
 */
public class ContactAdapter extends BaseAdapter {

    private List<Contact> contacts = null;
    private LayoutInflater inflator = null;

    public ContactAdapter(Context context, List<Contact> contacts) {
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        if (contacts != null) {
            return contacts.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (contacts != null&&position>=0&&position<=getCount()) {
            return contacts.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (contacts != null&&position>=0&&position<=getCount()) {
            return contacts.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        
        if (view==null){
            view=inflator.inflate(R.layout.contact_item,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            viewHolder.fullName = (TextView) view.findViewById(R.id.textView);
            viewHolder.email = (TextView) view.findViewById(R.id.textView2);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        Contact record = contacts.get(position);

//        viewHolder.image.setImageResource(record.getEmail());
        viewHolder.image.setImageResource(R.drawable.account_circle);
        viewHolder.fullName.setText(""+record.getName()+" "+record.getLastName());
        viewHolder.email.setText(""+record.getEmail());

        viewHolder.fullName.setTextColor(Color.GRAY);
        viewHolder.email.setTextColor(Color.GRAY);
        return view;
    }

    private class ViewHolder {
        public ImageView image;
        public TextView fullName;
        public TextView email;
    }
}