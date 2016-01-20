package com.example.oguzc.projectshop;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by oguzc on 12/17/2015.
 */
public class BookListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Book> bookList;

    public BookListAdapter(Activity activity, List<Book> bookList) {

        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookList = bookList;

    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Book getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineView;

        lineView = mInflater.inflate(R.layout.list_book_item, null);

        final ImageView bookImageView = (ImageView)lineView.findViewById(R.id.bookImageView);
        TextView bookNameView = (TextView) lineView.findViewById(R.id.bookName);
        TextView bookTypeView = (TextView) lineView.findViewById(R.id.bookType);
        TextView bookWriterView = (TextView) lineView.findViewById(R.id.writer);
        TextView bookPublisherView = (TextView) lineView.findViewById(R.id.publisher);
        TextView bookPageView = (TextView) lineView.findViewById(R.id.page);
        Button addButton = (Button)lineView.findViewById(R.id.addButton);

        final Book book = bookList.get(position);
        int id = bookImageView.getContext().getResources().getIdentifier(book.getId(), "drawable", bookImageView.getContext().getPackageName());

        if(id != 0) {
            bookImageView.setImageResource(id);
        }

        bookNameView.setText(book.getName());
        bookTypeView.setText(book.getType());
        bookWriterView.setText(book.getWriter());
        bookPublisherView.setText(book.getPublisher());
        bookPageView.setText(book.getPage());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.cartItemId.add(book.getId());
                Toast.makeText(bookImageView.getContext(), "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        return lineView;
    }

}