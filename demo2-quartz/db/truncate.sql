SET FOREIGN_KEY_CHECKS=0; -- 取消外键约束
truncate schedule_job;
truncate schedule_job_log;
truncate QRTZ_TRIGGERS;
truncate QRTZ_SIMPROP_TRIGGERS;
truncate QRTZ_SIMPLE_TRIGGERS;
truncate QRTZ_SCHEDULER_STATE;
truncate QRTZ_PAUSED_TRIGGER_GRPS;
truncate QRTZ_LOCKS;
truncate QRTZ_JOB_DETAILS;
truncate QRTZ_FIRED_TRIGGERS;
truncate QRTZ_CRON_TRIGGERS;
truncate QRTZ_CALENDARS;
truncate QRTZ_BLOB_TRIGGERS;
SET FOREIGN_KEY_CHECKS=1; --设置外键约束