package com.kj.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.system.util.PropertiesUtil;

public class SmsUtil {
	private static final String REQUEST_URL = PropertiesUtil.getProperty("smsUrl");
	private static final String USERID = PropertiesUtil.getProperty("smsUserid");
	private static final String ACCOUNT = PropertiesUtil.getProperty("smsAccount");
	private static final String PASSWORD = PropertiesUtil.getProperty("smsPassword");
	
	public static void main(String args[]) throws Exception{
		System.out.print(queryRest());
	}
	/**
	 * 发送短信
	 * @param mobile	手机号
	 * @param content	发送内容(需加入【XX】标签)
	 * @return	是否发送成功
	 */
	public static boolean sendMessage(String mobile, String content) throws Exception{
		//验证手机号
		Pattern pattern = Pattern.compile("^[1][1-9][0-9]{9}$");
		Matcher matcher = pattern.matcher(mobile);
		if(!matcher.find()){
			return false;
		}
		String requestUrl = REQUEST_URL;
		String param = "action=send";
		param += "&userid="+USERID;
		param += "&account="+ACCOUNT;
		param += "&password="+PASSWORD;
		param += "&mobile="+mobile;
		param += "&content="+content;
		Document dom = getXmlFromUrl(requestUrl, param);
		if(dom!=null){
			Element rootEle = dom.getRootElement();
			String returnMsg = rootEle.element("returnstatus").getTextTrim();
			return returnMsg.equals("Success");
		}else{
			return false;
		}
	}

	/**
	 * 查询剩余短信数
	 * @return	剩余数,为空则查询失败
	 */
	public static String queryRest() throws Exception{
		String requestUrl = REQUEST_URL;
		String param = "action=overage";
		param += "&userid="+USERID;
		param += "&account="+ACCOUNT;
		param += "&password="+PASSWORD;
		Document dom = getXmlFromUrl(requestUrl, param);
		if(dom!=null){
			Element rootEle = dom.getRootElement();
			String returnMsg = rootEle.element("returnstatus").getTextTrim();
			if(returnMsg.equals("Sucess")){
				return rootEle.element("overage").getText();
			}
		}
		return "";
	}
	
	private static Document getXmlFromUrl(String requestUrl, String param) throws Exception{
		PrintWriter out = null;
        BufferedReader in = null;
        Document dom = null;
        try {
            URL realUrl = new URL(requestUrl);
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 发送请求参数
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    SAXReader reader = new SAXReader();
		    dom = reader.read(in);
        }
        finally{
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
        return dom;
	}
}
