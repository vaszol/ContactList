package ru.vaszol.contactlist;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.spec.db.TestDbHelper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Created by vas on 09.08.2015.
 */
public class ContactDaoSpec {
    static Dao<Contact,Integer> contactDao = null;
    static ConnectionSource connectionSource =null;

    @BeforeClass
    public static void setUpDatabaseLayer() throws SQLException{
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, Contact.class);

        try{
            contactDao = DaoManager.createDao(connectionSource, Contact.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Before
    public void clearPupils(){
        Contact contact1 = new Contact(1,"Jack","Pot","jack@pot.ru");
        Contact contact2 = new Contact(2,"John","Kettle","john@kettle.ru");
        Contact read_contact1=null, read_contact2=null;
        try {
            contactDao.create(contact1);
            contactDao.create(contact2);
            read_contact1 = contactDao.queryForId(1);
            read_contact2 = contactDao.queryForId(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("Jack",read_contact1.getName());
        assertEquals("John",read_contact2.getName());
    }

}
