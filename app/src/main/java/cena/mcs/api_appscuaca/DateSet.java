package cena.mcs.api_appscuaca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateSet {
    public static String formattedDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = inputFormat.parse(dateString);
            return formatToDayDateMonth(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String formatToDayDateMonth(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
        return outputFormat.format(date);
    }

    public static String getDayOfWeek(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return getDayName(dayOfWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDayName(int dayOfWeek) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (dayOfWeek >= 1 && dayOfWeek <= 7) {
            return days[dayOfWeek - 1];
        }
        return null;
    }
}