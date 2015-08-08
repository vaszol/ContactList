package ru.vaszol.contactlist.contact;

import android.app.Application;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import ru.vaszol.contactlist.contact.db.ContactAppDBHelper;
import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.ContactManager;

/**
 * Created by vas on 08.08.2015.
 */
public class ConatactApp extends Application {
    private ContactManager contactManager = null;
private ContactAppDBHelper dbHekper = null;

    public ConatactApp() {
        super();
        try {
            dbHekper = new ContactAppDBHelper(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContactManager getContactManager() {
        if(contactManager==null){
            contactManager = new ContactManager();
//            contactManager.getContacts().add(new Contact(1,"Ivan","Ivanov","ivan@ivanov.ru"));
//            contactManager.getContacts().add(new Contact(1,"Petr","Petrov","petr@petrov.com"));
//            contactManager.getContacts().add(new Contact(1,"Alex","Sidorov","alex@sidorov.org"));
            try {
                Dao<Contact,Integer> contactDao = DaoManager.createDao(dbHekper.getConnectionSource(),Contact.class);
                contactManager.setContactDao(contactDao);
                if(contactDao.countOf()==0){
                    contactDao.create(new Contact(1,"Ivan","Ivanov","ivan@ivanov.ru"));
                    contactDao.create(new Contact(1,"Petr","Petrov","petr@petrov.com"));
                    contactDao.create(new Contact(1,"Alex","Sidorov","alex@sidorov.org"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return contactManager;
    }
}
