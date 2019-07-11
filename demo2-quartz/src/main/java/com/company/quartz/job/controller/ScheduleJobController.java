package com.company.quartz.job.controller;

import com.company.quartz.job.entity.ScheduleJobEntity;
import com.company.quartz.job.service.ScheduleJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务
 *
 */
@Api(description = "定时任务接口，可持久化到数据库",value = "定时任务接口",tags = "定时任务")
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@ApiOperation(value = "定时任务列表",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/list")
	public ResponseEntity<PageInfo> list(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "20")int pageSize){

		PageHelper.startPage(pageNo,pageSize);
		List<ScheduleJobEntity> query = scheduleJobService.query(null);
        PageInfo<ScheduleJobEntity> scheduleJobEntityPageInfo = new PageInfo<>(query);
        return new ResponseEntity<>(scheduleJobEntityPageInfo,HttpStatus.OK);
	}

	/**
	 * 定时任务信息
	 */
	@ApiOperation(value = "id查询定时任务",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/info/{jobId}")
	public ResponseEntity<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.queryById(jobId);
		return new ResponseEntity<>(schedule,HttpStatus.OK);
	}

	/**
	 * 保存定时任务
	 */
	@ApiOperation(value = "保存定时任务",httpMethod = "POST",response = ResponseEntity.class)
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody ScheduleJobEntity scheduleJob){

		scheduleJobService.save(scheduleJob);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 修改定时任务
	 */
	@ApiOperation(value = "修改定时任务",httpMethod = "PUT",response = ResponseEntity.class)
	@PutMapping("/update")
	public ResponseEntity update(@RequestBody ScheduleJobEntity scheduleJob){

		scheduleJobService.update(scheduleJob);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 删除定时任务
	 */
	@ApiOperation(value = "删除定时任务",httpMethod = "DELETE",response = ResponseEntity.class)
	@DeleteMapping("/delete")
	public ResponseEntity delete(@RequestBody Long[] jobIds){

		scheduleJobService.deleteBatch(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 立即执行任务
	 */
	@ApiOperation(value = "立即执行任务",httpMethod = "POST",response = ResponseEntity.class)
	@GetMapping("/run")
	public ResponseEntity run(@RequestBody Long[] jobIds){

		scheduleJobService.run(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 暂停定时任务
	 */
	@ApiOperation(value = "暂停定时任务",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/pause")
	public ResponseEntity pause(@RequestBody Long[] jobIds){

		scheduleJobService.pause(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 恢复定时任务
	 */
	@ApiOperation(value = "恢复定时任务",httpMethod = "GET",response = ResponseEntity.class)
	@GetMapping("/resume")
	public ResponseEntity resume(@RequestBody Long[] jobIds){

		scheduleJobService.resume(jobIds);

		return new ResponseEntity(HttpStatus.OK);
	}

}
