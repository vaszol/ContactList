package ru.vaszol.contactlist.contact;

import android.app.Application;
import android.widget.Toast;

import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.ContactManager;
import ru.vaszol.contactlist.db.DataBase;

/**
 * Created by vas on 08.08.2015.
 */
public class ConatactApp extends Application {
    private ContactManager contactManager = null;

    public ContactManager getContactManager() {
        if(contactManager==null){
            contactManager = new ContactManager();
            DataBase dataBase = new DataBase(this);

            contactManager.getContacts().add(new Contact(1,"Ivan","Ivanov","ivan@ivanov.ru"));
            contactManager.getContacts().add(new Contact(1,"Petr","Petrov","petr@petrov.com"));
            contactManager.getContacts().add(new Contact(1,"Alex","Sidorov","alex@sidorov.org"));
            boolean isInserted=dataBase.insertData("Ivan","Ivanov","ivan@ivanov.ru");
             isInserted=dataBase.insertData("Petr","Petrov","petr@petrov.com");
             isInserted=dataBase.insertData("Alex","Sidorov","alex@sidorov.org");
            if (isInserted)
                Toast.makeText(this, "Data Inserted! ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Data Not Inserted! ", Toast.LENGTH_SHORT).show();
        }
        return contactManager;
    }
}
