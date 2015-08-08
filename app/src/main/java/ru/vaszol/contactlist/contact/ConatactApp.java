package ru.vaszol.contactlist.contact;

import android.app.Application;

import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.ContactManager;

/**
 * Created by vas on 08.08.2015.
 */
public class ConatactApp extends Application {
    private ContactManager contactManager = null;

    public ContactManager getContactManager() {
        if(contactManager==null){
            contactManager = new ContactManager();
            contactManager.getContacts().add(new Contact(1,"Ivan","Ivanov","ivan@ivanov.ru"));
            contactManager.getContacts().add(new Contact(1,"Petr","Petrov","petr@petrov.com"));
            contactManager.getContacts().add(new Contact(1,"Alex","Sidorov","alex@sidorov.org"));
        }
        return contactManager;
    }
}
