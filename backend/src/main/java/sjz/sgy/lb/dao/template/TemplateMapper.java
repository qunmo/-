package sjz.sgy.lb.dao.template;

import java.util.List;
import java.util.Map;


public interface TemplateMapper {
	List<Map<String, Object>> queryTemplateList() throws Exception;
	void addTemplate(Map<String, Object> map) throws Exception; 
	
	void delTemplate(Map<String, Object> map) throws Exception; 
	
}