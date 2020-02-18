package sjz.sgy.lb.util;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;

public class ZipFileTools2 {
	
	 public static void unzip (String source,String target,String targetName) throws Exception{
	        try {
	            File file = new File(source);
	            if(!file.exists() || file.isDirectory()){
	                throw new Exception("将要解压文件不存在或路径填写不正确！");
	            }

	            file = new File(target+File.separator+targetName);
	            if(!file.exists()){
	                file.mkdirs();
	                System.out.println("路径不存在，创建路径");
	            }
	            ZipFile zipfile = new ZipFile(source);
	            if (!zipfile.isValidZipFile()) {  
	                throw new Exception("压缩文件不合法,可能被损坏.");  
	            } 
	            zipfile.setFileNameCharset("GBK");
	            zipfile.extractAll(target+File.separator+targetName);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }


	    }
	
}


	

