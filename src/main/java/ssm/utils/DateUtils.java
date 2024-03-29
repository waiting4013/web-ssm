package ssm.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = Calendar.getInstance();

	public static String FORMAT_SHORT = "yyyy-MM-dd";

	public static String FORMAT_SHORT_DD = "yyyyMMdd";

	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	public static String FORMAT_SHORT_CN = "yyyy年MM月dd日";

	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static Date timestampToDate(long time) {
		Timestamp ts = new Timestamp(time);
		Date date = new Date();
		try {
			date = ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date timestampToDate(Timestamp ts) {
		Date date = new Date();
		try {
			date = ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getBeforeDay(String dateTxt) {
		Date beginDate = null;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String tragetDate = "";
		try {
			beginDate = dft.parse(dateTxt);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate = dft.parse(dft.format(date.getTime()));
			tragetDate = dft.format(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return tragetDate;
	}

	public static String getDayCondition(String startDate, String endDate) {
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			return null;
		} else {
			String sql = " BETWEEN '" + startDate + " 00:00:00'  AND '" + endDate + " 23:59:59' ";
			return sql;
		}
	}
	
	public static String formatForMinute(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * @param date
	 *            日期
	 * @return sql 输出某一天条件
	 */
	public static String getDayCondition(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		} else {
			String sql = " BETWEEN '" + date + " 00:00:00'  AND '" + date + " 23:59:59' ";
			return sql;
		}
	}

	public static String getFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return format.format(cal_1.getTime());
	}

	public static String getLastDay(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		Date theDate = calendar.getTime();
		// 上个月最后一天
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		day_last = endStr.toString();
		return day_last;
	}

	/**
	 * 向后推移n天
	 * 
	 * @return
	 */
	public static String getTodayNext(int n) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 *
	 * @param dateTxt 传入日期
	 * @param n  向后向前推移n天
	 * @return
	 */
	public static String getBeforeDays(String dateTxt,int n) {
		Date beginDate ;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String targetDate = "";
		try {
			beginDate = dft.parse(dateTxt);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) + n);
			Date endDate = dft.parse(dft.format(date.getTime()));
			targetDate = dft.format(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return targetDate;
	}

	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	public static String getNow() {
		return format(new Date());
	}

	public static String getNow(String format) {
		return format(new Date(), format);
	}

	public static Date formatTime(String strTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(strTime); // Thu Jan 18 00:00:00 CST 2007
		} catch (ParseException e) {
		}
		return date;
	}

	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, n);
		return cal.getTime();
	}

	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, n);
		return cal.getTime();
	}

	public static String getpreHour(String format, int h) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		date.setTime(date.getTime() + h * 60 * 60 * 1000);
		return sdf.format(date);
	}

	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(2) + 1;
	}

	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(5);
	}

	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(11);
	}

	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(12);
	}

	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(13);
	}

	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	public static int countDays(String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000L - t1 / 1000L) / 3600 / 24;
	}

	public static int countDays(String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000L - t1 / 1000L) / 3600 / 24;
	}

	public static int getDayOfWeek() {
		Calendar aCalendar = Calendar.getInstance();

		int x = aCalendar.get(7);
		return x;
	}

	public static boolean isDate(String date) {
		String[] arr = date.split("-");
		if (arr.length < 3)
			return false;
		try {
			int y = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int d = Integer.parseInt(arr[2]);
			if ((y < 0) || (m > 12) || (m < 0) || (d < 0) || (d > 31))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDate(String strDate, String pattern) {
		if (null == strDate || "".equals(strDate)) {
			return false;
		}
		parse(strDate, pattern);
		return true;
	}

	public static boolean isTime(String time) {
		String[] arr = time.split(":");
		if (arr.length < 2)
			return false;
		try {
			int h = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int s = 0;
			if (arr.length == 3) {
				s = Integer.parseInt(arr[2]);
			}
			if ((h < 0) || (h > 23) || (m < 0) || (m > 59) || (s < 0) || (s > 59))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDateTime(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		if (str.indexOf(" ") > 0) {
			String[] arr = str.split(" ");
			if (arr.length == 2) {
				return (isDate(arr[0])) && (isTime(arr[1]));
			}
			return false;
		}

		return isDate(str);
	}

	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int t = cal.get(7);
		if ((t == 7) || (t == 1)) {
			return true;
		}
		return false;
	}

	public static boolean isWeekend(String str) {
		return isWeekend(parse(str));
	}

	public static String getNextDate(String refenceDate, int intevalDays) {
		try {
			return getNextDate(parse(refenceDate, FORMAT_SHORT), intevalDays);
		} catch (Exception ee) {
		}
		return "";
	}

	public static String getNextDate(Date refenceDate, int intevalDays) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(refenceDate);
			calendar.set(5, calendar.get(5) + intevalDays);
			return format(calendar.getTime(), FORMAT_SHORT);
		} catch (Exception ee) {
		}
		return "";
	}

	public static long getIntevalDays(String startDate, String endDate) {
		try {
			return getIntevalDays(parse(startDate, FORMAT_SHORT), parse(endDate, FORMAT_SHORT));
		} catch (Exception ee) {
		}
		return 0L;
	}

	public static long getIntevalDays(Date startDate, Date endDate) {
		try {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();

			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
			long diff = endCalendar.getTime().getTime()-startCalendar.getTime().getTime();

			return diff / 86400000L;
		} catch (Exception ee) {
		}
		return 0L;
	}

	public static long getTodayIntevalDays(String startDate) {
		try {
			Date currentDate = new Date();

			SimpleDateFormat myFormatter = new SimpleDateFormat(FORMAT_SHORT);
			Date theDate = myFormatter.parse(startDate);

			return (currentDate.getTime() - theDate.getTime()) / 86400000L;
		} catch (Exception ee) {
		}
		return 0L;
	}

	public static long getTodayIntevalDays(Date startDate) {
		try {
			Date currentDate = new Date();

			return (currentDate.getTime() - startDate.getTime()) / 86400000L;
		} catch (Exception ee) {
		}
		return 0L;
	}

	public static String formatForDay(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static String formatForMonth(Date date) {
		return format(date, "yyyy-MM");
	}

	public static Date formatDate(String strTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(strTime); // Thu Jan 18 00:00:00 CST 2007
		} catch (ParseException e) {
		}
		return date;
	}

	public static Date formatMin(String strTime) {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(strTime); // Thu Jan 18 00:00:00 CST 2007
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * @name getLastMonth
	 * @description 返回上个月的‘年月’格式，如‘201609’ 上月月传“-1”，当月传“0”，下个月传“1”
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-9-1 下午3:19:31
	 */
	public static String getMonth(int intervals) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, intervals);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(calendar.getTime());
	}

	/**
	 * @name getMonth
	 * @description 返回指定日期的某几月前的‘年月’格式，如‘201609’
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 上午11:55:21
	 */
	public static String getMonth(String date, int intervals) {
		SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatYM = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(formatYMD.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, intervals);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatYM.format(calendar.getTime());
	}

	/**
	 * @name getMonthFirstDay
	 * @description 获得当前月份的第一天
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 下午12:01:39
	 */
	public static String getMonthFirstDay(int intervals) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, intervals);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01");
		return format.format(calendar.getTime());
	}

	/**
	 * @name getMonthFirstDay
	 * @description 获得指定月份date的前intervals个月的第一天
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 下午12:01:39
	 */
	public static String getMonthFirstDay(String date, int intervals) {
		SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(formatYMD.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, intervals);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatYMD.format(calendar.getTime());
	}

	/**
	 * 获取时间区间内的每一天
	 * @param begintTime
	 * @param endTime
	 * @return
	 */
	public static List<String> findDaysStr(String begintTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = null;
		Date dEnd = null;
		try {
			dBegin = sdf.parse(begintTime);
			dEnd = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> daysStrList = new ArrayList<String>();
		daysStrList.add(sdf.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			String dayStr = sdf.format(calBegin.getTime());
			daysStrList.add(dayStr);
		}
		return daysStrList;
	}

	/**
	 *
	 * 	获取时间的周一和周日
	 * @return
	 */
	public static Map<String,String> getWeekDate(String dateStr) throws ParseException {
		Map<String,String> map = new HashMap<>(2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =sdf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if(dayWeek==1){
			dayWeek = 8;
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		Date mondayDate = cal.getTime();
		String weekBegin = sdf.format(mondayDate);
		System.out.println("所在周星期一的日期：" + weekBegin);


		cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
		Date sundayDate = cal.getTime();
		String weekEnd = sdf.format(sundayDate);
		System.out.println("所在周星期日的日期：" + weekEnd);

		map.put("mondayDate", weekBegin);
		map.put("sundayDate", weekEnd);
		return map;
	}


/*	//总共有几周 当天是这段时间的第几周
	public static Long[] weekNum(String  begin,String  end) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parseDate(begin,DateUtils.FORMAT_DATE));
		int monday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(monday!=1){
			cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
			begin= DateUtils.format(cal.getTime(),DateUtils.FORMAT_DATE);
		}
		System.out.println("学期开始时间所在周周一日期："+begin);

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(DateUtils.parseDate(end,DateUtils.FORMAT_DATE));
		int sunday = cal1.get(Calendar.DAY_OF_WEEK) - 1;
		if(sunday!=0){
			cal1.set(Calendar.DATE, cal1.get(cal1.DATE) + 6);
			end = DateUtils.format(cal1.getTime(),DateUtils.FORMAT_DATE);
		}
		System.out.println("学期结束时间所在周周日日期："+end);

		Long totalWeek = 0l;
		Long thisWeek = 0l;
		String nowDate = DateUtils.format(new Date(),DateUtils.FORMAT_DATE);

		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;
		Long beginTime =DateUtils.parse(begin,DateUtils.FORMAT_DATE).getTime();
		Long endTime =DateUtils.parse(end,DateUtils.FORMAT_DATE).getTime();
		//当前时间的星期天
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		int thisSunDay = cal2.get(Calendar.DAY_OF_WEEK) - 1;
		if(thisSunDay!=0){
			cal2.set(Calendar.DATE, cal2.get(cal2.DATE) + 6);
			nowDate = DateUtils.format(cal2.getTime(),DateUtils.FORMAT_DATE);
		}
		System.out.println("当前时间所在周周日日期："+nowDate);

		Long now =  DateUtils.parse(nowDate,DateUtils.FORMAT_DATE).getTime();

		//得到总共的天数，因为开始时间没有计算进去故加1
		Long totalWeekTime = (endTime-beginTime)/ dd+1;
		Long thisWeekTime = (now-beginTime)/dd+1;
		totalWeek = totalWeekTime/7;//得到总的周数


		if(now>endTime){
			thisWeek = 1l;//默认显示第一周
		}else if(monday==0){
			thisWeek= thisWeekTime/7+1;//因英文第一天是周日故+1
		}else{
			thisWeek= thisWeekTime/7;
		}
		return new Long[]{thisWeek,totalWeek};
	}*/
	/**
	 * @name getMonthFinalDay
	 * @description 获取当前月份前intervals个月的最后一天的日期
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 下午2:02:10
	 */
	public static String getMonthFinalDay(int intervals) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, intervals + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	/**
	 * @name getMonthFinalDay
	 * @description 获取日期date之前intervals个月的最后一天的日期
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 下午2:03:33
	 */
	public static String getMonthFinalDay(String date, int intervals) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(date));
			calendar.add(Calendar.MONTH, intervals + 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return format.format(calendar.getTime());
	}

	/**
	 * @name getNextDayMonth
	 * @description 获取指定日期后一天的年月格式 ,如‘201609’
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 上午10:56:57
	 */
	public static String getNextDayMonth(String dayParam) {
		String month = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date yesterday = sdf.parse(dayParam);
			String today = DateUtil.getNextDate(yesterday, 1);

			month = today.substring(0, 4) + today.substring(5, 7);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return month;
	}

	/**
	 * @name getLastMonth
	 * @description 根据yyyymm的当月字符串，获取上个月的yyyymm字符串。比如输入201601，返回201512
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-10 上午11:14:01
	 */
	public static String getLastMonth(String currentMonth) {
		int month = Integer.parseInt(currentMonth.substring(4));
		int year = Integer.parseInt(currentMonth.substring(0, 4));
		if (month == 1) {
			year--;
			month = 12;
		} else {
			month--;
		}
		if (month < 10) {
			return year + "0" + month;
		} else {
			return year + "" + month;
		}
	}

	/**
	 * @name getLastDate
	 * @description 返回前一天的日期串，如传入‘2016-10-13’格式参数，返回‘2016-10-12’
	 * @return String
	 * @author Yang_WeiXin
	 * @time 2016-10-17 上午10:35:56
	 */
	public static String getLastDate(String today) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = null;

		try {
			toDate = sdf.parse(today);
			toDate = new Date(toDate.getTime() - 24 * 60 * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sdf.format(toDate);
	}

	/**
	 * @Title: getBeforeFirstDayOfMonth
	 * @Description: 获取上个月的第一天
	 * @author liuzhen
	 * @return
	 */
	public static String getFirstDayOfBeforeMonth(LocalDate today) {
		LocalDate beforeMonth = today.minusMonths(1);
		return beforeMonth.with(TemporalAdjusters.firstDayOfMonth()).format(DateUtil.DATE_FORMATTER);
	}

	/**
	 * @Title: getLastDayOfBeforeMonth
	 * @Description: 获取上一个月的最后一天
	 * @author liuzhen
	 * @return
	 */
	public static String getLastDayOfBeforeMonth(LocalDate today) {
		LocalDate beforeMonth = today.minusMonths(1);
		return beforeMonth.with(TemporalAdjusters.lastDayOfMonth()).format(DateUtil.DATE_FORMATTER);
	}

	/**
	 * @Title: getFirstDayOfMonthBeforeLast
	 * @Description: 获取上上月的第一天
	 * @author liuzhen
	 * @param today
	 * @return
	 */
	public static String getFirstDayOfMonthBeforeLast(LocalDate today) {
		LocalDate monthBeforeLast = today.minusMonths(2);
		return monthBeforeLast.with(TemporalAdjusters.firstDayOfMonth()).format(DateUtil.DATE_FORMATTER);
	}

	/**
	 * @Title: getLastDayOfMonthBeforeLast
	 * @Description: 获取上上月最后一天
	 * @author liuzhen
	 * @param today
	 * @return
	 */
	public static String getLastDayOfMonthBeforeLast(LocalDate today) {
		LocalDate beforeMonth = today.minusMonths(2);
		return beforeMonth.with(TemporalAdjusters.lastDayOfMonth()).format(DateUtil.DATE_FORMATTER);
	}

	public static String formatDateTime(Date date, int type) {
		if (date == null) {
			date = new Date();
		}
		return formatDateTime(date.getTime(), type);
	}

	public static String formatDateTime(long time, int type) {
		SimpleDateFormat df;
		switch (type) {
		case 0:
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 1:
			df = new SimpleDateFormat("HH:mm:ss");
			break;
		case 2:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 3:
			df = new SimpleDateFormat("MM-dd");
			break;
		case 4:
			df = new SimpleDateFormat("HH:mm");
			break;
		case 5:
			df = new SimpleDateFormat("MM-dd HH:mm:ss");
			break;
		case 6:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			break;
		case 7:
			df = new SimpleDateFormat("yyyy-MM");
			break;
		case 8:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		case 9:
			df = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case 10:
			df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			break;
		case 11:
			df = new SimpleDateFormat("yyyyMMddHHmmssss");
			break;
		case 12:
			df = new SimpleDateFormat("yyyy年MM月dd日");
			break;
		case 13:
			df = new SimpleDateFormat("yyyy.MM.dd");
			break;
		case 14:
			df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			break;
		case 15:
			df = new SimpleDateFormat("mm:ss");
			break;
		default:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return df.format(cal.getTime());
	}

	public static String getStringDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static Date format(String date) {
		return format(date, null);
	}

	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}

	/**
	 * 时间格式相互转换
	 * 
	 * @param date
	 *            字符串时间
	 * @param format1
	 *            现在的格式
	 * @param format2
	 *            要转换的格式
	 * @return
	 */
	public static String dateFormat(String date, String format1, String format2) {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
			SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
			Date date1 = sdf1.parse(date);
			String date2 = sdf2.format(date1);
			return date2;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 通过传入时间值,往前推时间
	 * 
	 * @param n
	 * @param format
	 * @param date
	 * @return
	 */
	public static String getTodayLastByCTR(int n, String format, String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(formatter.parse(date));
		} catch (ParseException e) {
		}
		calendar.add(Calendar.DATE, -n);// 把日期往后增加一天.整数往后推,负数往前移动
		Date returnDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return formatter.format(returnDate);
	}

	public static String getStartTime() {
		return new SimpleDateFormat(FORMAT_SHORT).format(new Date()) + " 00:00:00";
	}

	public static String getEndTime() {
		return new SimpleDateFormat(FORMAT_SHORT).format(new Date()) + " 23:59:59";
	}

	public static String getMonthAndDay(Timestamp date) {
		long time = date.getTime();
		Date current = new Date(time);
		return new SimpleDateFormat("MM-dd").format(current);
	}

	/**
	 * @description 获取时间段的月份-日期
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author liuz
	 * @date 2017年3月23日
	 */
	public static List<String> fillListDate(String startDate, String endDate) {
		List<String> dates = new ArrayList<>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(format.parse(startDate));

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(format.parse(endDate));
			while (startCal.before(endCal)) {
				dates.add(format2.format(startCal.getTime()));
				startCal.add(Calendar.DAY_OF_YEAR, 1);
			}
			dates.add(format2.format(endCal.getTime()));
		} catch (ParseException e) {
		}
		return dates;
	}

	public enum DiffType {
		Year, Quarter, Month, Week, Day, Hour, Minute, Second
	}

	/**
	 * 按指定日期单位计算两个日期间的间隔
	 * 
	 * @param timeInterval
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long dateDiff(DiffType diffType, Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		switch (diffType) {
		case Year: {
			int time = calendar.get(Calendar.YEAR);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.YEAR);
		}
		case Quarter: {
			int time = calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date1);
			time += calendar.get(Calendar.MONTH) / 4;
			calendar.setTime(date2);
			return time - calendar.get(Calendar.MONTH) / 4;
		}
		case Month: {
			int time = calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date1);
			time += calendar.get(Calendar.MONTH);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.MONTH);
		}
		case Week: {
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			int time = calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date1);
			time += calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.WEEK_OF_YEAR);
		}
		case Day: {
			long time = date1.getTime() / 1000 / 60 / 60 / 24;
			return time - date2.getTime() / 1000 / 60 / 60 / 24;
		}
		case Hour: {
			long time = date1.getTime() / 1000 / 60 / 60;
			return time - date2.getTime() / 1000 / 60 / 60;
		}
		case Minute: {
			long time = date1.getTime() / 1000 / 60;
			return time - date2.getTime() / 1000 / 60;
		}
		case Second: {
			long time = date1.getTime() / 1000;
			return time - date2.getTime() / 1000;
		}
		default:
			return date1.getTime() - date2.getTime();
		}
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfWeek(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int m = c.get(Calendar.MONTH);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		// 下面的代码作用是如果最后一天的日期是下一个月的话，就把这周的最后一天设成本月一号最后一天
		while (c.get(Calendar.MONTH) > m) {
			// 如果月份大于了最初给定的月份了的话
			c.add(Calendar.DAY_OF_MONTH, -1);// 日期循环减少一天，直到月份是本月为止
		}
		return c.getTime();
	}
	
	public static int getWeekDays(){
		 Calendar c = Calendar.getInstance();
	     return c.get(Calendar.DAY_OF_WEEK);
	 }
	 public static String formatDate(Date date,String format){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				if(date != null && !"".equals(date)){
					return sdf.format(date);
				}else{
					return null;
				}
			}catch(Exception e){
			}
			return "";
		}
	/**
	 * 2个日期之间相差的分钟数
	 * @param source 当前时间
	 * @param target 目标时间
	 * @return
	 */
	public static long getBetweenMinutes(String source, String target) {
		return timePre(source, target)/(1000*60);
	}
	
	/**
	 * 时间差
	 * @param source 当前时间
	 * @param target 目标时间
	 * @return
	 */
	public static long timePre(Date source, Date target) {
		return target.getTime() - source.getTime();
	}

	/**
	 * 时间差
	 * @param source 当前时间
	 * @param target 目标时间
	 * @return
	 */
	public static long timePre(String source, String target) {
		Date sourceTime = parseDate(source,FORMAT_LONG);
		Date targetTime = parseDate(target,FORMAT_LONG);
		if(null != sourceTime && null != targetTime) {
			return timePre(sourceTime, targetTime);
		}
		return -1L;
	}
	public static Date parseDate(String dateStr, String format){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			if(dateStr != null && !dateStr.equals("")){
				return sdf.parse(dateStr);
			}else{
				return null;
			}
		}catch(Exception e){
		}
		return null;
	}
	public static String FormatDateStringToString(String dateStr){
		Date date = parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	return formatDate(date,"yyyy年MM月dd日  HH:mm:ss");
	}	  
	 public static String FormatDateStringToStringBack(String dateStr){
			Date date = parseDate(dateStr, "yyyy年MM月dd日  HH:mm:ss");
	return formatDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 获取当前月份，格式如 yyyy-MM
	 * @Title: getCurMonth
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author xiaoxiao
	 * @return
	 */
	public static String getCurMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(calendar.getTime());
	}
	/**
	 * 获取当前日期是星期几<br>
	 *
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static Integer getWeekOfDate(Date dt) {
		Integer[] weekDays = {1, 2, 3, 4, 5, 6, 7};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 2;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	/**
	 * 获取指定区间连续天的时间,包含beginStr与endStr yyyy-MM-dd
	 * @Title: getBetweenDate
	 * @author xiaoxiao
	 * @param beginStr
	 * @param endStr
	 * @return
	 */
	public static List<String> getBetweenDate(String beginStr,String endStr){
		List<String> lstDate = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar beginCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();

		try {
			beginCal.setTime(sdf.parse(beginStr));
			endCal.setTime(sdf.parse(endStr));

			while(!endCal.before(beginCal)){
				String date = sdf.format(beginCal.getTime());
				lstDate.add(date);
				beginCal.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lstDate;

	}

	/**
	 * 获取指定区间连续月的时间,包含beginStr与endStr yyyy-MM
	 * @Title: getBetweenDate
	 * @author xiaoxiao
	 * @param beginStr
	 * @param endStr
	 * @return
	 */
	public static List<String> getBetweenMonthDate(String beginStr,String endStr) {
		List<String> lstDate = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar beginCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();

		try {
			beginCal.setTime(sdf.parse(beginStr));
			endCal.setTime(sdf.parse(endStr));

			while (!endCal.before(beginCal)) {
				String date = sdf.format(beginCal.getTime());
				lstDate.add(date);
				beginCal.add(Calendar.MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lstDate;
	}
	 /**
	  * 根据指定日期向后推移n天
	  * @return
	  */
	 public static String getTodayNextByDate(Date date , int n) {
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,n);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 return dateString;
	 }
	 public static Date parseYYYYMMDD(String dateStr){
			return parse(dateStr, FORMAT_SHORT);
	 }
	 
	 /**
	  * @Title: getNextHourDate
	  * @Description: 传入日期字符串dateStr，以及串的格式format，再传入小时数interval，获得format格式的新日期串
	  * @Author Yang Weixin
	  * @Time 2017年11月9日 下午4:56:29 
	  * @param dateStr
	  * @param format
	  * @param interval
	  * @return
	  */
	 public static String getNextHourDate(String dateStr,String format,int interval){
		 SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		 try {
			Date date = dateFormat.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, interval);
			
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	 }

	public static Boolean dateCompare(Date time1,Date time2,int numYear) {
		Date time3 = addDate(time1, Calendar.YEAR,numYear);
		return time3.getTime() < time2.getTime();
	}
	/**
	 * 时间加减
	 * @param date
	 * @param calendarField ：Calendar.YEAR/ Calendar.MONTH /Calendar.DAY
	 * @param amount
	 * @return
	 */
	private static Date addDate(final Date date, final int calendarField, final int amount) {
		if (date == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}



	/**
	 * 将秒转换为string
	 * @param number
	 * @return
	 */
	public static String  revertSecondToDateString(Long number){
		Long hours = number / 3600; // 小时数
		Long minutes = (number % 3600) / 60; // 分钟数
		Long seconds = (number % 3600) % 60; // 秒数
		System.out.println("转换结果：" + hours + "小时 " + minutes + "分钟 " + seconds + "秒");

		Long second = number%60;
		Long min = number*60%60;
		Long hour = number*60*60%60;
		System.out.println("转换结果：" + hour + "小时 " + min + "分钟 " + second + "秒");

		String time = hour+ ":" + min+ ":"+second;
		Date date = DateUtil.strToDate(time, DateUtil.TIME);
		String format = DateUtil.format(date, DateUtil.TIME);
		System.out.println(format);
		return format;
	}

	public static void main(String[] args) {
//		LocalDateTime now = LocalDateTime.now();
//		LocalDateTime last = LocalDateTime.of(2023,12,28,14,29,30);
//
//		long until = LocalDateTime.now().until(last, ChronoUnit.SECONDS);
//		long until2 = last.until(now, ChronoUnit.SECONDS);
//		revertSecondToDateString(until2);
////		String dateStr = "2017-11-01 12:11:36";
////		System.out.println(getNextHourDate(dateStr, FORMAT_LONG, 1));

		LoadingCache<String, Object> cache = cacheData(10, 29, 1, TimeUnit.MINUTES);
//		cache.put("test", 1212);

		try {
			Object test = cache.get("test");
			System.out.println(test);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}


	private static LoadingCache<String, Object> cacheData(int initialCapacity, int maximumSize,long  duration, TimeUnit unit){
		LoadingCache<String, Object> localCache = CacheBuilder.newBuilder()
				.initialCapacity(initialCapacity)
				.maximumSize(maximumSize)
				// 设定写入过期时间
				.expireAfterWrite(duration, unit)
				.build(new CacheLoader<String, Object>() {
					@Override
					public String load(String key) {
						return "null";
					}
				});


		return localCache;
	}
}