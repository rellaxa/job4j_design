package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");


    @Override
    public Calendar unmarshal(String v) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.parse(v));
        return calendar;
    }

    @Override
    public String marshal(Calendar date) throws Exception {
        return DATE_FORMAT.format(date.getTime());
    }

}
