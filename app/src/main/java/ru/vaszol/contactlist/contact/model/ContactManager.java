package ru.vaszol.contactlist.contact.model;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vas on 08.08.2015.
 */
public class ContactManager {
    private Dao<Contact, Integer> contactDao = null;
//    private List<Contact> contacts=null;

    public void setContactDao(Dao<Contact, Integer> contactDao) {
        this.contactDao = contactDao;
    }

    public ContactManager() {
        super();
//        contacts=new ArrayList<Contact>();
    }

    public List<Contact> getContacts() throws SQLException {
//        return contacts;
        return contactDao.queryForAll();
    }

    public void removeAtId(int id) throws SQLException {
//        List<Contact> contacts =getContacts();
//        Contact for_remove =null;
//        for (Contact contact: contacts){
//            if (contact.getId() == id)
//                for_remove=contact;
//        }
//        contacts.remove(for_remove);
        contactDao.deleteById(id);
    }

    public Contact findById(Integer id) throws SQLException {
//        List<Contact> contacts =getContacts();
//        Contact result =null;
//        for (Contact contact: contacts){
//            if (contact.getId() == id)
//                result=contact;
//        }
//        return result;
        return contactDao.queryForId(id);
    }
}
