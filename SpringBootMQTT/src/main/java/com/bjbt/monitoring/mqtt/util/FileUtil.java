package com.bjbt.monitoring.mqtt.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class FileUtil {
	public static byte[] fileToByte(String filePath) throws Exception {
        FileInputStream fin = new FileInputStream(new File(filePath));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void byteToFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			String string = filePath + file.separator + fileName;
			file = new File(string);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
 /**
  * 从服务器上获得一个输入流
  * @param psurl
  * @return
  */
 	public static byte[] getInputStream(String psurl) {
 		InputStream inputStream = null;
 		HttpURLConnection httpURLConnection = null;
 		byte[] bytes = null;
 		try {
 			//注：不加会报错cookie错误（不清楚怎么回事，根据错误百度出的）
 			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
 			URL url = new URL(psurl);
 			httpURLConnection = (HttpURLConnection) url.openConnection();
 			// 设置网络连接超时时间
 			httpURLConnection.setConnectTimeout(10000);
 			// 设置应用程序要从网络连接读取数据
 			httpURLConnection.setDoInput(true);
 			httpURLConnection.setRequestMethod("GET");
 			int responseCode = httpURLConnection.getResponseCode();
 			if (responseCode == 200) {
 				// 从服务器返回一个输入流
 				inputStream =  httpURLConnection.getInputStream();
 				bytes  = new byte[inputStream.available()];
 			    //将文件内容写入字节数组，提供测试的case
 				inputStream.read(bytes);
 				inputStream.close();
 			}
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return bytes;
 	}
}
