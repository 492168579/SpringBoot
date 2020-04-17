package com.bjbt.monitoring.kafka.util;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;


public class ImageUtil {
	public static String IMG_PATH="C:\\Users\\Alex\\Pictures\\ammeter";

		/**
		 * 图片转成16进制字符
		 * @param path 图片路径及名称 例如：f:/1.jpg
		 * @return 16进制字符
		 * @throws Exception
		 */
		public static String img2byte(String path) throws Exception
		{
			FileInputStream fis = null;
			try
			{
				StringBuffer sb = new StringBuffer();
				File file = new File(path); 
				fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
				byte[] buff = new byte[1024];
				int len = 0;
				while ((len = fis.read(buff)) != -1)
				{
					bos.write(buff, 0, len);
				}
				// 得到图片的字节数组
				byte[] result = bos.toByteArray();
				String str = byte2HexStr(result);
				return str;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}finally {
				if (null != fis) {
					fis.close();
				}
				
			}
			return null;
		}
		/**
		 * 16进制字符转成图片
		 * @param bytes 16进制字符
		 * @param path 生成图片路径及文件名 例如： f:/2.jpg
		 * @throws Exception
		 */
		public void byte2img(String bytes,String path) throws Exception
		{
			this.saveToImgFile(bytes.toUpperCase(), path);
		}
		
		
		

		/*
		 * 实现字节数组向十六进制的转换方法一
		 */
		public static String byte2HexStr(byte[] b)
		{
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++)
			{
				stmp = (Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
			return hs.toUpperCase();
		}

		private static byte uniteBytes(String src0, String src1)
		{
			byte b0 = Byte.decode("0x" + src0).byteValue();
			b0 = (byte) (b0 << 4);
			byte b1 = Byte.decode("0x" + src1).byteValue();
			byte ret = (byte) (b0 | b1);
			return ret;
		}

		/*
		 * 实现字节数组向十六进制的转换的方法二
		 */
		public static String bytesToHexString(byte[] src)
		{
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0)
			{
				return null;
			}
			for (int i = 0; i < src.length; i++)
			{
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2)
				{
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString();
		}
	
		
		public static void saveToImgFile(String src, String output)
		{
			if (src == null || src.length() == 0)
			{
				return;
			}
			try
			{
				FileOutputStream out = new FileOutputStream(new File(output));
				byte[] bytes = src.getBytes();
				for (int i = 0; i < bytes.length; i += 2)
				{
					out.write(charToInt(bytes[i]) * 16 + charToInt(bytes[i + 1]));
				}
				out.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		private static int charToInt(byte ch)
		{
			int val = 0;
			if (ch >= 0x30 && ch <= 0x39)
			{
				val = ch - 0x30;
			}
			else if (ch >= 0x41 && ch <= 0x46)
			{
				val = ch - 0x41 + 10;
			}
			return val;
		}
		
}
