
package com.company.quartz.job.service.impl;

import com.company.quartz.core.BaseService;
import com.company.quartz.job.dao.ScheduleJobLogDao;
import com.company.quartz.job.entity.ScheduleJobLogEntity;
import com.company.quartz.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;


@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends BaseService<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {



}
