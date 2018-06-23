package goalkeeper.android.bignerdranch.com.goalkeeper.data;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Created by denis on 17.06.2018.
 */

public class ArrayConvertHelper {

    private String strSeparator = " ";

    public ArrayConvertHelper(String strSeparator) {
        this.strSeparator = strSeparator;
    }
    public ArrayConvertHelper() {

    }


    public String fromArrayToString (List<CalendarDay> dates){
        String str = "";
        if(dates != null){
            for (int i = 0;i<dates.size(); i++) {
                str = str+Integer.toString(dates.get(i).hashCode());
                // Do not append comma at the end of last element
                if(i<dates.size()-1){
                    str = str+strSeparator;
                }
            }
        }
        return str;
    }

   public List<CalendarDay> fromStringToDateArray (String str)  {
        List<CalendarDay> dates = new ArrayList<>();
        if (!str.equals("") && str != null){
            String[] buffer_dates = str.split(strSeparator);
            Integer date, year, month, day;
            for (int i=0; i<buffer_dates.length; i++ ){
                Log.d("Date", buffer_dates[i]);
                    date = Integer.parseInt(buffer_dates[i]);
                    year = date / 10000 ;
                    month = (date - year * 10000) / 100;
                    day = (date - year*10000 - month *100);
                    dates.add(CalendarDay.from(year, month, day));
            }
        }
        return dates;
    }
}
