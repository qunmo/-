package sjz.sgy.lb.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import net.lingala.zip4j.core.ZipFile;

public class FileTools2 {
	
	//定义图片上传的文件目录
	//private static String FILE_BASE_PATH = "F:/root/clkj/zip";
	private static String FILE_BASE_PATH = "/root/clkj/zip/";
	/**
	 * @description 单文件上传
	 * @param file
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public static String uploadFile(MultipartFile file) throws Exception {
		String extName = "";
		String filePath = FILE_BASE_PATH;
		String originalFilename = file.getOriginalFilename();// 得到上传的文件名
		extName = originalFilename.substring(originalFilename.lastIndexOf("."));// 得到图片的后缀
		// 为了不让图片不重复进行相关操作
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = sdf.format(new Date());
		int randomCode = (int) (Math.random() * (999 - 100) + 100);
		// 生成新文件名
		String fileName = nowTime + randomCode + extName;

		// 创建了一个File对象，名字是newFile，路径是filePath，名字是filename。
		File newFile = new File(filePath, fileName);
		if (!newFile.exists()) {
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			newFile.createNewFile();
		}
		FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
		String picDataPath = filePath +  fileName;
		//return picDataPath;
	System.err.println(picDataPath);
	// public static void unzip (String source,String target,String targetName) throws Exception{
		//picDataPath 压缩文件位置  target解压后的目录 targetName解压后的名称
	       
	        	String target = FILE_BASE_PATH;
	        	System.err.println(target);
	        	System.err.println(fileName);
	        	String fileName1 = fileName.replace(".zip", "");
	        	String originalFilename1 = originalFilename.replace(".zip", "");
	        	System.err.println(fileName1+"aaaaaaa");
	            File file1 = new File(picDataPath);
	            if(!file1.exists() || file1.isDirectory()){
	                throw new Exception("将要解压文件不存在或路径填写不正确！");
	            }

	            file1 = new File(target+File.separator+fileName1 );
	            if(!file1.exists()){
	                file1.mkdirs();
	               System.err.println("路径不存在，创建路径");
	            }
	            ZipFile zipfile = new ZipFile(picDataPath);
	            if (!zipfile.isValidZipFile()) {  
	                throw new Exception("压缩文件不合法,可能被损坏.");  
	            } 
	            System.err.println(picDataPath);
	           
	            zipfile.setFileNameCharset("GBK");
	            System.err.println(target+File.separator+fileName1);
	            zipfile.extractAll(target+File.separator+fileName1);
	            
	            File file78 = new File(target+File.separator+fileName1);
	            
	            System.err.println(file78);
                String[] filelist =   file78.list();
                for (int i = 0; i < filelist.length; i++) {
                        File readfile = new File( target+File.separator+fileName1 + "/" + filelist[i]);
                        System.err.println("aaaaaaaaaaaaaaaaaaaaa");
                       // if (!readfile.isDirectory()) {
                        	System.err.println("bbbbbbbbbbbbbbbbbbbb");
                                System.out.println("path=" + readfile.getPath());
                                System.out.println("absolutepath=" + readfile.getAbsolutePath());
                                System.out.println("name=" + readfile.getName());
                                 String sbName = readfile.getName();
                      //  }
	            
	            System.err.println(target+fileName1);
	          picDataPath = "/zip/" + fileName1 + "/" + sbName +"/index.html";
	          System.err.println(picDataPath);
		

	                               }
                
                System.err.println(picDataPath);
				return picDataPath;
	
	
	
	}
	}
	


