package com.method;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Method {
    public long getDiffTimeDays1(Date dDateInput1,
                                Date dDateInput2, Time tTimeInput2) throws ParseException {

        String date1 = dDateInput1.toString();
        String date2 = dDateInput2.toString();
        String time1 = "00:00:00";
        String time2 = tTimeInput2.toString();

        String format = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        java.util.Date dateObj1 = sdf.parse(date1 + " " + time1);
        java.util.Date dateObj2 = sdf.parse(date2 + " " + time2);

        long diff = dateObj2.getTime() - dateObj1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    public long getDiffTimeDays2(Date dDateInput1,
                               Date dDateInput2) throws ParseException {

        String date1 = dDateInput1.toString();
        String date2 = dDateInput2.toString();
        String time1 = "00:00:00";
        String time2 = "00:00:00";

        String format = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        java.util.Date dateObj1 = sdf.parse(date1 + " " + time1);
        java.util.Date dateObj2 = sdf.parse(date2 + " " + time2);

        long diff = dateObj2.getTime() - dateObj1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }
}
