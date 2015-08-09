package ru.vaszol.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ru.vaszol.contactlist.contact.ConatactApp;
import ru.vaszol.contactlist.contact.ContactAdapter;
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
        contactAdapter=new ContactAdapter(getApplicationContext(),contactManager.getContacts());
        contactLV.setAdapter(contactAdapter);

//        contactAdapter.notifyDataSetChanged();//информарует listView об изменении (abserver)
        contactLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, RequestCode.REQUEST_CODE_EDIT);
            }
        });
    }

    public void addData(){

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
                    Toast.makeText(this,"Add! "+name, Toast.LENGTH_SHORT).show();

                    break;
                case RequestCode.REQUEST_CODE_EDIT:
                     name = data.getStringExtra("name");
                     lastName = data.getStringExtra("lastName");
                     email = data.getStringExtra("email");
                    Toast.makeText(this,"Edit! "+name, Toast.LENGTH_SHORT).show();
                    break;
            }
                    contactAdapter.notifyDataSetChanged();//информарует listView об изменении (abserver)
        }else {
            Toast.makeText(this,"Cancel changes", Toast.LENGTH_SHORT).show();
        }
    }
}
