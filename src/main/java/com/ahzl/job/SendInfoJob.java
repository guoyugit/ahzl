package com.ahzl.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <p>Title:UrlCheckJob.java</p >
 * <p>Description: </p >
 * <p>Copyright: 公共服务与应急管理战略业务本部 Copyright(c)2019</p >
 * <p>Date:2019/9/25 10:55</p >
 *
 * @version 1.0
 */
@Component
@Slf4j
public class SendInfoJob {
    int i = 1;

    /**
     *
     **/
    @Scheduled(cron = "0/3 * * * * ? ")
    public void urlCheck() {
        System.out.println("第" + i++ + "次执行任务！");
    }
}
