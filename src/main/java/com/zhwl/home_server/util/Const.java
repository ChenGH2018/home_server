package com.zhwl.home_server.util;

import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;

/**
 * 项目名称：
 * @author:liangzhilin
 * 修改日期：2015/11/2
*/
public class Const {
	public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session用的用户
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String SETTLEFILEPATH = "uploadFiles/settleFile/";		//文件上传路径
	public static final String ADFILEPATH = "uploadFiles/adsystem/";		//文件上传路径
	public static final String BILLPATH = "billTemplates/";		//文件上传路径
	public static final String ImagePiex = "/image";		//文件读取前缀
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String STATIC_FILE_PATH = ".*/*.(html|js|css|map|ico|gif|bmp|png|jpg|jpeg|svg|mp3|eot|ttf|woff|woff2|exe)"; //静态文件
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(login?logout)|(code)|(interiorMemberInfo)|(memberInfo/getMemberInfoByCardNum*)|(verifyCardNumberBySms)|(mobile/)|(getAllSendMealPath)|(getSendMealPathByPage)).*";	//不对匹配该值的访问路径拦截（正则）
	public static final String FRONT_DESK_PATH = ".*/((setMeal/getMenuAndOrderByCard)|(orderManager/submitOrderBycardNum)).*";	//前台不拦截的URL
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化

	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};

	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};

	/**
	 * 七牛云凭证
	 */
	public static final String QI_NIU_ACCESS_KEY = "UBbNN_LGmOE7CuW3tVE_Z-Y5BJco-FtBLwnZV26p";
	public static final String QI_NIU_SECRET_KEY  = "NFqQnN2NX5QHTMCvm4Kid1uX93qAfTIaSbfLYB8R";
	public static final String QI_NIU_BUCKET_NAME  = "yhyl";
	public static final String QI_NIU_DOWNLOAD_URL = "http://p3rqvr5ih.bkt.clouddn.com";

	/**
	 * 其他驻点类别名称
	 */
	public static final String FIRST_SORT = "第一类";
	public static final String SECOND_SORT = "第二类";
	public static final String THIRD_SORT = "第三类";
	public static final String OTHER_SORT = "其他";

	/**
	 * 餐车下单标识
	 */
	public static final String CarSign = "餐车下单";

	/**
	 * 暂无图路径
	 */
	public static final String NotImg = "no_img.png";
	/**
	 * 七牛云图片样式
	 */
	public static final String imgSheet = "thumb";

}
