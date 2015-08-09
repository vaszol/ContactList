package ru.vaszol.contactlist.contact.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vas on 08.08.2015.
 */
public class ContactManager {
    private List<Contact> contacts=null;

    public ContactManager() {
        contacts=new ArrayList<Contact>();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void removeAtID(int id){
        List<Contact> contacts = getContacts();
        Contact result = null;
        for(Contact contact:contacts){
            if(contact.getId()==id)
                result=contact;
        }
        contacts.remove(result);
    }

    public Contact findById(int id) {
        List<Contact> contacts = getContacts();
        Contact result = null;
        for(Contact contact:contacts){
            if(contact.getId()==id)
                result=contact;
        }
        return result;
    }
}
