package sjz.sgy.lb.controller.work;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sjz.sgy.lb.service.work.WorkService;
import sjz.sgy.lb.util.R;

@RestController
public class WorkController {

	@Autowired
	private WorkService workService;
	
	
	 //新增工作量   
		@PostMapping("/work/addDayWork")
		public R addDayWork(@RequestBody Map<String, Object> map )throws Exception {
			workService.addDayWork(map);
			
			return R.ok("success");
		}
		
	   
	    //修改工作量
	    @PostMapping("/work/updateDayWork")
		public R updateDayWork(@RequestBody Map<String, Object> map )throws Exception {
			
	    	workService.updateDayWork(map);
			
			return R.ok("success");
		}
    //工作量列表
	@PostMapping("/work/queryDayWorkList")
	public R commissionUserList(@RequestBody Map<String, Object> map)throws Exception {
	    
		if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
				&& !"".equals(map.get("pageNum"))) {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		} else {
			PageHelper.startPage(1, 10);
		}
		List<Map<String,Object>> list = workService.queryDayWorkList();
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
				page.getPages());
	   }
	//工作量列表
		@PostMapping("/work/queryAllDayWorkList")
		public R queryAllDayWorkList(@RequestBody Map<String, Object> map)throws Exception {
		    
			if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
					&& !"".equals(map.get("pageNum"))) {
				PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
						Integer.parseInt(map.get("pageSize").toString()));
			} else {
				PageHelper.startPage(1, 10);
			}
			List<Map<String,Object>> list = workService.queryAllDayWorkList();
			PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
			return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
					page.getPages());
		   }
	
	
   
	
   
    
}
