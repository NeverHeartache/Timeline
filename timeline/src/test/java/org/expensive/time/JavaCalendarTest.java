package org.expensive.time;

import java.util.Calendar;
import java.util.Locale;

public class JavaCalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, new Locale("ZH"));
        System.out.println(date);
    }
}
