package test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobQuartzTest implements Job {
    private static  String triggerIdentity;

    private static String username;


    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("HelloJob实例:" + sf.format(date));
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        JobDataMap tDataMap = context.getTrigger().getJobDataMap();
        String user = dataMap.getString("user");
//        String username = tDataMap.getString("username");
        //通过contex里面取出user值
//        System.out.println("JobDetail获取传递数据:"+user);
        //通过定义username的setter和get方法自动获取username值
//        System.out.println("Trigger获取传递数据:"+username);
    }

    public static void main11111(String[] args) throws SchedulerException {
        //创建JobDetail实例  将实例与Hellojob绑定
        JobDetail jobDetail = JobBuilder.newJob(JobQuartzTest.class).withIdentity("jobName1", "group1").usingJobData("user", "lss").build();
        //
        System.out.println("jobDetail的jobName:" + jobDetail.getKey().getName());
        System.out.println("jobDetail的jobGroup:" + jobDetail.getKey().getGroup());
        System.out.println("jobDetail的jobClass:" + jobDetail.getJobClass().getName());

        //设置trigger开启时间
        Date starDate = new Date();
        starDate.setTime(starDate.getTime() + 5000L);

        //设置trigger结束时间
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 1000000L);

        //创建一个Trigge实例,定义Job的执行方式
        Trigger trig = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").usingJobData("username", "lss0555")
                //设置立即开启生效
//                .startNow()
                .startAt(starDate)
                //设置结束时间
                .endAt(endDate)
                //使用simpleTrigger
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        //2秒钟执行一次,一直运行下去
                        .withIntervalInSeconds(13).repeatForever()).build();
        //创建Schedule实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trig);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间:" + sf.format(new Date()));
//        String cron = "";
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                .withIdentity(triggerIdentity + "@@" + cron)
//                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
//                .forJob(jobDetail)
//                .build();

        //秒 分钟 小时 日 月 周 年
        //* * * * * ? *
    }

    public static  void cccc(){
        // js判断n分钟执行的循环次数，跨越的小时数(每小时单独一个表达式)
        Date basedate = new Date("2020/09/12 00:00");
        Date date = new Date("2020/09/12 00:00");
        System.out.println("date: " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        //时间间隔：分钟
        int num = 23;
        for (int i = 1; i <= 111; i++) {
            date.setMinutes(date.getMinutes() + num);
            System.out.println("date: " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            if (date.getMinutes() == basedate.getMinutes()) {
                System.out.println("---如果是每隔" + num +"分钟执行一次，循环第" + i + "次进入重新循环，跨越"
                        + (date.getHours() - basedate.getHours()) + "个小时，所以需要" + (date.getHours() - basedate.getHours()));
            }
        }
    }

    public static void main(String[] args) {
        createTrigger();
//        createTrigger();
    }

    private static  CronTrigger createTrigger() {


        String cronExpression = "0 0 12 *** ** * * ?";
        boolean isValid = CronExpression.isValidExpression(cronExpression);
        System.out.println("Cron表达式是否合法：" + isValid);



        String jobDetail = "test";
        String cron = "0 0/5 * * * ? *";
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerIdentity + "@@" + cron)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .forJob(jobDetail)
                .build();
        return cronTrigger;
    }




}
