package com.kazmik.rapido.app.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kazmik.rapido.app.R;
import com.kazmik.rapido.app.utils.location_autocomplete.PlaceAPI;
import com.kazmik.rapido.app.utils.location_autocomplete.PlaceResultItem;
import com.kazmik.rapido.app.utils.toolbar.colorizeToolbar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity implements View.OnClickListener{

    colorizeToolbar colorize;
    PlacesAutoCompleteAdapter fromAdapter,destinationAdapter;
    HandlerThread fromHandlerThread,destinationHandlerThread;
    Handler fromThreadHandler,destinationThreadHandler;
    String TAG = Home.class.getSimpleName();
    PlaceResultItem startLocation,endLocation;

    @Bind(R.id.home_nav_dc)
    LinearLayout home_nav_dc;

    @Bind(R.id.from_et)
    AutoCompleteTextView from_et;

    @Bind(R.id.destination_et)
    AutoCompleteTextView destination_et;

    @Bind(R.id.find_btn)
    TextView find_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        colorize = new colorizeToolbar();
        colorize.setColor(toolbar, Color.WHITE, Home.this);

        //navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ButterKnife.bind(this);


        fromAdapter = new PlacesAutoCompleteAdapter(Home.this, R.layout.autocomplete_list_item,0);
        from_et.setAdapter(new PlacesAutoCompleteAdapter(Home.this, R.layout.autocomplete_list_item,0));
        from_et.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                startLocation = fromAdapter.resultList.get(position);
            }
        });
        if (fromThreadHandler == null) {
            // Initialize and start the HandlerThread
            // which is basically a Thread with a Looper
            // attached (hence a MessageQueue)
            fromHandlerThread = new HandlerThread(TAG, android.os.Process.THREAD_PRIORITY_BACKGROUND);
            fromHandlerThread.start();

            // Initialize the Handler
            fromThreadHandler = new Handler(fromHandlerThread.getLooper()) {
                @Override
                public void handleMessage(final Message msg) {
                    if (msg.what == 1) {
                        ArrayList<PlaceResultItem> results = fromAdapter.resultList;

                        if (results != null && results.size() > 0) {
                            fromAdapter.notifyDataSetChanged();
                        }
                        else {
                            fromAdapter.notifyDataSetInvalidated();
                        }
                    }
                }
            };
        }
        from_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String value = s.toString();

                // Remove all callbacks and messages
                fromThreadHandler.removeCallbacksAndMessages(null);

                // Now add a new one
                fromThreadHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // Background thread

                        fromAdapter.resultList = fromAdapter.mPlaceAPI.autocomplete(value);

                        // Footer
                        if (fromAdapter.resultList!=null && fromAdapter.resultList.size() > 0) {
                            PlaceResultItem item = new PlaceResultItem();
                            item.setCity("footer");
                            item.setTitle("footer");
                            fromAdapter.resultList.add(item);
                        }

                        // Post to Main Thread
                        fromThreadHandler.sendEmptyMessage(1);
                    }
                }, 500);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        destinationAdapter = new PlacesAutoCompleteAdapter(Home.this, R.layout.autocomplete_list_item,1);
        destination_et.setAdapter(new PlacesAutoCompleteAdapter(Home.this, R.layout.autocomplete_list_item,1));
        destination_et.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                endLocation = destinationAdapter.resultList.get(position);
            }
        });
        if (destinationThreadHandler == null) {
            // Initialize and start the HandlerThread
            // which is basically a Thread with a Looper
            // attached (hence a MessageQueue)
            destinationHandlerThread = new HandlerThread(TAG, android.os.Process.THREAD_PRIORITY_BACKGROUND);
            destinationHandlerThread.start();

            // Initialize the Handler
            destinationThreadHandler = new Handler(destinationHandlerThread.getLooper()) {
                @Override
                public void handleMessage(final Message msg) {
                    if (msg.what == 1) {
                        ArrayList<PlaceResultItem> results = destinationAdapter.resultList;
                        if (results != null && results.size() > 0) {
                            destinationAdapter.notifyDataSetChanged();
                        }
                        else {
                            destinationAdapter.notifyDataSetInvalidated();
                        }
                    }

                }
            };
        }
        destination_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String value = s.toString();

                // Remove all callbacks and messages
                destinationThreadHandler.removeCallbacksAndMessages(null);

                // Now add a new one
                destinationThreadHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // Background thread

                        destinationAdapter.resultList = destinationAdapter.mPlaceAPI.autocomplete(value);

                        // Footer
                        if (destinationAdapter.resultList!=null && destinationAdapter.resultList.size() > 0) {
                            PlaceResultItem item = new PlaceResultItem();
                            item.setCity("footer");
                            item.setTitle("footer");
                            item.setPlace_id("footer");
                            destinationAdapter.resultList.add(item);
                        }

                        // Post to Main Thread
                        destinationThreadHandler.sendEmptyMessage(1);
                    }
                }, 500);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_routes();
            }
        });



        home_nav_dc.setOnClickListener(this);
    }

    void find_routes()
    {
        Log.d("from location",startLocation.getTitle()+startLocation.getPlace_id());
        Log.d("end location",endLocation.getTitle()+endLocation.getPlace_id());
    }

    @Override
    public void onClick(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawer_layout);


        switch (view.getId())
        {
            case R.id.home_nav_dc:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }

    class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

        ArrayList<PlaceResultItem> resultList = new ArrayList<>();

        Context mContext;
        int mResource;
        int fromTo;

        PlaceAPI mPlaceAPI = new PlaceAPI();

        public PlacesAutoCompleteAdapter(Context context, int resource,int fromTo) {
            super(context, resource);

            mContext = context;
            mResource = resource;
            this.fromTo = fromTo;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (position != (resultList.size() - 1))
                view = inflater.inflate(R.layout.autocomplete_list_item, null);
            else
                view = inflater.inflate(R.layout.autocomplete_google_logo, null);

            if (position != (resultList.size() - 1)) {
                TextView autocompleteTextView = (TextView) view.findViewById(R.id.autocompleteText);
                autocompleteTextView.setText(resultList.get(position).getTitle());
            }

            return view;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // Last item will be the footer
            try {
                return resultList.size();
            } catch (Exception e) {
                return 0;
            }
        }

        @Override
        public String getItem(int position) {
            return resultList.get(position).getCity();
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        resultList = mPlaceAPI.autocomplete(constraint.toString());
                        // Footer
                        PlaceResultItem item = new PlaceResultItem();
                        item.setCity("footer");
                        item.setTitle("footer");
                        item.setPlace_id("footer");
                        resultList.add(item);

                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    }
                    else {
                        notifyDataSetInvalidated();
                    }
                }
            };

            return filter;
        }
    }
}
