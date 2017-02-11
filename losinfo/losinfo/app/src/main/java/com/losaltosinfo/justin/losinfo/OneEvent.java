package com.losaltosinfo.justin.losinfo;

import android.util.Log;

import com.google.api.client.util.DateTime;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Created by justin on 4/23/2016.
 */
public class OneEvent {
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);

    private String summary;
    private String description = "";
    private long startTimeSec;

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public long getStartTimeSec() {
        return startTimeSec;
    }

    public String getStartTimeString() {
        return OUTPUT_DATE_FORMAT.format(startTimeSec);
    }

    public OneEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public OneEvent setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public OneEvent setTimeSec(long startTimeSec) {
        this.startTimeSec = startTimeSec;
        return this;
    }

    public Map<String, String> getMap(){
        String strLong = Long.toString(startTimeSec);

        return ImmutableMap.<String, String>of(
                "summary",
                summary,
                "description",
                description,
                "start",
                getStartTimeString()
        );
    }

    public static final Function<OneEvent, Map<String,String>> TO_MAP = new Function<OneEvent, Map<String,String>>() {
        @Override
        public Map<String, String> apply(OneEvent event){
            Log.d("log", "lgo" + event.getSummary());
            return event.getMap();
        }
    };
}

