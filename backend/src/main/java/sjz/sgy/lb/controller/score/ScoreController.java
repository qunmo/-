package sjz.sgy.lb.controller.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sjz.sgy.lb.service.score.ScoreService;
import sjz.sgy.lb.util.R;

@RestController
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	
	 //投票的动作   
		@PostMapping("/work/voteToOther")
		public R voteToOther(@RequestBody Map<String, Object> map )throws Exception {
			scoreService.voteToOther(map);
			
			return R.ok("success");
		}
		// /work/queryScoreByMemberId
		 //查看自己的评分信息
		@PostMapping("/work/queryScoreByMemberId")
		public R queryScoreByMemberId(@RequestBody Map<String, Object> map)throws Exception {
		    
			if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
					&& !"".equals(map.get("pageNum"))) {
				PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
						Integer.parseInt(map.get("pageSize").toString()));
			} else {
				PageHelper.startPage(1, 10);
			}
			List<Map<String,Object>> list = scoreService.queryScoreByMemberId();
			PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
			return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
					page.getPages());
		   }
		
	    //发起人可以查看所有人的投票列表
	   
	@PostMapping("/work/queryAllScore")
	public R queryAllScore(@RequestBody Map<String, Object> map)throws Exception {
	    
		if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
				&& !"".equals(map.get("pageNum"))) {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		} else {
			PageHelper.startPage(1, 10);
		}
		List<Map<String,Object>> list = scoreService.queryAllScore();
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
				page.getPages());
	   }
	
	// 投票公开榜
	@PostMapping("/work/queryVoteOpen")
	public R queryVoteOpen(@RequestBody Map<String, Object> map)throws Exception {
	    
		if (map.get("pageSize") != null && map.get("pageNum") != null && !"".equals(map.get("pageSize"))
				&& !"".equals(map.get("pageNum"))) {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		} else {
			PageHelper.startPage(1, 10);
		}
		List<Map<String,Object>> list = scoreService.queryVoteOpen();
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		return R.ok().put("list", page.getList()).put("count", page.getTotal()).put("totalPages",
				page.getPages());
	   }
	
	
	
   
    
}
