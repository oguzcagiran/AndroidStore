package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private ListView commentList;
    private EditText commentEmailEditText;
    private EditText commentEdittext;
    private Button sendCommentButton;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        getSupportActionBar().setIcon(R.drawable.comment);
        getSupportActionBar().setTitle(" Comment");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        commentList = (ListView) findViewById(R.id.commentList);
        commentEmailEditText = (EditText) findViewById(R.id.emailEditTextComment);
        commentEdittext = (EditText) findViewById(R.id.commentEdittext);
        sendCommentButton = (Button) findViewById(R.id.commentConfirmButton);


        try {
            updateCommentList();
        }catch (Exception e){
            e.printStackTrace();
        }


        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    String email = commentEmailEditText.getText().toString();
                    String commentString = commentEdittext.getText().toString();

                    Comment comment = new Comment(commentString, email);

                    saveComment(comment);

                    updateCommentList();

                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void updateCommentList() {

        List<String> commentEmailList = getComments();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commentEmailList);
        commentList.setAdapter(arrayAdapter);

    }

    private void saveComment(Comment comment) {

            CustomerDatabase commentDatabase = new CustomerDatabase(this);
            SQLiteDatabase db = commentDatabase.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("email", comment.getOwnerEmail());
            cv.put("comment", comment.getCommentString());

            db.insertOrThrow("CustomerComment", null, cv);

    }

    private List<String> getComments() {

        ArrayList<String> commentList = new ArrayList<>();


            CustomerDatabase commentDatabase = new CustomerDatabase(this);
            SQLiteDatabase db = commentDatabase.getReadableDatabase();

            Cursor cursor = db.query("CustomerComment", new String[]{"email", "comment"}, null, null, null, null, null, null);

            String email = " ";
            String commentString = " ";

            while (cursor.moveToNext()) {

                email = cursor.getString(cursor.getColumnIndex("email"));
                commentString = cursor.getString(cursor.getColumnIndex("comment"));

                commentList.add(email + " : \n" + commentString);

            }

        return commentList;

    }

}
