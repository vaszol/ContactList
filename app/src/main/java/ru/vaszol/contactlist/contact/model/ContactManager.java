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
}
