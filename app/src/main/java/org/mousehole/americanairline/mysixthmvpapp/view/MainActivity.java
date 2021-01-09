package org.mousehole.americanairline.mysixthmvpapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ListView;

import org.mousehole.americanairline.mysixthmvpapp.R;
import org.mousehole.americanairline.mysixthmvpapp.model.Shoe;
import org.mousehole.americanairline.mysixthmvpapp.presenter.ShoeAdapter;
import org.mousehole.americanairline.mysixthmvpapp.presenter.ShoePresenter;
import org.mousehole.americanairline.mysixthmvpapp.presenter.ShoeShopPresenterContract.*;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ShoeShopView {

    public static final String SHOE_DATA = "shoe_data";
    private ShoeShopPresenter shoePresenter;
    private ListView shoeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoePresenter = new ShoePresenter(this);

        shoeListView = findViewById(R.id.shoe_listview);

        shoePresenter.populateIfEmpty();

        shoePresenter.getAllShoes();
    }

    @Override
    public void displayShoes(List<Shoe> shoeList) {
        try {
            ShoeAdapter shoeAdapter = new ShoeAdapter(shoeList);
            shoeListView.setAdapter(shoeAdapter);
        } catch (Exception e) {
            Log.e("TAG_X", e.getMessage(), e);
        }

    }

    @Override
    public void displayError(String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_AppCompat))
                        .setTitle("Database Error")
                        .setMessage(errorMessage)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public void successMessage(String successMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_AppCompat))
                        .setTitle("Database Success!")
                        .setMessage(successMessage)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

}