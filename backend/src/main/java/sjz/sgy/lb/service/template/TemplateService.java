package sjz.sgy.lb.service.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sjz.sgy.lb.dao.template.TemplateMapper;
import sjz.sgy.lb.util.FileTools;
import sjz.sgy.lb.util.FileTools2;
import sjz.sgy.lb.util.ZipFileTools2;

@Service
public class TemplateService {

	@Autowired
	private TemplateMapper templateMapper;

	
	public List<Map<String, Object>> queryTemplateList() throws Exception {
		
		
		List<Map<String, Object>> list = templateMapper.queryTemplateList();
		
		return list;
	}
    public void addTemplate(Map<String, Object> map) throws Exception {
	
		templateMapper.addTemplate(map);

	}

	public void delTemplate(Map<String, Object> map) throws Exception {
		
		templateMapper.delTemplate(map);

	}

	public void uplaodZip2(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
	    //上传文件	
			System.err.println(file);
			FileTools.uploadFile(file);
		//解压缩文件
		//返回文件夹名+index.heml
			
			
   }





	public void uplaodZip() throws Exception {
		ZipFileTools2.unzip("F:\\root\\clkj\\pic\\20200212170514326.zip","F:\\root\\clkj\\pic","test");
		 
   }
	public String uplaodZip3(MultipartFile file) throws Exception {
		
		return FileTools2.uploadFile(file);
		 
		// TODO Auto-generated method stub
		
	}

    }
		   
		  

		    




