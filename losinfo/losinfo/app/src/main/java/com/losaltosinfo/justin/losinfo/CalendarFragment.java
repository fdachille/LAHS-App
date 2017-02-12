package com.losaltosinfo.justin.losinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.api.client.util.DateTime;
import me.everything.providers.android.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.common.collect.FluentIterable;
import com.losaltosinfo.justin.losinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.core.Data;

import static com.losaltosinfo.justin.losinfo.CalendarListView.DATE_COMPARATOR;
import static com.losaltosinfo.justin.losinfo.CalendarListView.GetUrl;
import static com.losaltosinfo.justin.losinfo.CalendarListView.LONG_STRING_TO_DATE_TIME;
import static com.losaltosinfo.justin.losinfo.CalendarListView.SHORT_STRING_TO_DATE_TIME;
import static com.losaltosinfo.justin.losinfo.CalendarListView.olderThanToday;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    private TextView calendarList;
    private static String CALENDAR_URL = "https://www.googleapis.com/calendar/v3/calendars/mvla.net_3136363439353036363236%40resource.calendar.google.com/events?key=AIzaSyA9rfs-jb3ZfE48Vfas9qEAL1rU8tKGbYw&timeMin=2017-02-03T10:00:00-07:00&maxResults=5";
    private static final String TAG_ITEM_INFO = "items";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_START = "start";
    private static final String TAG_DATE_TIME = "dateTime";
    private static final String TAG_DATE = "date";
    private static final String LONG_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    private static final String TITLE = "Events";
    private static final SimpleDateFormat LONG_SIMPLE_DATE_FORMAT = new SimpleDateFormat(LONG_FORMAT, Locale.US);
    private static final String shortFormat = "yyyy-MM-dd";
    private static final SimpleDateFormat SHORT_SIMPLE_DATE_FORMAT = new SimpleDateFormat(shortFormat, Locale.US);
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy", Locale.US);
    private static final DateTime TODAY = new DateTime(System.currentTimeMillis() - 86400);

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(TITLE);
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
//                    String startDateTime = "";
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

    public class GetData extends AsyncTask<Void,Void,Void> {
        // Hashmap for ListView
        List<OneEvent> events;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(getActivity());
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
            ListView lv = (ListView) getActivity().findViewById(R.id.list);
            List<java.util.Map<String, String>> eventsList = FluentIterable.from(events).transform(OneEvent.TO_MAP).toImmutableList();
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    eventsList,
                    R.layout.calendar_item,
                    new String[]{"summary", "description", "start"},
                    new int[]{R.id.summary, R.id.description, R.id.start});
            lv.setAdapter(adapter);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
