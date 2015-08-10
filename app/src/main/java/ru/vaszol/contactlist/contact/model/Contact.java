package ru.vaszol.contactlist.contact.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by vas on 08.08.2015.
 */
@DatabaseTable(tableName = "Contact")
public class Contact {
    @DatabaseField( generatedId = true) //id = true, canBeNull = false,
    private Integer id;
    @DatabaseField(columnName = "name", canBeNull = false, index = true, indexName = "name_index")
    private String name;
    @DatabaseField(columnName = "lastName", canBeNull = false, index = true, indexName = "lastName_index")
    private String lastName;
    @DatabaseField(columnName = "email", canBeNull = false, index = true, indexName = "email_index")
    private String email;

    public Contact() {
        super();
    }

    public Contact(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    //    public Contact(Integer id, String name, String lastName, String email) {
//        this.id = id;
//        this.name = name;
//        this.lastName = lastName;
//        this.email = email;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
