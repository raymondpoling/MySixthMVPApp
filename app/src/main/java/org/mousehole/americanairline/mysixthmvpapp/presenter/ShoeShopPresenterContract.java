package org.mousehole.americanairline.mysixthmvpapp.presenter;

import android.content.Context;

import org.mousehole.americanairline.mysixthmvpapp.model.Shoe;

import java.util.List;

public interface ShoeShopPresenterContract {
    interface ShoeShopPresenter {
        void getAllShoes();
        void insertShoe(Shoe newShoe);
        void deleteShow(Shoe deleteShoe);
        void populateIfEmpty();
    }
    interface ShoeShopView {
        void displayShoes(List<Shoe> shoeList);
        void displayError(String errorMessage);
        void successMessage(String successMessage);
        Context getContext();
    }
}
