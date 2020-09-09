package test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
            String todayNext = DateUtil.getTodayNext(-7);
            System.out.println(todayNext);
            DateUtil.getBeforeDay("2020-07-14");

            int dayOfMonth = LocalDate.now().getDayOfMonth();
            LocalDate.now();
            String firstDayOfBeforeMonth = DateUtil.getFirstDayOfBeforeMonth(LocalDate.now());
            System.out.println(dayOfMonth);
            System.out.println(firstDayOfBeforeMonth);




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
        }
}
