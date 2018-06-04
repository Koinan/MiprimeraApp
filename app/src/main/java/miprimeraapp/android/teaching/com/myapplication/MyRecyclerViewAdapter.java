package miprimeraapp.android.teaching.com.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import miprimeraapp.android.teaching.com.myapplication.View.MyViewHolder;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    private String[] dataSet;

    public MyRecyclerViewAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }
    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.length : 0 ;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder (
            @NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bind(dataSet[position]);
    }
}
