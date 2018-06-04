package miprimeraapp.android.teaching.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView myRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        myRecyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager myLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL );
        myRecyclerView.setLayoutManager(myLayoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getResources().getStringArray(R.array.colors));
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }
}
