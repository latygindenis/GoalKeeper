package goalkeeper.android.bignerdranch.com.goalkeeper.data;

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

    private String strSeparator = "__,__";

    public ArrayConvertHelper(String strSeparator) {
        this.strSeparator = strSeparator;
    }
    public ArrayConvertHelper() {
        this.strSeparator = strSeparator;
    }


    public String fromArrayToString (List<Date> dates){
        String str = "";
        if(dates != null){
            for (int i = 0;i<dates.size(); i++) {
                str = str+dates.get(i).toString();
                // Do not append comma at the end of last element
                if(i<dates.size()-1){
                    str = str+strSeparator;
                }
            }
        }

        return str;
    }

   public List<Date> fromStringToDateArray (String str)  {
        List<Date> dates = new ArrayList<>();
        String[] buffer_dates = str.split(strSeparator);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        for (int i=0; i<buffer_dates.length; i++){
            try {
                dates.add(format.parse(buffer_dates[i]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dates;
    }
}
