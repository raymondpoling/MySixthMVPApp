package org.mousehole.americanairline.mysixthmvpapp.presenter;

import android.util.Log;

import org.mousehole.americanairline.mysixthmvpapp.model.Shoe;
import org.mousehole.americanairline.mysixthmvpapp.model.db.ShoeDatabaseHelper;

public class ShoePresenter implements ShoeShopPresenterContract.ShoeShopPresenter {

    private final ShoeShopPresenterContract.ShoeShopView shoeShopView;
    private final ShoeDatabaseHelper shoeDatabaseHelper;

    public ShoePresenter(ShoeShopPresenterContract.ShoeShopView shoeShopView) {
        this.shoeShopView = shoeShopView;
        shoeDatabaseHelper = new ShoeDatabaseHelper(shoeShopView.getContext());
    }

    @Override
    public void getAllShoes() {

        //when I get all shoes from the database...
        new Thread() {
            public void run() {
                super.run();
                try {
                    shoeShopView.displayShoes(shoeDatabaseHelper.getAllShoes());
                } catch (Exception e) {
                    Log.e("TAGX_ALL", e.getMessage(), e);
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();
    }

    @Override
    public void insertShoe(Shoe newShoe) {
        new Thread() {
            public void run() {
                super.run();
                try {
                    shoeDatabaseHelper.insertNewShoe(newShoe);
                    shoeShopView.successMessage(newShoe.getShoeModel() + " Inserted!!");
                } catch (Exception e) {
                    Log.e("TAG_X", e.getMessage(), e);
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();
    }

    @Override
    public void deleteShow(Shoe deleteShoe) {
        new Thread() {
            public void run() {
                super.run();
                try {
                    shoeDatabaseHelper.deleteShoeFromDatabase(deleteShoe);
                    shoeShopView.successMessage(deleteShoe.getShoeModel() + " DELETED!!");
                } catch (Exception e) {
                    Log.e("TAG_X", e.getMessage(), e);
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();
    }

    @Override
    public void populateIfEmpty() {
        shoeDatabaseHelper.populateIfEmpty();
    }
}
