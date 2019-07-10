package com.company.quartz.job.service;


import com.company.quartz.job.entity.ScheduleJobEntity;

import java.util.List;

/**
 * 定时任务
 *
 */
public interface ScheduleJobService {

	ScheduleJobEntity queryById(Object Id);

	List<ScheduleJobEntity> query(ScheduleJobEntity entity);

	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobEntity entity);

	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);

	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);

	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);

	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);

	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);

	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
