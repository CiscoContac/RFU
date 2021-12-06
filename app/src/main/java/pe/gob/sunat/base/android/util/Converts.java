package pe.gob.sunat.base.android.util;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Converts {
        //String  = "03/26/2012 11:49:00 AM";
        //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");

        public static String  formatingDate(String dateString) {
                if(dateString.equalsIgnoreCase("")){
                        return "";
                }else{
                        Date date1= null;
                        try{
                                date1=new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(dateString);

                        }catch(java.text.ParseException e){

                                e.printStackTrace();
                        }
                        String niceDateStr  = (String) DateUtils.getRelativeTimeSpanString(date1.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
                        return niceDateStr;
                }

        }

        public static String  formatingShortDate(String dateString) {
                if(dateString.equalsIgnoreCase("")){
                        return "";
                }else{
                        Date date1= null;
                        try{
                                date1=new SimpleDateFormat("dd/MM/yy").parse(dateString);

                        }catch(java.text.ParseException e){

                                e.printStackTrace();
                        }
                        String niceDateStr  = (String) DateUtils.getRelativeTimeSpanString(date1.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
                        return niceDateStr;
                }

        }

        public static String getShortDate(String dateString){
                Date date1=null;
                try{
                        date1=new SimpleDateFormat("dd/MM/yy").parse(dateString);

                }catch(java.text.ParseException e){

                        e.printStackTrace();
                }
                return  date1.toString();
        }

        public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {

                Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
                SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
                String parsedDate = formatter.format(initDate);

                return parsedDate;
        }
}