package test;

import ssm.utils.DateUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    /**
     * @从制定URL下载文件并保存到指定目录
     * @param filePath 文件将要保存的目录
     * @param method 请求方法，包括POST和GET
     * @param url 请求的路径
     * @return
     */

    public static File saveUrlAs(String url, String filePath, String method){
        //System.out.println("fileName---->"+filePath);
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod("post");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+"123.png");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

        return file;

    }
        public  static void  main(String[] args) throws ParseException {

            /*String s1="1,12,34,5,6,7";

            String s2="1,2,3,4,5,6,7,12";
            String checkedCarType="1,7";
            String[] stringS1 = s1.split(",");
            String[] stringS2=s2.split(",");
            String[] checkedCarTypes=checkedCarType.split(",");
            List<String> tt1= Arrays.asList(stringS1);
            List<String> tt2= Arrays.asList(stringS2);


            int num=0;
            int num2=0;
            for (String car: checkedCarTypes) {

                if(tt1.contains(car)){
                    num=num+1;
                }

            }
            for (String car: checkedCarTypes) {

                if(tt2.contains(car)){
                    num2=num2+1;
                }

            }
            if(num==checkedCarTypes.length&&num2==checkedCarTypes.length){
                System.out.println("全部包含");
            }else {

                System.out.println("不全部包含");
            }*/

            /*BigDecimal bigDecimal = Tools.stringToBigDecimal("-100");
            System.out.println(bigDecimal);
            Calendar beginCal = Calendar.getInstance();

            String beginStr="2020-01-02";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            beginCal.setTime(sdf.parse(beginStr));
            String date = sdf1.format(beginCal.getTime());
            System.out.println(date);*/
            String formatString = "yyyy-MM-dd HH:mm:ss";

            SimpleDateFormat format = new SimpleDateFormat(formatString);
            String todayNext = DateUtils.getTodayNext(-7);
            //System.out.println(todayNext);
            DateUtils.getBeforeDay("2020-07-14");

            int dayOfMonth = LocalDate.now().getDayOfMonth();
            LocalDate.now();
            String firstDayOfBeforeMonth = DateUtils.getFirstDayOfBeforeMonth(LocalDate.now());
            //System.out.println(dayOfMonth);
            //System.out.println(firstDayOfBeforeMonth);


            String sql = "SELECT uo.coupon_value,uo.invoice_head,uo.subsidy_money,uo.order_id,uo.order_sum,uo.settle_sum,uo.original_cost,uo.oil_price,uo.unit_price,uo.oil_mass,uo.pay_order_time,uo.pay_status,eo.oil_name,eog.oilgun_name,eog.oilgun_id,yu.user_car_number,uo.car_type,uoipl.log_id,uo.order_sign,uo.is_filled"
                    + " FROM user_order uo LEFT JOIN ejiayou_oil eo ON ( eo.id = uo.oil_id) "
                    + " INNER JOIN ejiayou_order_oilgun eoo ON (eoo.order_id =uo.order_id) "
                    + "  LEFT JOIN yijiayou_user yu ON (yu.user_id = uo.user_id) "
                    + "  LEFT join user_order_invoice_print_logs uoipl on (uo.order_Id=uoipl.order_id and uoipl.state=1) "
                    + " INNER JOIN ejiayou_oilgun eog ON (	eoo.oilgun_id = eog.oilgun_id ) "
                    + " WHERE uo.state=1 and eo.state=1 and eoo.state=1 and eog.state=1 and	uo.pay_status = 1 "
                    + "  and uo.pay_order_time is not null AND uo.filling_station_id =" + 12
                    + " and eoo.oilgun_id in(select oilgun_id from oilgun_group where state=1  and is_apply=1 and server_group_id="
                    //+ groupId
                    + " and station_id=";
                    //+ stationId
                    //+ ") "
                   // + "  AND  uo.pay_order_time >='" + endSettlementTime + "' ORDER BY	uo.pay_order_time"

           /* String[] zz=s1.split(",");
            //相等的话查询一个的carTypes
            Integer [] s={1,2,3,8,9};
            Integer [] w={3,5,6};
            List<Integer> ee=new ArrayList<>();
            ee.add(3);
            ee.add(5);
            ee.add(6);
            List<Integer> ss=new ArrayList<>();
            //ss.add(1);
            //ss.add(2);
            ss.add(3);
            //ss.add(4);
            ss.add(5);
            ss.add(6);
            //ss.add(7);
            //ss.add(8);
            //ss.add(9);
            boolean ccc=ss.contains(ee);
            int ce=0;
            for (Integer i: s) {
               if(ss.contains(i)){
                ce=ce+1;
               }
            }
            System.out.println(ce);
            System.out.println(ccc);*/

            List<String> list = new ArrayList<>();
            String sa="sda";
            list.add(sa);
            list.add("sda1");
            list.add("sda2");
            list.add("sda3");
            String ddd = "sda";
            boolean contains = list.contains(ddd);
            //System.out.println(contains);



            //deal("",22223319);

            String monthFinalDay = DateUtils.getMonthFinalDay("2020-11-12", 0);


            System.out.println(monthFinalDay);

        }
        private static boolean  deal(String key,int stationId){

            //先查询redis是否存在配置
            String stationIds = null;
            //不用list的contain 是因为  对应的是匹配的==值,是比较的地址值.所以会出现value值相同的时候,未必是包含的
            List<String>  stations = Arrays.asList((stationIds==null?"":stationIds).split(","));
            ArrayList<String> arrayList = new ArrayList<>(stations);
            //Log.out("stationIdssss-------:" + arrayList);

            //如果存在
            if(stations.contains(String.valueOf(stationId))){
                //Log.out("返回值1-------:" + true);
                System.out.println("返回值1-------:" + true);
                return true;
            }else {
                //如果不存在 查询数据库
                //ServiceDB db = new ServiceDB();
                String tickerStationIds = "12,22223319";
                stations = Arrays.asList(tickerStationIds.split(","));
                arrayList = new ArrayList<>(stations);
//                Log.out("查询数据库的list-------:" + arrayList);
                //如果数据库存在更新redis
                if (stations.contains(String.valueOf(stationId))) {
                    System.out.println("返回值2-------:" + true);
                    //暂时存放   秒 单位
//                    JedisUtil.putAndSetExpire(key, tickerStationIds, 300);
                    return true;
                } else {
//                   Log.out("返回值3-------:" + false);
                    System.out.println("返回值3-------:" + false);
                    return false;
                }
            }
        }
}
