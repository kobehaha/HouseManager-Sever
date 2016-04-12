package com.house.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFileUtil {

	/**
	 * 上传文件到服务器上面，并返回文件的绝对路径+文件名以及前段传来的parameter的name和value。
	 * 其中文件保存在uploadFilePlace下面
	 * 
	 * @param request
	 * @param response
	 * @return 存放parameter的hash map，其中文件路径在hashMap中的key是"fileNames",fileCount
	 * @author LiangYiHuai
	 */

	public static String PROJECT_NAME = "HouseService02";
	public static String IMAGE_STORE = "uploadFilePlace";
	public static String IMAGE_STORE_MANAGER_ICO = "managerIcoFilePlace";
	public static String IMAGE_STORE_BUYER_ICO = "buyerIcoFilePlace";
	public static String IMAGE_STORE_SELLER_ICO = "sellerIcoFilePlace";
	public static String DISK = "/Users/kobe/Documents/workspace3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/waswebapps";

	public static Map<String, String> uploadManagerIco(HttpServletRequest request, HttpServletResponse response) {
		String filePathAndName = null;
		Map<String, String> parameters = new HashMap<String, String>();
		response.setContentType("text/plain");
		String tempFilePath = "tempUploadFilePlace";
		String tempFilePath2 = "managerIcoFilePlace";
		tempFilePath = request.getSession().getServletContext().getRealPath("/") + tempFilePath;
		tempFilePath2 = request.getSession().getServletContext().getRealPath("/") + tempFilePath2;
		System.out.println(tempFilePath);
		System.out.println(tempFilePath2);

		try {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4 * 1024);
			File tempFile = new File(tempFilePath);
			if (!tempFile.isDirectory() && !tempFile.exists()) {
				tempFile.mkdir();
				System.out.println("file folder is not exists");
				factory.setRepository(tempFile);

			}

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 1024);

			List<FileItem> items = upload.parseRequest(request);// Servlet上传

			Iterator<FileItem> iter = items.iterator();

			int fileCount = 0;
			String timeString = MyTimeFormat.changeDateToLongString(new Date());
			StringBuilder fileNames = new StringBuilder();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				System.out.println("size = " + item.getSize());
				if (item.isFormField()) {// 读取表单域中的数据
					String name = item.getFieldName();
					String value = item.getString();
					parameters.put(name, value);
					// System.out.println("name = " + name + "; value = " +
					// value);
				} else {

					// 获取文件名
					String fileName = item.getName();
					/*
					 * int index = fileName.lastIndexOf("\\"); fileName =
					 * fileName.substring(index + 1, fileName.length()); //
					 * System.out.println("filePath = "+fileName); long fileSize
					 * = item.getSize();
					 * 
					 * if ("".equals(fileName) && fileSize == 0) { return null;
					 * }
					 */

					int index = fileName.lastIndexOf(".");
					if (index == -1) {
						continue;
					}
					String sufix = fileName.substring(index);

					// 判断并创建文件夹tempFilePath2
					File temp = new File(tempFilePath2);
					if (!temp.exists() && !temp.isDirectory()) {
						temp.mkdir();
					}

					filePathAndName = tempFilePath2 + File.separator + timeString + fileCount + sufix;
					File tempFile2 = new File(filePathAndName);
					// 将文件路径放到map中
					// 将上传的文件放到tempFilePath2目录下面
					try {
						item.write(tempFile2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 返回的数据
					String returnFileName = PROJECT_NAME + "/" + IMAGE_STORE_MANAGER_ICO + "/" + timeString + fileCount
							+ sufix;
					fileNames.append(returnFileName + "#");

					fileCount++;
				}
			}
			// print
			System.out.println("fileNames = " + fileNames.toString());
			parameters.put("fileNames", fileNames.toString());
			parameters.put("fileCount", "" + (fileCount + 1));

		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return parameters;

	}

	public static Map<String, String> uploadSellerIcoFile(HttpServletRequest request, HttpServletResponse response) {
		String filePathAndName = null;

		Map<String, String> parameters = new HashMap<String, String>();

		response.setContentType("text/plain");

		// 第一个是暂时的缓存文件名
		// 第二个是存放文件的地方
		String tempFilePath = "tempUploadFilePlace";
		String tempFilePath2 = "sellerIcoFilePlace";
		tempFilePath = request.getSession().getServletContext().getRealPath("/")// 获取绝对路径
				+ tempFilePath;
		tempFilePath2 = request.getSession().getServletContext().getRealPath("/") + tempFilePath2;
		System.out.println(tempFilePath);
		System.out.println(tempFilePath2);

		try {
			// 创建一个基于硬盘的FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置向硬盘写数据时所用的缓冲区的大小，此处为4k
			factory.setSizeThreshold(4 * 1024);
			// 判断是否有没有这个文件目录
			File tempFile = new File(tempFilePath);
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				tempFile.mkdir();
				System.out.println("file folder is not exists");
			}
			// 设置暂时缓存目录
			factory.setRepository(tempFile);

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置文件最大不能超过1G
			upload.setSizeMax(1024 * 1024 * 1024);

			List<FileItem> items = upload.parseRequest(request);// Servlet上传

			Iterator<FileItem> iter = items.iterator();

			int fileCount = 0;
			String timeString = MyTimeFormat.changeDateToLongString(new Date());
			StringBuilder fileNames = new StringBuilder();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				System.out.println("size = " + item.getSize());
				if (item.isFormField()) {// 读取表单域中的数据
					String name = item.getFieldName();
					String value = item.getString();
					parameters.put(name, value);
					// System.out.println("name = " + name + "; value = " +
					// value);
				} else {

					// 获取文件名
					String fileName = item.getName();
					/*
					 * int index = fileName.lastIndexOf("\\"); fileName =
					 * fileName.substring(index + 1, fileName.length()); //
					 * System.out.println("filePath = "+fileName); long fileSize
					 * = item.getSize();
					 * 
					 * if ("".equals(fileName) && fileSize == 0) { return null;
					 * }
					 */

					int index = fileName.lastIndexOf(".");
					if (index == -1) {
						continue;
					}
					String sufix = fileName.substring(index);

					// 判断并创建文件夹tempFilePath2
					File temp = new File(tempFilePath2);
					if (!temp.exists() && !temp.isDirectory()) {
						temp.mkdir();
					}

					filePathAndName = tempFilePath2 + File.separator + timeString + fileCount + sufix;
					File tempFile2 = new File(filePathAndName);
					// 将文件路径放到map中
					// 将上传的文件放到tempFilePath2目录下面
					try {
						item.write(tempFile2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 返回的数据
					String returnFileName = PROJECT_NAME + "/" + "sellerIcoFilePlace" + "/" + timeString + fileCount
							+ sufix;
					fileNames.append(returnFileName + "#");

					fileCount++;
				}
			}
			// print
			System.out.println("fileNames = " + fileNames.toString());
			parameters.put("fileNames", fileNames.toString());
			parameters.put("fileCount", "" + (fileCount + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameters;
	}

	public static Map<String, String> uploadBuyerIcoFile(HttpServletRequest request, HttpServletResponse response) {
		String filePathAndName = null;

		Map<String, String> parameters = new HashMap<String, String>();

		response.setContentType("text/plain");

		// 第一个是暂时的缓存文件名
		// 第二个是存放文件的地方
		String tempFilePath = "tempUploadFilePlace";
		String tempFilePath2 = "buyerIcoFilePlace";
		tempFilePath = request.getSession().getServletContext().getRealPath("/")// 获取绝对路径
				+ tempFilePath;
		tempFilePath2 = request.getSession().getServletContext().getRealPath("/") + tempFilePath2;
		System.out.println(tempFilePath);
		System.out.println(tempFilePath2);

		try {
			// 创建一个基于硬盘的FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置向硬盘写数据时所用的缓冲区的大小，此处为4k
			factory.setSizeThreshold(4 * 1024);
			// 判断是否有没有这个文件目录
			File tempFile = new File(tempFilePath);
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				tempFile.mkdir();
				System.out.println("file folder is not exists");
			}
			// 设置暂时缓存目录
			factory.setRepository(tempFile);

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置文件最大不能超过1G
			upload.setSizeMax(1024 * 1024 * 1024);

			List<FileItem> items = upload.parseRequest(request);// Servlet上传

			Iterator<FileItem> iter = items.iterator();

			int fileCount = 0;
			String timeString = MyTimeFormat.changeDateToLongString(new Date());
			StringBuilder fileNames = new StringBuilder();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				System.out.println("size = " + item.getSize());
				if (item.isFormField()) {// 读取表单域中的数据
					String name = item.getFieldName();
					String value = item.getString();
					parameters.put(name, value);
					// System.out.println("name = " + name + "; value = " +
					// value);
				} else {

					// 获取文件名
					String fileName = item.getName();
					/*
					 * int index = fileName.lastIndexOf("\\"); fileName =
					 * fileName.substring(index + 1, fileName.length()); //
					 * System.out.println("filePath = "+fileName); long fileSize
					 * = item.getSize();
					 * 
					 * if ("".equals(fileName) && fileSize == 0) { return null;
					 * }
					 */

					int index = fileName.lastIndexOf(".");
					if (index == -1) {
						continue;
					}
					String sufix = fileName.substring(index);

					// 判断并创建文件夹tempFilePath2
					File temp = new File(tempFilePath2);
					if (!temp.exists() && !temp.isDirectory()) {
						temp.mkdir();
					}

					filePathAndName = tempFilePath2 + File.separator + timeString + fileCount + sufix;
					File tempFile2 = new File(filePathAndName);
					// 将文件路径放到map中
					// 将上传的文件放到tempFilePath2目录下面
					try {
						item.write(tempFile2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 返回的数据
					String returnFileName = PROJECT_NAME + "/" + "buyerIcoFilePlace" + "/" + timeString + fileCount
							+ sufix;
					fileNames.append(returnFileName + "#");

					fileCount++;
				}
			}
			// print
			System.out.println("fileNames = " + fileNames.toString());
			parameters.put("fileNames", fileNames.toString());
			parameters.put("fileCount", "" + (fileCount + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameters;
	}

	public static Map<String, String> uploadFile(HttpServletRequest request, HttpServletResponse response) {
		String filePathAndName = null;

		Map<String, String> parameters = new HashMap<String, String>();

		response.setContentType("text/plain");

		// 第一个是暂时的缓存文件名
		// 第二个是存放文件的地方
		String tempFilePath = "tempUploadFilePlace";
		String tempFilePath2 = "uploadFilePlace";
		tempFilePath = request.getSession().getServletContext().getRealPath("/")// 获取绝对路径
				+ tempFilePath;
		tempFilePath2 = request.getSession().getServletContext().getRealPath("/") + tempFilePath2;
		System.out.println(tempFilePath);
		System.out.println(tempFilePath2);

		try {
			// 创建一个基于硬盘的FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置向硬盘写数据时所用的缓冲区的大小，此处为4k
			factory.setSizeThreshold(4 * 1024);
			// 判断是否有没有这个文件目录
			File tempFile = new File(tempFilePath);
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				tempFile.mkdir();
				System.out.println("file folder is not exists");
			}
			// 设置暂时缓存目录
			factory.setRepository(tempFile);

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置文件最大不能超过1G
			upload.setSizeMax(1024 * 1024 * 1024);

			List<FileItem> items = upload.parseRequest(request);// Servlet上传

			Iterator<FileItem> iter = items.iterator();

			int fileCount = 0;
			String timeString = MyTimeFormat.changeDateToLongString(new Date());
			StringBuilder fileNames = new StringBuilder();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				System.out.println("size = " + item.getSize());
				if (item.isFormField()) {// 读取表单域中的数据
					String name = item.getFieldName();
					String value = item.getString();
					parameters.put(name, value);
					// System.out.println("name = " + name + "; value = " +
					// value);
				} else {

					// 获取文件名
					String fileName = item.getName();
					/*
					 * int index = fileName.lastIndexOf("\\"); fileName =
					 * fileName.substring(index + 1, fileName.length()); //
					 * System.out.println("filePath = "+fileName); long fileSize
					 * = item.getSize();
					 * 
					 * if ("".equals(fileName) && fileSize == 0) { return null;
					 * }
					 */

					int index = fileName.lastIndexOf(".");
					if (index == -1) {
						continue;
					}
					String sufix = fileName.substring(index);

					// 判断并创建文件夹tempFilePath2
					File temp = new File(tempFilePath2);
					if (!temp.exists() && !temp.isDirectory()) {
						temp.mkdir();
					}

					filePathAndName = tempFilePath2 + File.separator + timeString + fileCount + sufix;
					File tempFile2 = new File(filePathAndName);
					// 将文件路径放到map中
					// 将上传的文件放到tempFilePath2目录下面
					try {
						item.write(tempFile2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 返回的数据
					String returnFileName = PROJECT_NAME + "/" + IMAGE_STORE + "/" + timeString + fileCount + sufix;
					fileNames.append(returnFileName + "#");

					fileCount++;
				}
			}
			// print
			System.out.println("fileNames = " + fileNames.toString());
			parameters.put("fileNames", fileNames.toString());
			parameters.put("fileCount", "" + (fileCount + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameters;
	}

	public static boolean deletePicture(String path) {
		boolean flag = false;
		File file = new File(path);
		System.out.println("开始删除" + path);
		if (file.exists() && file.isFile()) {
			file.delete();
			flag = true;

		} else if (file.isDirectory()) {// 删除文件目录及其以下的文件
			boolean flag2 = deleteDirecotry(path);
			if (flag2) {
				flag = true;

			}

		} else {
			flag = true;
			System.out.println("文件遭到损坏,自动更新");
		}
		if (flag) {
			System.out.println("删除文件成功");
		}
		return flag;
	}

	private static boolean deleteDirecotry(String path) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;

		}
		File dirFile = new File(path);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.exists() || file.isFile()) {
				file.delete();
			} else {
				flag = deleteDirecotry(file.getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (flag) {
			System.out.println("删除目录成功");
		}

		return flag;

	}

}
