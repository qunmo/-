package sjz.sgy.lb.service.work;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjz.sgy.lb.dao.work.WorkMapper;

@Service
public class WorkService {

	@Autowired
	private WorkMapper workMapper;

	
	 public void addDayWork(Map<String, Object> map) throws Exception {
			
	    	workMapper.addDayWork(map);

		}

		public void updateDayWork(Map<String, Object> map) throws Exception {
			
			workMapper.updateDayWork(map);

		}
	
	public List<Map<String, Object>> queryDayWorkList() throws Exception {
		
		
		List<Map<String, Object>> list = workMapper.queryDayWorkList();
		
		return list;
	}
     public List<Map<String, Object>> queryAllDayWorkList() throws Exception {
		
		
		List<Map<String, Object>> list = workMapper.queryAllDayWorkList();
		
		return list;
	}
	
   



    }
		   
		  

		    




