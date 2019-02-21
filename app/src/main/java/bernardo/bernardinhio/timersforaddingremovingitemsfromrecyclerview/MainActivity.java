package bernardo.bernardinhio.timersforaddingremovingitemsfromrecyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

        setOnCreateViews();
        setupRecyclerView();
        setupAdapter();
    }

    private void setOnCreateViews(){
        tvProducersInfo = (TextView) findViewById(R.id.info_producers);
        tvConsumersInfo = (TextView) findViewById(R.id.info_consumers);
        tvFeedback = (TextView) findViewById(R.id.feedback_added_removed);

        updateFeedbackTop(BLACK, "", View.GONE);

        countCreatedProducers = countCreatedConsumers = 0;

        tvProducersInfo.setText("Producers(" + countCreatedProducers + ")");
        tvConsumersInfo.setText("Consumers(" + countCreatedConsumers + ")");
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setupAdapter(){
        adapterRV = new AdapterRV(arrayListOriginal);
        recyclerView.setAdapter(adapterRV);
        adapterRV.notifyDataSetChanged();
    }

    // reset Activity
    public void resetActivity(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onClickAddConsumer(View view) {
        if (arrayListActive.size() > 0){
            createTimerRemoveItem(4000); // 4 sec
            countCreatedConsumers++;
            tvConsumersInfo.setText("Consumers(" + countCreatedConsumers + ")");
            updateFeedbackTop(BLACK, "Consumer will remove 1 Item in... 4 seconds", View.VISIBLE);
            showDefaultSnackBar(view, "New Consumer Added");
        } else Toast.makeText(this, "You can't create Consumer\nNo more Items!", Toast.LENGTH_LONG).show();
    }

    private void createTimerRemoveItem(final long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (arrayListActive.size() > 0){
                    // recursive when reaching 4 sec create a new Timer
                    createTimerRemoveItem(delay);
                    removeRandomItem();
                }
            }
        }, delay); // every 4 seconds
    }

    private void removeRandomItem(){
        // remove Item from arrayListActive
        if (arrayListActive.size() > 0){
            Log.d("Consumers", "ArrayListSize: " + arrayListActive.size());

            // random can't be applied on 0 & 1
            final int positionToRemove = (arrayListActive.size() == 1) ? 0 : new Random().nextInt(arrayListActive.size() - 1);

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final String titleItemToRemove = arrayListActive.get(positionToRemove).getTitle();
                    arrayListActive.remove(positionToRemove);
                    adapterRV.notifyItemRemoved(positionToRemove);
                    Log.d("Consumers", titleItemToRemove + ": removed --> ArrayListSize: " + arrayListActive.size());
                    Log.d("Consumers", "positionRemoved: " + positionToRemove);
                    updateFeedbackTop(RED, "--> " + titleItemToRemove + "\nwas removed ~~ Next in... 4 sec", View.VISIBLE);
                    if (arrayListActive.size() == 1) Toast.makeText(MainActivity.this, "Reached final Item!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onClickAddProducer(View view) {
        createTimerAddItem(3000);
        countCreatedProducers++;
        tvProducersInfo.setText("Producer(" + countCreatedProducers + ")");
        updateFeedbackTop(BLACK, "Producer will add 1 Item in... 3 seconds", View.VISIBLE);
        showDefaultSnackBar(view, "New Producer Added");
    }

    private void createTimerAddItem(final long delay){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // recursive when reaching 3 sec create a new Timer
                createTimerAddItem(delay);
                addRandomItem();
            }
        }, delay); // every 3 seconds
    }

    private void addRandomItem(){

        // add Item from arrayListOriginal
        int positionItemToBeAdded = new Random().nextInt(arrayListOriginal.size() - 1);

        // get itemToBeAdded from the original list
        ProductModel itemToBeAdded = arrayListOriginal.get(positionItemToBeAdded);

        // copy Item from Original list and add it to Active list
        arrayListActive.add(itemToBeAdded);

        // find position where itemToBeAdded is added
        int indexItemToBeAdded = arrayListActive.indexOf(itemToBeAdded);

        // update the ArrayList by putting the Added element on Top (pos 0)
        Collections.swap(arrayListActive, indexItemToBeAdded, 0);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // New Item added on the top and Top previous Item changed location
                adapterRV.notifyDataSetChanged();

                final String titleItemAdded = arrayListActive.get(0).getTitle();

                updateFeedbackTop(GREEN, "--> " + titleItemAdded + "\nwas added ~~ Next in... 3 sec", View.VISIBLE);
            }
        });
    }

    // material design when producer / consumer is added
    private void showDefaultSnackBar (View view, String message){
        Snackbar snackbar = Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void updateFeedbackTop(String color, String message, int visibility){
        tvFeedback.setBackgroundColor(Color.parseColor(color));
        tvFeedback.setText(message);
        tvFeedback.setVisibility(visibility);
    }
}
