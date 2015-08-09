package ru.vaszol.contactlist;

import android.content.Intent;
import android.graphics.Color;
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

public class Main2Activity extends AppCompatActivity {

    private int id;
    private EditText name, lastName, email;
    private Button btnOk, btnCancel;
    private ImageView image;

//    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = (EditText) findViewById(R.id.edit_name);
        lastName = (EditText) findViewById(R.id.edit_last_name);
        email = (EditText) findViewById(R.id.edit_email);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel= (Button) findViewById(R.id.btn_cancel);
        image= (ImageView) findViewById(R.id.edit_image);

        id=getIntent().getIntExtra("id", -1);

        //если id известна, то выводим данные по контакту
        if(id != -1){
            Contact contact = ((ConatactApp)getApplication()).getContactManager().findById(id);

            name.setText(""+ contact.getName());
            lastName.setText("" + contact.getLastName());
            email.setText("" + contact.getEmail());
        }

        name.setTextColor(Color.GRAY);
        lastName.setTextColor(Color.GRAY);
        email.setTextColor(Color.GRAY);

//        final SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOk(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickClose();
            }
        });
    }
    public void onClickOk(View view){
        Intent intent = new Intent();
//        intent.putExtra("id", position);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("lastName", lastName.getText().toString());
        intent.putExtra("email", email.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


    public void onClickClose(){
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
