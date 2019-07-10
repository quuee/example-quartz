
package com.company.quartz.job.dao;


import com.company.quartz.core.Mapper;
import com.company.quartz.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 */
public interface ScheduleJobDao extends Mapper<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
