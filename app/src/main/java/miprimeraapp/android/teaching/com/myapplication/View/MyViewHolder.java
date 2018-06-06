package miprimeraapp.android.teaching.com.myapplication.View;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import miprimeraapp.android.teaching.com.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView myTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        myTextView = itemView
                .findViewById(R.id.text_view_view_holder);

    }

    public void bind(String value) {
        myTextView.setText(value);
        myTextView.setBackgroundColor(Color.parseColor(value));
        myTextView.setHeight(new Random().nextInt(500) + 200);
    }
}

