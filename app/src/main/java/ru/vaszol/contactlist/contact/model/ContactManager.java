package ru.vaszol.contactlist.contact.model;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vas on 08.08.2015.
 */
public class ContactManager {
//    private List<Contact> contacts=null;
    private Dao<Contact,Integer> contactDao = null;

    public void setContactDao(Dao<Contact, Integer> contactDao) {
        this.contactDao = contactDao;
    }

    public ContactManager() {
//        contacts=new ArrayList<Contact>();
        super();
    }

    public List<Contact> getContacts() throws SQLException {
            return contactDao.queryForAll();

    }

    public void removeAtID(int id) throws SQLException {
//        List<Contact> contacts = getContacts();
//        Contact result = null;
//        for(Contact contact:contacts){
//            if(contact.getId()==id)
//                result=contact;
//        }
//        contacts.remove(result);
        contactDao.deleteById(id);
    }

    public Contact findById(int id) throws SQLException {
//        List<Contact> contacts = getContacts();
//        Contact result = null;
//        for(Contact contact:contacts){
//            if(contact.getId()==id)
//                result=contact;
//        }
        return contactDao.queryForId(id);
    }
}
