package com.zhwl.home_server.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
 * 说明：日期处理
 * 创建人：liangzhilin
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	//return: yyyy-MM-dd 格式的日期字符串
	public static String DateToStr(Date date){
		synchronized (sdfDay){
			return sdfDay.format(date);
		}
	}

	/**
	 * 获取当前时段、9点前为早餐（value=1）、11点到14点为午餐（value=2）、17点到19点为晚餐（value=3）
	 * 如果为逾期、则返回-1、-2、-3
	 */
	public static Integer getCurrentPeriod(){
		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHour();
		if(hour<11){ //早餐后
			if(hour >= 9)	return -1;
			return 1;
		}else if(hour>=14){
			if(hour>=19){
				return -3;
			}else if(hour >= 16)	return 3;
			return	-2;
		}
		return 2;
	}
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: (日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * (日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return
	 */
	public static boolean compareDate(Date s,Date e){
		return s.getTime() >= e.getTime();
	}


	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 */
	public static boolean compareDate(String s, String e,boolean after) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >fomatDate(e).getTime();
	}


	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }

	/**
	 * 得到n天之后的日期
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days,String format) {
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdfd = null;
		if(format==null||format.compareTo("")==0){
			sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else {
			sdfd = new SimpleDateFormat(format);
		}
		String dateStr = sdfd.format(date);
		return dateStr;
	}


	/**
	 * 得到n天之后的日期字符串
	 * @param date  日期
	 * @param days  天數
	 * @param format 日期格式
	 * @return
	 */
	public static String afterDayStr(Date date,int days,String format) {
		Calendar calendar = Calendar.getInstance(); // java.util包
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);
		date=calendar.getTime();
		return format == null || "".equals(format)?
				sdfDay.format(date):new SimpleDateFormat(format).format(date);
	}

	/**
	 * 得到n天之后的日期字符串
	 * @param date  日期
	 * @param days  天數
	 * @return
	 */
	public static String afterDayStr(Date date,int days) {
		return afterDayStr(date,days,"");
	}





	/**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

	/**
	   * 将短时间格式字符串转换为时间 yyyy-MM-dd
	   *
	   * @param strDate
	   * @return
	   */
 	public static Date strToDate(String strDate) throws ParseException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return  formatter.parse(strDate);
	 }
    /* 根据当前日期获得周的日期区间（周一和周日日期）
			*choice:0 本周 1 上周 2 下周
			* @return
			* @author zhaoxuepu
	* @throws ParseException
	*/
	public static String getTimeInterval(Date date, int choice) {
		if(choice==1){
			choice = -7;
		}else if(choice==2){
			choice = 7;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day+choice);
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	public static String getTimeIntervalNext(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return getTimeInterval(date,2);
	}

	public static String getTimeInterval(String strDate,Integer choice){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return getTimeInterval(date,choice);
	}
	public static Date[] getTimeIntervalNextAndDate(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(strDate);
			String[] strs = getTimeInterval(date,2).split(",");
			Date[] dates = new Date[2];
			dates[0] = formatter.parse(strs[0]);
			dates[1] = formatter.parse(strs[1]);
			return dates;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date[] getTimeIntervalNextAndDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String[] strs = getTimeInterval(date,2).split(",");
			Date[] dates = new Date[2];
			dates[0] = formatter.parse(strs[0]);
			dates[1] = formatter.parse(strs[1]);
			return dates;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 获取那一天的周一，不包括 时分秒
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date)throws Exception {
		String tempStr = sdfDay.format(date);
		date = sdfDay.parse(tempStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	/* 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	public static List<String> collectLocalDates(String timeStart, String timeEnd,String format){
		return collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd),format);
	}
	/* 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
   * @param timeStart
   * @param timeEnd
   * @return
           */
	public static List<String> collectLocalDates(String timeStart, String timeEnd){
		return collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd),null);
	}

	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> collectLocalDates(LocalDate start, LocalDate end,String format){
		if(format==null){
			format = "yyyy-MM-dd";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		// 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
		return Stream.iterate(start, localDate -> localDate.plusDays(1))
				// 截断无限流，长度为起始时间和结束时间的差+1个
				.limit(ChronoUnit.DAYS.between(start, end) + 1)
				// 由于最后要的是字符串，所以map转换一下
				.map(LocalDate->LocalDate.format(formatter))
				// 把流收集为List
				.collect(Collectors.toList());
	}



	public static  void main(String[] args){
		List<String> datas = collectLocalDates("2018-04-18","2018-04-20","EEEE");
		System.out.println("123123");
	}



	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	public static Map<String,List<String>> collectLocalDatesReturnDay(String timeStart, String timeEnd) throws ParseException {
		List<String> day  = collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd),null);
		List<String> name = collectLocalDatesName(timeStart,timeEnd);
		Map<String,List<String>> mp = new HashMap<>();
		mp.put("day",day);
		mp.put("name",name);
		return mp;
	}

	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	public static Map<String,List<String>> collectLocalDatesReturnDay(String timeStart, String timeEnd,String format) throws ParseException {
		List<String> day  = collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd),format);
		List<String> name = collectLocalDatesName(timeStart,timeEnd);
		Map<String,List<String>> mp = new HashMap<>();
		mp.put("day",day);
		mp.put("name",name);
		return mp;
	}

	/**
	 * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private static List<String> collectLocalDatesName(String beginDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Locale localeCN = Locale.SIMPLIFIED_CHINESE;
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE",localeCN);
		List<String> strings = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate));
		for (long d = cal.getTimeInMillis(); d <= sdf.parse(endDate).getTime(); d = get_D_Plaus_1(cal)) {
			strings.add(dateFm.format(d));
		}
		return strings;
	}


	private static long get_D_Plaus_1(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		return c.getTimeInMillis();
	}


	/**
	 * 获得该月第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	public static String getWeekNumByDate(String date) throws ParseException {
		return getWeekNumByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	}

	//通过日期返回当天是周几
	public static String getWeekNumByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String dayForWeek = "";
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = "日";
		}else{
			int number = c.get(Calendar.DAY_OF_WEEK) - 1;
			switch (number){
				case 1:dayForWeek="一";break;
				case 2:dayForWeek="二";break;
				case 3:dayForWeek="三";break;
				case 4:dayForWeek="四";break;
				case 5:dayForWeek="五";break;
				case 6:dayForWeek="六";break;
			}
		}
		return dayForWeek;
	}

	//根据日期获取  格式:月份/日期
	public static String getMonthAndDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH)+1;
		int days = cal.get(Calendar.DAY_OF_MONTH);
		return month+"/"+days;
	}


	//根据日期获取  格式:月份-日期
	public static String MonthAndDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH)+1;
		int days = cal.get(Calendar.DAY_OF_MONTH);
		return month+"-"+days;
	}

	public static Date addDate(Date date, int x)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);//24小时制
		date = cal.getTime();
		return date;
	}
	public static SimpleDateFormat getSdfYear() {
		return sdfYear;
	}

	public static SimpleDateFormat getSdfDay() {
		return sdfDay;
	}

	public static SimpleDateFormat getSdfDays() {
		return sdfDays;
	}

	public static SimpleDateFormat getSdfTime() {
		return sdfTime;
	}
}
