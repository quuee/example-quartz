package com.company.quartz.job.service.impl;

import com.company.quartz.core.BaseService;
import com.company.quartz.job.config.Constant;
import com.company.quartz.job.dao.ScheduleJobDao;
import com.company.quartz.job.entity.ScheduleJobEntity;
import com.company.quartz.job.service.ScheduleJobService;
import com.company.quartz.job.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends BaseService<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {

	@Autowired
    private Scheduler scheduler;

	/**
	 * 项目启动时，初始化定时器
	 */
//	@PostConstruct
//	public void init(){
//		List<ScheduleJobEntity> scheduleJobList = this.baseMapper.selectAll();
//		for(ScheduleJobEntity scheduleJob : scheduleJobList){
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
//            //如果不存在，则创建
//            if(cronTrigger == null) {
//                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
//            }else {
//                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
//            }
//		}
//	}




	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        this.insertSelective(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        this.updateById(scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
			this.baseMapper.deleteByPrimaryKey(jobId);
    	}

	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
		Example example = new Example(ScheduleJobEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("jobId",Arrays.asList(jobIds));
        ScheduleJobEntity build = ScheduleJobEntity.builder().status(status).build();
        return baseMapper.updateByExampleSelective(build,example);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, this.baseMapper.selectByPrimaryKey(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

}
