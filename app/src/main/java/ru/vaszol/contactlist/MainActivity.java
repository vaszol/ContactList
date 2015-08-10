package ru.vaszol.contactlist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

import ru.vaszol.contactlist.contact.ConatactApp;
import ru.vaszol.contactlist.contact.ContactAdapter;
import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.contact.model.ContactManager;
import ru.vaszol.contactlist.db.DataBase;
import ru.vaszol.contactlist.util.RequestCode;

public class MainActivity extends ActionBarActivity {

    private ListView contactLV=null;
    private ContactManager contactManager=null;
    private ContactAdapter contactAdapter=null;

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        dataBase = new DataBase(this);

        contactLV = (ListView) findViewById(R.id.listView);
        contactManager=((ConatactApp)getApplication()).getContactManager();
        try {
            contactAdapter=new ContactAdapter(getApplicationContext(),contactManager.getContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contactLV.setAdapter(contactAdapter);

//        contactAdapter.notifyDataSetChanged();//информарует listView об изменении (abserver)
        contactLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    contactManager.removeAtID((int) id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                contactAdapter.notifyDataSetChanged();
                return true;
            }
        });

        /**
         * вызываем редактор контакта
         */
        contactLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("id", position);
                startActivityForResult(intent, RequestCode.REQUEST_CODE_EDIT);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * вызываем добавление контакта
     */
    public void addItem(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivityForResult(intent, RequestCode.REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            String name;
            String lastName;
            String email;

            switch (requestCode){
                case RequestCode.REQUEST_CODE_ADD:

                     name = data.getStringExtra("name");
                     lastName = data.getStringExtra("lastName");
                     email = data.getStringExtra("email");
                    //с этой id исеются проблемы известно какие
//                    contactManager.getContacts().add(new Contact(3, name, lastName, email));
//                    addData(name,lastName,email);
                    Toast.makeText(this,"Add! "+name, Toast.LENGTH_SHORT).show();

                    break;
                case RequestCode.REQUEST_CODE_EDIT:
                     name = data.getStringExtra("name");
                     lastName = data.getStringExtra("lastName");
                     email = data.getStringExtra("email");
//                    contactManager.getContacts(). add(new Contact(4,name,lastName,email));
                    Toast.makeText(this,"Edit! "+name, Toast.LENGTH_SHORT).show();
                    break;
            }
                    contactAdapter.notifyDataSetChanged();//информарует listView об изменении (abserver)
        }else {
            Toast.makeText(this,"Cancel changes", Toast.LENGTH_SHORT).show();
        }
    }

//    public void addData(String name, String lastName, String email){
//        boolean isInserted=dataBase.insertData(name,lastName,email);
//        if (isInserted)
//            Toast.makeText(this,"Data Inserted! "+name, Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(this,"Data Not Inserted! "+name, Toast.LENGTH_SHORT).show();
//    }

//    public void viewAll(){
//        Cursor res = dataBase.getAllData();
//        if (res.getCount() ==0){
//            return;
//        }
//        StringBuffer buffer=new StringBuffer();
//        while (res.moveToNext()){
////            buffer.append("Id :"+ res.getString(0)+"\n");
////            buffer.append("name :"+ res.getString(1)+"\n");
////            buffer.append("lastName :"+ res.getString(2)+"\n");
////            buffer.append("email :"+ res.getString(3)+"\n\n");
////            contactManager.getContacts().add(new Contact(
////                    res.getInt(0),
////                    res.getString(1),
////                    res.getString(2),
////                    res.getString(3)
////                    ));
//        }
//
////        contactAdapter.notifyDataSetChanged();//информарует listView об изменении (abserver)
//    }

}
