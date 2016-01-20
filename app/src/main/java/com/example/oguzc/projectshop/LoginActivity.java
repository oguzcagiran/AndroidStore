package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdittext;
    private EditText passwordEdittext;
    private Button loginButton;
    private TextView registerTextView;
    private Button commentButton;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        usernameEdittext = (EditText) findViewById(R.id.userNameEditText);
        passwordEdittext = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerTextView = (TextView) findViewById(R.id.registerTextView);
        commentButton = (Button) findViewById(R.id.commentButton);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                LoginActivity.this.startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();


                try {

                    String[] informationCustomer = getCustomerInformationFromDatabase(username);
                    String passwordRecorded = informationCustomer[0];
                    String name = informationCustomer[1];
                    String surname = informationCustomer[2];
                    String email = informationCustomer[3];

                    checkPassword(password, passwordRecorded);
                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    intent.putExtra("username", username);

                    startActivity(intent);

                } catch (RuntimeException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void checkPassword(String password, String recordedPassword) {

        if (!password.equals(recordedPassword)) {
            throw new RuntimeException("Username or Password false");
        }

    }

    private String[] getCustomerInformationFromDatabase(String username) {

        try {

            String[] information = new String[4];
            CustomerDatabase customerDatabase = new CustomerDatabase(this);
            SQLiteDatabase db = customerDatabase.getReadableDatabase();

            Cursor cursor = db.query("Customer", new String[]{"mpassword", "name", "surname", "email"}, "username=?", new String[]{username}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                information[0] = cursor.getString(cursor.getColumnIndex("mpassword"));
                information[1] = cursor.getString(cursor.getColumnIndex("name"));
                information[2] = cursor.getString(cursor.getColumnIndex("surname"));
                information[3] = cursor.getString(cursor.getColumnIndex("email"));
            }

            return information;

        } catch (Exception e) {
            throw new RuntimeException("Username or password false");

        }

    }

}