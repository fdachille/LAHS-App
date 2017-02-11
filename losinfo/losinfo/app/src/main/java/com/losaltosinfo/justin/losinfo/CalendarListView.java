package com.losaltosinfo.justin.losinfo;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.support.v7.app.ActionBar;
// android.support.v7.app.Activity;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.losaltosinfo.justin.losinfo.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class CalendarListView extends AppCompatActivity {
    private TextView calendarList;
    private static String CALENDAR_URL = "https://www.googleapis.com/calendar/v3/calendars/mvla.net_3136363439353036363236%40resource.calendar.google.com/events?key=AIzaSyA9rfs-jb3ZfE48Vfas9qEAL1rU8tKGbYw&timeMin=2017-01-03T10:00:00-07:00";
    private static final String TAG_ITEM_INFO = "items";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_START = "start";
    private static final String TAG_DATE_TIME = "dateTime";
    private static final String TAG_DATE = "date";
    private static final String LONG_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    private static final String TITLE = "LAHS Calendar";
    private static final SimpleDateFormat LONG_SIMPLE_DATE_FORMAT = new SimpleDateFormat(LONG_FORMAT, Locale.US);
    private static final String shortFormat = "yyyy-MM-dd";
    private static final SimpleDateFormat SHORT_SIMPLE_DATE_FORMAT = new SimpleDateFormat(shortFormat, Locale.US);
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy", Locale.US);
    private static final DateTime TODAY = new DateTime(System.currentTimeMillis() - 86400);

    //ActionBar actionBar = getActionBar();
    //actionBar.setDisplayHomeAsUpEnabled(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setTitle(TITLE);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        new GetData().execute();
    }

    private List<OneEvent> ParseJSON(String json) {
        if (json != null) {
            try {
                List<OneEvent> events = new ArrayList<>();
                JSONObject jsonObj = new JSONObject(json);
                JSONArray items = jsonObj.getJSONArray(TAG_ITEM_INFO);
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);
                    OneEvent event = new OneEvent();

                    try {
                        event.setDescription(c.getString(TAG_DESCRIPTION));
                    } catch (JSONException e) {

                    }

                    event.setSummary(c.getString(TAG_SUMMARY));
                    JSONObject start = c.getJSONObject(TAG_START);

                    try {
                        //startDateTime = SHORT_STRING_TO_DATE_TIME_STRING.apply(start.getString(TAG_DATE));
                        event.setTimeSec(SHORT_STRING_TO_DATE_TIME.apply(start.getString(TAG_DATE)).getValue());
                    } catch(JSONException e) {

                    }

                    try {
                        //startDateTime = LONG_STRING_TO_DATE_TIME_STRING.apply(start.getString(TAG_DATE_TIME));
                        event.setTimeSec(LONG_STRING_TO_DATE_TIME.apply(start.getString(TAG_DATE_TIME)).getValue());
                    } catch(JSONException e) {

                    }

                    if(!olderThanToday(event.getStartTimeSec())) {
                        events.add(event);
                    }
                }
                Collections.sort(events, DATE_COMPARATOR);
                return events;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
            return null;
        }
    }



    public static final Function<String, String> SHORT_STRING_TO_DATE_TIME_STRING = new Function<String, String>() {
        @Override
        public String apply(String input) {
            if(input.isEmpty()) {
                return "";
            }
            try {
                DateTime dateTime = new DateTime(SHORT_SIMPLE_DATE_FORMAT.parse(input));
                return dateTime.toString();
            } catch (ParseException e) {
                Log.d("Exception", "unable to parse " + input + " " + e);
                return "";
            }
        }
    };

    public static final Function<String, String> LONG_STRING_TO_DATE_TIME_STRING = new Function<String, String>() {
        @Override
        public String apply(String input) {
            return LONG_STRING_TO_DATE_TIME.apply(input).toString();
        }
    };

    public static final Function<String, DateTime> LONG_STRING_TO_DATE_TIME = new Function<String, DateTime>() {
        @Override
        public DateTime apply(String input) {
            if(input == null || input.isEmpty()) {
                return new DateTime(0);
            }
            try {
                return DateTime.parseRfc3339(input);
            } catch (NumberFormatException e) {
                Log.d("NumberFormatException", "unable to parse " + input + " " + e);
                return new DateTime(0);
            }
        }
    };

    public static final Function<String, DateTime> SHORT_STRING_TO_DATE_TIME = new Function<String, DateTime>() {
        @Override
        public DateTime apply(String input) {
            if(input == null || input.isEmpty()) {
                return new DateTime(0);
            }
            try {
                return new DateTime(SHORT_SIMPLE_DATE_FORMAT.parse(input));

            } catch (ParseException e) {
                Log.d("NumberFormatException", "unable to parse " + input + " " + e);
                return new DateTime(0);
            }
        }
    };

    public static void fixDates(ArrayList<HashMap<String, String>> list) {
     //   String time1 = "2000-01-11T18:45:00-07:00";
     //   String time2 = "2016-04-26T18:45:00-07:00";
     //   DateTime dateTime1 = LONG_STRING_TO_DATE_TIME.apply(time1);
     //   DateTime dateTime2 = LONG_STRING_TO_DATE_TIME.apply(time2);
     //   int diff = (int)(dateTime1.getValue() - dateTime2.getValue());
     //   Log.d("compare", String.format("compare %s %s %d", dateTime1, dateTime2, diff));
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> items = list.get(i);
            DateTime time = LONG_STRING_TO_DATE_TIME.apply(items.get(TAG_DATE_TIME));
            String outpout = OUTPUT_DATE_FORMAT.format(time);

        }
    }

    public static boolean olderThanToday(long timeStampSec) {
        DateTime dateTime = new DateTime(timeStampSec);
        return dateTime.getValue() < TODAY.getValue();
    }

    public static final Comparator<OneEvent> DATE_COMPARATOR = new Comparator<OneEvent>() {
            @Override
            public int compare(OneEvent lhs, OneEvent rhs) {
                if (lhs.getStartTimeSec() > rhs.getStartTimeSec()) {
                    return 1;
                } else if (lhs.getStartTimeSec() < rhs.getStartTimeSec()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

    public class GetData extends AsyncTask<Void,Void,Void> {
        // Hashmap for ListView
        List<OneEvent> events;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(CalendarListView.this);
            proDialog.setMessage("Gathering Data...");
            proDialog.setCancelable(false);
            proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();

            // Making a request to url and getting response
            String jsonStr = GetUrl(CALENDAR_URL);
            events = ParseJSON(jsonStr);
            return null;
        }

      /*  public java.util.Date Rfc3339ToDateThroughJoda(String dateString) {
            DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
            DateTime dateTime = dateFormatter.parseDateTime(dateString);
            return dateTime.toDate();
        }*/
        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
/**
 * Updating received data from JSON into ListView
 * */
            ListView lv = (ListView)findViewById(R.id.list);
            List<Map<String, String>> eventsList = FluentIterable.from(events).transform(OneEvent.TO_MAP).toImmutableList();
            ListAdapter adapter = new SimpleAdapter(
                    CalendarListView.this,
                    eventsList,
                    R.layout.calendar_item,
                    new String[]{"summary", "description", "start"},
                    new int[]{R.id.summary, R.id.description, R.id.start});
            lv.setAdapter(adapter);
        }

    }
 /*   DefaultHttpClient httpclient = HttpClientBuilder.create().build();
    HttpPost httppost = new HttpPost(CALENDAR_URL);
// Depends on your web service
            httppost.setHeader("Content-type", "application/json");

    InputStream inputStream = null;
    String result = null;
    try {
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        inputStream = entity.getContent();
        // json is UTF-8 by default
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        result = sb.toString();
    } catch (Exception e) {
        // Oops
    }
    finally {
        try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
    }*/

//    public String getInternetData() throws Exception {
//
//        BufferedReader in = null;
//        String data = null;
//
//        try {
//            HttpClient client = new DefaultHttpClient();
//
//            URI website = new URI("https://server.com:8443/XoW");
//            HttpGet request = new HttpGet();
//            request.setURI(website);
//            HttpResponse response = client.execute(request);
//            response.getStatusLine().getStatusCode();
//
//            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer sb = new StringBuffer("");
//            String l = "";
//            String nl = System.getProperty("line.separator");
//            while ((l = in.readLine()) != null) {
//                sb.append(l + nl);
//            }
//            in.close();
//            data = sb.toString();
//            return data;
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                    return data;
//                } catch (Exception e) {
//                    Log.e("GetMethodEx", e.getMessage());
//                }
//            }
//        }
//    }

    public static String GetUrl(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
