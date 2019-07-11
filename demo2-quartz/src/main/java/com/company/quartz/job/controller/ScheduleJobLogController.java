

package com.company.quartz.job.controller;

import com.company.quartz.job.entity.ScheduleJobLogEntity;
import com.company.quartz.job.service.ScheduleJobLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 */
@Api(description = "定时任务日志接口，从数据库获取",value = "定时任务日志接口",tags = "定时任务-日志")
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@ApiOperation(value = "定时任务日志列表",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/list")
	public ResponseEntity<PageInfo> list(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "20") int pageSize){
		PageHelper.startPage(pageNo,pageSize);
        List<ScheduleJobLogEntity> query = scheduleJobLogService.query(null);
        PageInfo<ScheduleJobLogEntity> scheduleJobLogEntityPageInfo = new PageInfo<>(query);
        return new ResponseEntity<>(scheduleJobLogEntityPageInfo,HttpStatus.OK);
	}

	/**
	 * 定时任务日志信息
	 */
	@ApiOperation(value = "定时任务日志列表",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/info/{logId}")
	public ResponseEntity<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryById(logId);

		return new ResponseEntity<>(log, HttpStatus.OK);
	}
}
