package sjz.sgy.lb.dao.score;

import java.util.List;
import java.util.Map;


public interface ScoreMapper {
	
		void voteToOther(Map<String, Object> map) throws Exception; 
		
		List<Map<String, Object>> queryScoreByMemberId() throws Exception;
		
		List<Map<String, Object>> queryAllScore() throws Exception;
		
		List<Map<String, Object>> queryVoteOpen() throws Exception;
	
	
}