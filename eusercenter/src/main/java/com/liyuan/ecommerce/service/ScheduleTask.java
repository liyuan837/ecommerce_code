package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.util.DateUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class ScheduleTask {
    //单位：毫秒，1分钟 = 60秒 = 60 * 1000毫秒
    public final static long ONE_Minute =  60 * 1000;

    /**
     * fixedDelay是当任务执行完毕后1分钟再执行。所以根据实际业务不同，我们会选择不同的方式
     */
    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){  
        System.out.println(DateUtil.getCurrentTime(DateUtil.FORMAT)+" >>fixedDelay执行....");
    }

    /**
     * fixedRate就是每多次分钟一次，不论你业务执行花费了多少时间。我都是1分钟执行1次
     */
    @Scheduled(fixedRate=ONE_Minute)  
    public void fixedRateJob(){  
        System.out.println(DateUtil.getCurrentTime(DateUtil.FORMAT)+" >>fixedRate执行....");
    }

    /**
     * cron表达式:
     * cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位：
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
     另外：1表示星期天，2表示星期一。
     * 第7为，年份，可以留空，取值1970-2099
     *
     * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
     * (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
     * (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
     * (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
     * (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：*'/y，等同于0/y
     */
    @Scheduled(cron="0 15 3 * * ?")  
    public void cronJob(){  
        System.out.println(DateUtil.getCurrentTime(DateUtil.FORMAT)+" >>cron执行....");
    }  
}  