package org.mousehole.americanairline.mysixthmvpapp.presenter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.mousehole.americanairline.mysixthmvpapp.R;
import org.mousehole.americanairline.mysixthmvpapp.model.Shoe;

import java.util.List;

public class ShoeAdapter extends BaseAdapter {

    final List<Shoe> shoes;

    public ShoeAdapter(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    @Override
    public int getCount() {
        return shoes.size();
    }

    @Override
    public Shoe getItem(int i) {
        return shoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return shoes.get(i).getShoeId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("TAG_X", "Making view: " + i);
        Shoe shoe = shoes.get(i);

        View mainView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shoe_item_view,
                        viewGroup,
                        false);

        TextView model = mainView.findViewById(R.id.shoe_model_textview);
        TextView price = mainView.findViewById(R.id.shoe_price_textview);
        TextView size = mainView.findViewById(R.id.shoe_size_textview);

        model.setText(shoe.getShoeModel());
        price.setText(String.format("$%.2f",shoe.getShoePrice()));
        size.setText("Size "+shoe.getShoeSize());

        Log.d("TAG_X", "Produced model " + shoe.toString());

        return mainView;
    }
}
