package cn.zucc.debug.frame.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class FileUtil {
	/**
	 * 读取文件
	 * @param url 文件的地址
	 * @return 文件的字节
	 */
	public static byte[] read(String url) {
		int i;
		byte[] buff = null;
		try {
			FileInputStream inputStream = new FileInputStream(url);
			i = inputStream.available();
			buff = new byte[i];
			inputStream.read(buff);
			// 记得关闭输入流
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// byte数组用于存放图片字节数据
		return buff;
	}

	/**
	 * 文件写入
	 * @param buff 字节
	 * @param url 储存路径
	 * @return
	 */
	public static boolean write(byte[] buff, String url) {
		try {
			File file = new File(url);
			if (!file.getParentFile().exists()) {
				if(!file.getParentFile().mkdirs()) {  
					return false;
				}
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream outPutStream = new FileOutputStream(file);
			outPutStream.write(buff);
			outPutStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
