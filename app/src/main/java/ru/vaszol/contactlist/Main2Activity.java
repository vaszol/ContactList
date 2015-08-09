package ru.vaszol.contactlist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ru.vaszol.contactlist.contact.ConatactApp;
import ru.vaszol.contactlist.contact.model.Contact;
import ru.vaszol.contactlist.db.DataBase;

public class Main2Activity extends AppCompatActivity {

    private int position;
    private EditText name, lastName, email;
    private Button btnOk, btnCancel;
    private ImageView image;

//    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        dataBase = new DataBase(this);
        name = (EditText) findViewById(R.id.edit_name);
        lastName = (EditText) findViewById(R.id.edit_last_name);
        email = (EditText) findViewById(R.id.edit_email);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel= (Button) findViewById(R.id.btn_cancel);
        image= (ImageView) findViewById(R.id.edit_image);

        position=getIntent().getIntExtra("position", 0);

        Contact contact = ((ConatactApp)getApplication()).getContactManager().findByPosition(position);

        name.setText(""+ contact.getName());
        lastName.setText("" + contact.getLastName());
        email.setText("" + contact.getEmail());

//        final SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ContentValues contentValues = new ContentValues;
//                contentValues.put("name", name.getText().toString());
//                contentValues.put("lastName", lastName.getText().toString());
//                contentValues.put("email", email.getText().toString());

//                sqLiteDatabase.insert("Contact",null,contentValues);
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickClose();
            }
        });
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
