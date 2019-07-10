

package com.company.quartz.job.controller;

import com.company.quartz.job.entity.ScheduleJobLogEntity;
import com.company.quartz.job.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务日志
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@GetMapping("/list")
	public ResponseEntity list(@RequestParam Map<String, Object> params){

		return null;
	}

	/**
	 * 定时任务日志信息
	 */
	@GetMapping("/info/{logId}")
	public ResponseEntity<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryById(logId);

		return new ResponseEntity<ScheduleJobLogEntity>(log, HttpStatus.OK);
	}
}
