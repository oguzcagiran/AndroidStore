package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText nameEdittext;
    private EditText surnameEdittext;
    private EditText emailEdittext;
    private EditText usernameEdittext;
    private EditText passwordEdittext;
    private EditText againPasswordEdittext;
    private EditText birthDateEdittext;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setIcon(R.drawable.useradd);
        getSupportActionBar().setTitle(" Sign up");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_register);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        nameEdittext = (EditText)findViewById(R.id.nameEditText);
        surnameEdittext = (EditText)findViewById(R.id.surnameEditText);
        emailEdittext = (EditText)findViewById(R.id.emailEditText);
        usernameEdittext = (EditText)findViewById(R.id.usernameEditText);
        passwordEdittext = (EditText)findViewById(R.id.passwordEditText);
        againPasswordEdittext = (EditText)findViewById(R.id.againPasswordEditText);
        birthDateEdittext = (EditText)findViewById(R.id.birthDateEditText);
        registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String name = nameEdittext.getText().toString();
                String surname = surnameEdittext.getText().toString();
                String email = emailEdittext.getText().toString();
                String username = usernameEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();
                String againPassword = againPasswordEdittext.getText().toString();
                String birthDate = birthDateEdittext.getText().toString();

                try{

                   checkNameEmpty(name);
                   checkSurnameEmpty(surname);
                   checkEmail(email);
                   checkUsernameEmpty(username);
                   checkPassword(password, againPassword);
                   checkEmailLenght(password);
                   checkBirthDate(birthDate);

                   Customer customer = new Customer(name, surname, email, username, password, birthDate);
                   saveCustomer(customer);

                   Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                   intent.putExtra("name", name);
                   intent.putExtra("surname", surname);
                   intent.putExtra("username", username);

                   startActivity(intent);

                }catch (RuntimeException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkPassword(String password, String againPassword) {

        if(!password.equals(againPassword)) {
            throw new RuntimeException("passwords are not same");
        }

    }

    private void checkUsernameEmpty(String username){
        if(username.isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }
    }

    private void checkNameEmpty(String name) {
        if(name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }
    }

    private void checkSurnameEmpty(String surname) {
        if(surname.isEmpty()) {
            throw new RuntimeException("Surname cannot be empty");
        }
    }

    private void checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()) {
            throw new RuntimeException("Invalid Email");
        }
    }

    private void checkBirthDate(String date) {
        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new RuntimeException("Invalid Date");
        }
    }

    private void checkEmailLenght(String password) {
        if(!(password.length() >= 8)) {
            throw new RuntimeException("Password must be greater than 8 character");
        }
    }

    private void saveCustomer(Customer customer){

        CustomerDatabase customerDatabase= new CustomerDatabase(this);
        SQLiteDatabase db = customerDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();

        try {

            cv.put("name", customer.getName());
            cv.put("surname", customer.getSurname());
            cv.put("email", customer.getEmail());
            cv.put("username", customer.getUsername());
            cv.put("mpassword", customer.getPassword());
            cv.put("birthdate", customer.getBirthDate());

            db.insertOrThrow("Customer", null, cv);
        } catch (android.database.SQLException e) {
            throw new RuntimeException("Email and username must be unique");
        }
    }
}