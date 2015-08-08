package ru.vaszol.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ru.vaszol.contactlist.contact.ConatactApp;
import ru.vaszol.contactlist.contact.model.Contact;

public class Main2Activity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        position=getIntent().getIntExtra("position", 0);

        TextView name = (TextView) findViewById(R.id.edit_name);
        TextView lastName = (TextView) findViewById(R.id.edit_last_name);
        TextView email = (TextView) findViewById(R.id.edit_email);
        ImageView image = (ImageView) findViewById(R.id.edit_image);

        Contact contact = ((ConatactApp)getApplication()).getContactManager().findByPosition(position);

        name.setText(""+ contact.getName());
        lastName.setText(""+ contact.getLastName());
        email.setText(""+ contact.getEmail());
    }

    public void onClickClose(){
//        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
//        startActivity(intent);
        finish(); //возвращает в предыдущую активность по стеку
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
