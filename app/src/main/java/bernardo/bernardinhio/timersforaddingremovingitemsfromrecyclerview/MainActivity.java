package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ProductModel> arrayListProducts = ProductDataProvider.Companion.getArrayListProducts();
    private RecyclerView recyclerView;
    private AdapterRV adapterRV;

    private TextView tvProducersInfo;
    private TextView tvConsumersInfo;
    private TextView tvFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tvProducersInfo = (TextView) findViewById(R.id.info_producers);
        tvConsumersInfo = (TextView) findViewById(R.id.info_consumers);
        tvFeedback = (TextView) findViewById(R.id.feedback_added_removed);

        setupRecyclerView();
        setAdapter();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter(){
        adapterRV = new AdapterRV(arrayListProducts);
        recyclerView.setAdapter(adapterRV);
        adapterRV.notifyDataSetChanged();
    }


    public void addProducer(View view) {
        // get random from Recycler
    }

    public void addConsumer(View view) {
        // get random from dataProvider
    }


    // https://developer.android.com/reference/java/util/Timer
    // https://developer.android.com/reference/java/util/TimerTask

    private void removeItem(final int position, long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeItem(position);
            }
        }, 4000); // everty 4 seconds
    }

    private void removeItem(int position){
        arrayListProducts.remove(position);
        adapterRV.notifyItemRemoved(position);
    }

    private void addItem(final int position, long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addItem(position);
            }
        }, 3000); // every 3 seconds
    }

    private void addItem(int position){
        adapterRV.notifyItemInserted(position);

        // ut the added item on the top ?? test
        adapterRV.notifyItemMoved(0, 1);
    }


    // material design when producer / consumer is added
    private void showDefaultSnackBar (View view, String message){
        Snackbar snackbar = Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // maybe used for labeling different Producers or Consumers
    // created in difefrent times
    private String getFormattedHour(){
        String formattedHour = "";
        return formattedHour;
    }
}
