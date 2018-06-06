package com.apicloud.moduleDemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by asus on 2018/2/7.
 */

public class TimeUtils
{

    public static final long SECONONDS_PER_DAY = 24*60*60;
    public static final long MILLISECONONDS_PER_DAY = SECONONDS_PER_DAY * 1000;
    public static final long SECONONDS_PER_HOUR = 60*60;

    public static final String TIME_FORMAT_NORMAL = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_NORMAL_TIME = "yyyyMMdd";
    public static final String TIME_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT_SHOW = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT_NORMAL_SHOW = "yyyy.MM.dd HH:mm:ss";
    public static final String TIME_FORMAT_NORMAL_SHOW_TYPE = "yyyy.MM.dd HH:mm";
    public static final String DAY_FORMAT_NORMAL = "yyyy.MM.dd";

    public static String covertTimeFormat(final String strTime,
                                          final String strTimeFormat,
                                          final String strToFormat)
    {
        SimpleDateFormat sdfFrom = new SimpleDateFormat(strTimeFormat);
        SimpleDateFormat sdfTo = new SimpleDateFormat(strToFormat);

        try
        {
            Date date = sdfFrom.parse(strTime);
            return sdfTo.format(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public static String getCurrentTimeString()
    {
        return time2String(System.currentTimeMillis(), TIME_FORMAT_NORMAL);
    }

    /**
     *
     * @param t  long转换成时间字符串
     * @param strTimeFomat
     * @return
     */
    public static String time2String(long t, String strTimeFomat)
    {
        Date date = new Date(t);
        SimpleDateFormat formatter = new SimpleDateFormat(strTimeFomat);
        String time = formatter.format(date);
        return time;
    }

    /**
     * 把String时间转换成long型
     * @param strTime
     * @return
     */
    public static long string2Time(String strTime)
    {
        return string2Time(strTime, TIME_FORMAT_NORMAL);
    }
    public static long string2Time(String strTime, String formatType)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        try
        {
            Date date = sdf.parse(strTime);
            if (date == null)
            {
                return 0;
            }
            else
            {
                return date.getTime();
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     * @param time
     * @param strTime
     * @return
     */
    public static  boolean time2Time(String time,String strTime,String strTimeType){
        SimpleDateFormat format =  new SimpleDateFormat(strTimeType);
        try {
            Date date=format.parse(time);
            Date date1=format.parse(strTime);
            if (date.getTime()<=date1.getTime()){//比较
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将秒数转换为形如“xx小时xx分xx秒”的字符串
     * @param lSecond
     * @return
     */
    public static String formatSecond(long lSecond)
    {
        String str = "";

        if (lSecond >= 0)
        {
            int nHours = (int) (lSecond / (60 * 60));
            int nMinutes = (int) (lSecond / 60 - nHours * 60);
            int nSeconds = (int) (lSecond - nMinutes * 60 - nHours * 60 * 60);

            int nDays = nHours / 24;
            nHours = nHours % 24;

            if(nDays == 1 &&  nHours == 0)
            {
                nDays = 0;
                nHours = 24;
            }

            if(nHours == 1 && nMinutes == 0)
            {
                nHours = 0;
                nMinutes = 60;
            }

            if(nMinutes == 1 && nSeconds == 0)
            {
                nMinutes = 0;
                nSeconds = 60;
            }

            if(nDays > 0)
            {
                str = String.format("%02d天%02d小时%02d分%02d秒", nDays, nHours, nMinutes, nSeconds);
            }
            else if (nHours > 0)
            {
                str = String.format("%02d小时%02d分%02d秒", nHours, nMinutes, nSeconds);
            }
            else if (nMinutes > 0)
            {
                str = String.format("%02d分%02d秒", nMinutes, nSeconds);
            }
            else
            {
                str = String.format("%02d秒", nSeconds);
            }
        }

        return str;
    }

    /**
     * 将秒数转换为形如“xx小时xx分”的字符串
     * @param lSecond
     * @return
     */
    public static String formatMinute(long lSecond)
    {
        String str = "";

        if (lSecond >= 0)
        {
            int nHours = (int) (lSecond / (60 * 60));
            int nMinutes = (int) (lSecond / 60 - nHours * 60);
            int nSeconds = (int) (lSecond - nMinutes * 60 - nHours * 60 * 60);

            int nDays = nHours / 24;
            nHours = nHours % 24;

            if(nDays == 1 &&  nHours == 0)
            {
                nDays = 0;
                nHours = 24;
            }

            if(nHours == 1 && nMinutes == 0)
            {
                nHours = 0;
                nMinutes = 60;
            }

            if(nMinutes == 1 && nSeconds == 0)
            {
                nMinutes = 0;
                nSeconds = 60;
            }

            if(nDays > 0)
            {
                str = String.format("%02d天%02d小时%02d分", nDays, nHours, nMinutes);
            }
            else if (nHours > 0)
            {
                str = String.format("%02d小时%02d分", nHours, nMinutes);
            }
            else if (nMinutes > 0)
            {
                str = String.format("%02d分", nMinutes);
            }
            else
            {
                str = "00分";
            }
        }

        return str;
    }

//	// 获取当前时间的描述（上午/下午/晚上）
//	public static String getCurTimeDesc()
//	{
//		Calendar now = Calendar.getInstance();
//		int nHour = now.get(Calendar.HOUR_OF_DAY);
//		if(nHour>=7 && nHour<12)
//		{
//			return "上午";
//		}
//		else if (nHour>=12 && nHour<18)
//		{
//			return "下午";
//		}
//		else
//		{
//			return "晚上";
//		}
//	}

    public static String getWeekText(int nIndex)
    {
        String arrWeekName[] = {"日", "一", "二", "三", "四", "五", "六"};
        if(nIndex >=0 && nIndex < arrWeekName.length)
        {
            return arrWeekName[nIndex];
        }
        else
        {
            return "";
        }
    }

    public static String formatBeginTime(String strDate)
    {
        if(strDate.isEmpty())
        {
            return "";
        }
        return TimeUtils.covertTimeFormat(strDate, TimeUtils.DAY_FORMAT_NORMAL, TimeUtils.TIME_FORMAT_NORMAL);
    }

    public static String formatEndTime(String strDate)
    {
        if(strDate.isEmpty())
        {
            return "";
        }
        return TimeUtils.covertTimeFormat(strDate + " 23:59:59", TimeUtils.TIME_FORMAT_NORMAL_SHOW, TimeUtils.TIME_FORMAT_NORMAL);
    }

    //获取当前日期往后一周的时间
    public static List<String> getNextSevenData()
    {

        int mYear; //当前年
        int mMonth; //当前月
        int mDay;
        int mWeek;
        int dayAmount = 4;//往后天数

        List<String> dates = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int maxDay = c.getActualMaximum(Calendar.DATE);//该月最大天数
        mYear = c.get(Calendar.YEAR);// 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mWeek = c.get(Calendar.DAY_OF_WEEK);

        //当天
//        String date = mYear + "-" + mMonth + "-" + mDay;
        String today = String.valueOf(mDay);
        dates.add("今天");
        //往后6天
        for (int i = 0; i < dayAmount; i++)
        {
            if (mDay + 1 > maxDay)
            { //超过最大天数
                if (mMonth + 1 > 12)
                {//该年最后一个月
                    mYear = mYear + 1;
                    mMonth = 1;
                }
                else
                {
                    mMonth = mMonth + 1;
                }
                mDay = 1;
            }
            else
            {
                mDay = mDay + 1;
            }
            String date = today+"-"+mDay;
            dates.add(date);
        }
        return dates;
    }

    //根据当前日期获得所在周的日期区间（周一和周日日期）
    public static String getTimeInterval(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek)
        {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        return imptimeBegin + "," + imptimeEnd;
    }

    public static List<Date> findDates(Date dBegin, Date dEnd)
    {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    public static List<String> findThisWeekDates()
    {
        List<String> arrList = new ArrayList<>();
        String yz_time=getTimeInterval(new Date());//获取本周时间
        String array[]=yz_time.split(",");
        String start_time=array[0];//本周第一天
        String end_time=array[1];  //本周最后一天
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfs = new SimpleDateFormat("MM/dd");
        try {
            Date dBegin = sdf.parse(start_time);
            Date dEnd = sdf.parse(end_time);
            List<Date> lDate = findDates(dBegin, dEnd);//获取这周所有date
            for (Date date : lDate)
            {
                if(sdfs.format(new Date()).equals(sdfs.format(date))){
                    arrList.add("今天");
                }else{
                    arrList.add(sdfs.format(date));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return arrList;
    }
}
