package serviceA.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serviceA.dao.BaseDao;
import serviceA.dao.VideoDao;
import serviceA.pojo.Video;
import serviceA.service.VideoService;

/**
 * 
 * <br>
 * <b>功能：</b>VideoService<br>
 */
@Service
@Transactional(value ="transactionManager" ,readOnly = true)
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {
  private final static Logger logger= LogManager.getLogger("VideoServiceImpl");
//	@Autowired
    private VideoDao videoDao;

	@Override
	protected BaseDao<Video> getBaseDao() {
		return videoDao;
	}

}
