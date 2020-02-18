package sjz.sgy.lb.dao.work;

import java.util.List;
import java.util.Map;


public interface WorkMapper {
	
    void addDayWork(Map<String, Object> map) throws Exception; 
	
	void updateDayWork(Map<String, Object> map) throws Exception; 
	
	List<Map<String, Object>> queryDayWorkList() throws Exception;

	List<Map<String, Object>> queryAllDayWorkList() throws Exception;

	
}