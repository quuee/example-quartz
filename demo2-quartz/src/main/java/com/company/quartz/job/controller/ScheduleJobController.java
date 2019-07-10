
package com.company.quartz.job.controller;

import com.company.quartz.job.entity.ScheduleJobEntity;
import com.company.quartz.job.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@GetMapping("/list")
	public ResponseEntity list(@RequestParam Map<String, Object> params){

		return null;
	}

	/**
	 * 定时任务信息
	 */
	@GetMapping("/info/{jobId}")
	public ResponseEntity info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.queryById(jobId);

		return new ResponseEntity<ScheduleJobEntity>(schedule,HttpStatus.OK);
	}

	/**
	 * 保存定时任务
	 */
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody ScheduleJobEntity scheduleJob){


		scheduleJobService.save(scheduleJob);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 修改定时任务
	 */
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody ScheduleJobEntity scheduleJob){

		scheduleJobService.update(scheduleJob);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 删除定时任务
	 */
	@PostMapping("/delete")
	public ResponseEntity delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 立即执行任务
	 */
	@PostMapping("/run")
	public ResponseEntity run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 暂停定时任务
	 */
	@PostMapping("/pause")
	public ResponseEntity pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 恢复定时任务
	 */
	@PostMapping("/resume")
	public ResponseEntity resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

}
