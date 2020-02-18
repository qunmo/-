package sjz.sgy.lb.controller.template;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.lingala.zip4j.model.FileHeader;
import sjz.sgy.lb.service.template.TemplateService;
import sjz.sgy.lb.util.R;

@RestController
public class TemplateController {

	@Autowired
	private TemplateService templateService;
    //模板列表
	@PostMapping("/template/queryTemplateList")
	public R commissionUserList(@RequestBody Map<String, Object> map)throws Exception {
	    
		if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
				&& !"".equals(map.get("pageNum"))) {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		} else {
			PageHelper.startPage(1, 10);
		}
		List<Map<String,Object>> list = templateService.queryTemplateList();
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
				page.getPages());
	   }
	
	
    //新增模板    
	@PostMapping("/template/addTemplate")
	public R addTemplate(@RequestBody Map<String, Object> map )throws Exception {
		templateService.addTemplate(map);
		
		return R.ok("success");
	}
	
    //上传文件压缩包模板
	@PostMapping("/template/uplaodZip3")
	public R uplaodZip( )throws Exception {
		templateService.uplaodZip();
		
		return R.ok("success");
		}
	
    //删除模板
    @PostMapping("/template/delTemplate")
	public R delTemplate(@RequestBody Map<String, Object> map )throws Exception {
		
		templateService.delTemplate(map);
		
		return R.ok("success");
	}
	
    @PostMapping("/template/uplaodZip2")
	public R uplaodZip2(@RequestBody MultipartFile file )throws Exception {
    	System.err.println(file);
		templateService.uplaodZip2(file);
		
		return R.ok("success");
		}
    @PostMapping("/template/uplaodZip")
	public R uplaodZip3(@RequestBody MultipartFile file )throws Exception {
    	System.err.println(file);
    	String path = templateService.uplaodZip3(file);
    	return R.ok().put("ZipUrl", path);
		
		}
    
}
