package sjz.sgy.lb.service.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjz.sgy.lb.dao.score.ScoreMapper;

@Service
public class ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;

	
	
    public void voteToOther(Map<String, Object> map) throws Exception {
	
    	scoreMapper.voteToOther(map);

	}

	
public List<Map<String, Object>> queryScoreByMemberId() throws Exception {
		
		
		List<Map<String, Object>> list = scoreMapper.queryScoreByMemberId();
		
		return list;
	}

public List<Map<String, Object>> queryAllScore() throws Exception {
		
		
		List<Map<String, Object>> list = scoreMapper.queryAllScore();
		
		return list;
	}
public List<Map<String, Object>> queryVoteOpen() throws Exception {
	
	
	List<Map<String, Object>> list = scoreMapper.queryVoteOpen();
	
	return list;
}

    }
		   
		  

		    




