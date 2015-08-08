package ru.vaszol.contactlist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import ru.vaszol.contactlist.contact.ConatactApp;
import ru.vaszol.contactlist.contact.ContactAdapter;
import ru.vaszol.contactlist.contact.model.ContactManager;

public class MainActivity extends Activity {

    private ListView contactLV=null;
    private ContactManager contactManager=null;
    private ContactAdapter contactAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactLV = (ListView) findViewById(R.id.listView);
        contactManager=((ConatactApp)getApplication()).getContactManager();
        contactAdapter=new ContactAdapter(getApplicationContext(),contactManager.getContacts());
        contactLV.setAdapter(contactAdapter);
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
}
