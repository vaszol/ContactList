package ru.vaszol.contactlist.contact;

import android.app.Application;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.ContactManager;
import ru.vaszol.contactlist.db.DBHelperORM;
import ru.vaszol.contactlist.db.DataBase;

/**
 * Created by vas on 08.08.2015.
 */
public class ConatactApp extends Application {
    private ContactManager contactManager = null;
    private DBHelperORM dbHelperORM =null;
    public ConatactApp() {
        super();
        try {
            dbHelperORM=new DBHelperORM(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContactManager getContactManager() {
        if(contactManager==null){
            contactManager = new ContactManager();

            try {
                Dao<Contact,Integer> contactDao = DaoManager.createDao(dbHelperORM.getConnectionSource(),Contact.class);
                contactManager.setContactDao(contactDao);

                if(contactDao.countOf()==0){
                    contactDao.create(new Contact(0,"Ivan","Ivanov","ivan@ivanov.ru"));
                    contactDao.create(new Contact(1,"Petr","Petrov","petr@petrov.com"));
                    contactDao.create(new Contact(2,"Alex","Sidorov","alex@sidorov.org"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            DataBase dataBase = new DataBase(this);

//            contactManager.getContacts().add(new Contact(0,"Ivan","Ivanov","ivan@ivanov.ru"));
//            contactManager.getContacts().add(new Contact(1,"Petr","Petrov","petr@petrov.com"));
//            contactManager.getContacts().add(new Contact(2,"Alex","Sidorov","alex@sidorov.org"));

//            boolean isInserted=dataBase.insertData("Ivan","Ivanov","ivan@ivanov.ru");
//             isInserted=dataBase.insertData("Petr","Petrov","petr@petrov.com");
//             isInserted=dataBase.insertData("Alex","Sidorov","alex@sidorov.org");
//            if (isInserted)
//                Toast.makeText(this, "Data Inserted! ", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this,"Data Not Inserted! ", Toast.LENGTH_SHORT).show();
        }
        return contactManager;
    }
}
