package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

// https://developer.android.com/reference/java/util/Timer
// https://developer.android.com/reference/java/util/TimerTask

public class MainActivity extends AppCompatActivity {

    private ArrayList<ProductModel> arrayListOriginal, arrayListActive;
    private RecyclerView recyclerView;
    private AdapterRV adapterRV;

    private TextView tvProducersInfo, tvConsumersInfo, tvFeedback;
    private int countCreatedProducers, countCreatedConsumers;

    private final String BLACK = "#000000";
    private final String RED = "#F85151";
    private final String GREEN = "#5AA700";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListOriginal = arrayListActive = ProductDataProvider.Companion.getArrayListProducts();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tvProducersInfo = (TextView) findViewById(R.id.info_producers);
        tvConsumersInfo = (TextView) findViewById(R.id.info_consumers);
        tvFeedback = (TextView) findViewById(R.id.feedback_added_removed);
        updateTopFeedback(BLACK, "", View.GONE);

        countCreatedProducers = countCreatedConsumers = 0;
        tvProducersInfo.setText("Producers(" + countCreatedProducers + ")");
        tvConsumersInfo.setText("Consumers(" + countCreatedConsumers + ")");

        setupRecyclerView();
        setAdapter();
    }


    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter(){
        adapterRV = new AdapterRV(arrayListOriginal);
        recyclerView.setAdapter(adapterRV);
        adapterRV.notifyDataSetChanged();
    }

    public void addProducer(View view) {
        createTimerAddItem(3000);
        Toast.makeText(this, "Producer will add\nItem in 3 sec", Toast.LENGTH_LONG).show();
    }

    private void createTimerRemoveItem(final long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // when reaching 4 sec create a new Timer
                createTimerRemoveItem(delay);
                removeRandomItem();
            }
        }, delay); // every 4 seconds
    }

    private void removeRandomItem(){
        // remove Item from arrayListActive
        int positionToRemove = new Random().nextInt(arrayListActive.size());
        arrayListActive.remove(positionToRemove);
        adapterRV.notifyItemRemoved(positionToRemove);
        countCreatedConsumers++;
        tvConsumersInfo.setText("Consumers(" + countCreatedConsumers + ")");
        updateTopFeedback(RED, "Consumer(" + getFormattedHour() + ") Removed Item", View.VISIBLE);
    }

    public void addConsumer(View view) {
        createTimerRemoveItem(4000);
        Toast.makeText(this, "Producer will remove\nItem in 4 sec", Toast.LENGTH_LONG).show();
    }

    private void createTimerAddItem(long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addRandomItem();
            }
        }, 3000); // every 3 seconds
    }

    private void addRandomItem(){
        // add Item from arrayListOriginal
        int positionItemToBeAdded = new Random().nextInt(arrayListOriginal.size());

        // get itemToBeAdded from the original list
        ProductModel itemToBeAdded = arrayListOriginal.get(positionItemToBeAdded);

        // copy Item from Original list and add it to Active list
        arrayListActive.add(itemToBeAdded);

        // find position where itemToBeAdded is added
        int indexItemToBeAdded = arrayListActive.indexOf(itemToBeAdded);

        // update the ArrayList by putting the Added element on Top (pos 0)
        Collections.swap(arrayListActive, indexItemToBeAdded, 0);

        // New Item added on the top and Top previous Item changed location
        adapterRV.notifyDataSetChanged();

        // add the Item in UI recycler
        //adapterRV.notifyItemInserted(positionItemToBeAdded);

        // ut the added item on the top ?? test
        //adapterRV.notifyItemMoved(0, 1);

        countCreatedProducers++;
        tvProducersInfo.setText("Producers(" + countCreatedProducers + ")");

        updateTopFeedback(GREEN, "Producer(" + getFormattedHour() + ") Added Item", View.VISIBLE);
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
    // created in different times
    private String getFormattedHour(){
        String formattedHour = "";
        // GET
        return formattedHour;
    }


    private void updateTopFeedback(String color, String message, int visibility){
        tvFeedback.setBackgroundColor(Color.parseColor(color));
        tvFeedback.setText(message);
        tvFeedback.setVisibility(visibility);
    }
}
