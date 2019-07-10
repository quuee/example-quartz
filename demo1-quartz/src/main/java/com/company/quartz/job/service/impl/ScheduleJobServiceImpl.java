package com.company.quartz.job.service.impl;



import com.company.quartz.job.config.Constant;
import com.company.quartz.job.entity.ScheduleJobEntity;
import com.company.quartz.job.quartzUtil.ScheduleUtils;
import com.company.quartz.job.service.ScheduleJobService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {

//	@Autowired
//	private ScheduleJobDao jobDao;

	@Autowired
    private Scheduler scheduler;


	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		//初始化定时任务 ，没有数据库，就写死
		List<ScheduleJobEntity> scheduleJobList = new ArrayList<>();

        ScheduleJobEntity scheduleJobEntity = new ScheduleJobEntity();
        scheduleJobEntity.setJobId(1L);
        scheduleJobEntity.setBeanName("Task2");
        scheduleJobEntity.setMethodName("dataBack");
        scheduleJobEntity.setParams(null);
        scheduleJobEntity.setCronExpression("*/10 * * * * ?");
        scheduleJobEntity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        scheduleJobEntity.setCreateTime(new Date());

        scheduleJobList.add(scheduleJobEntity);


        for(ScheduleJobEntity scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}

	@Override
	public ScheduleJobEntity queryById(Object Id) {
		return null;
	}

	@Override
	public List<ScheduleJobEntity> query(ScheduleJobEntity entity) {
		return null;
	}

	@Override
	public void save(ScheduleJobEntity scheduleJob) {
//		scheduleJob.setCreateTime(new Date());
//		scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
//		jobDao.insert(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

	@Override
	public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

//		jobDao.updateByPrimaryKeySelective(scheduleJob);
    }

	@Override
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
			//删除数据
//			jobDao.deleteByPrimaryKey(jobId);
    	}
	}

//	@Override
//    public int updateBatch(Long[] jobIds, int status){
//    	Map<String, Object> map = new HashMap<>();
//    	map.put("list", jobIds);
//    	map.put("status", status);
//    	return jobDao.updateBatch(map);
//    }

    @Override
    public int updateBatch(Long[] jobIds, int status){
//        for (Long jobId : jobIds) {
//            ScheduleJobEntity entity = (ScheduleJobEntity)systemctlCache.getCacheDataByKey(jobId.toString());
//            entity.setStatus(status);
//        }
        return 1;
    }

	@Override
    public void run(Long[] jobIds) {
//    	for(Long jobId : jobIds){
//    		ScheduleUtils.run(scheduler, (ScheduleJobEntity)systemctlCache.getCacheDataByKey(jobId.toString()));
//    	}
    }

	@Override
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

}
