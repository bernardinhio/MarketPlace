package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ProductModel> arrayListProducts = ProductDataProvider.Companion.getArrayListProducts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setupRecyclerView();
        setAdapter();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter(){
        AdapterRV adapterRV = new AdapterRV(arrayListProducts);
        recyclerView.setAdapter(adapterRV);
        adapterRV.notifyDataSetChanged();
    }
}
