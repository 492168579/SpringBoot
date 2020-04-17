package com.yzy.could.esclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author wanghw
 *	Elasticsearch-restful客户端工具
 */
public class EsRestfulClient {
	
	private final static Log log = LogFactory.getLog(EsRestfulClient.class);
	
	/**
	 * 使用Elasticsearch-restful客户端 
	 * @param queryUrl	请求url
	 * @param queryBody	DSL请求体
	 * @return	查询JSON字符串查询结果json字符串
	 */
	public static String queryBodySearch(String queryUrl, String queryBody) {

		String dataJson = "";

		// 创建一个DefaultHttpClient的实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 发送post请求：创建一个HttpPost对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
		HttpPost httppost_count = new HttpPost(queryUrl);
		CloseableHttpResponse response_count = null;

		try {
			httppost_count.setEntity(new StringEntity(queryBody, "UTF-8"));
			response_count = httpclient.execute(httppost_count);
			int error = response_count.getStatusLine().getStatusCode();
			log.info("ES请求体查询请求：" + queryUrl + "，数据请求体：" + queryBody);
			if (error == 500) {
				log.error("ES请求体查询数据错误：" + error);
			}
			// 查询es所得数据
			HttpEntity entity_count = response_count.getEntity();
			// 流获取返回的数据主体内容.
			InputStream instream_count = entity_count.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(instream_count, "utf-8"));
			StringBuffer strBuf = new StringBuffer();
			String strTemp;
			while ((strTemp = br.readLine()) != null) {
				strBuf.append(strTemp);
			}

			log.info("ES请求体查询数据完成");
			dataJson = strBuf.toString();

		} catch (IOException e) {
			log.error("ES请求体查询数据，读取错误", e);
		} finally {
			// 关闭连接,释放资源
			try {
				response_count.close();
				httpclient.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}

		return dataJson;

	}
}
