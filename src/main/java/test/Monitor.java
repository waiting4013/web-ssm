package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monitor {
    //    @Value("${hkvi.config.host}")
    private  static String host = "10.32.19.253";
    //    @Value("${hkvi.config.appKey}")
    private  static String appKey =  "23953001";
    //    @Value("${hkvi.config.appSecret}")
    private  static String appSecret = "VncNI0lMkyktUvewVQyn";


    public static void main(String[] args) {
        String apiUrl = "/api/resource/v1/cameras";
        String result1 = doPostArtemis(apiUrl, 1, 1000);
        List<Map> info1 = getInfo(result1);
        String result2 = doPostArtemis(apiUrl, 2, 1000);
        List<Map> info2 = getInfo(result2);
        info1.addAll(info2);

/*        if (info1.size() == 0){
            return;
        }*/
/*        for (HeadquartersMonitorInfo headquartersMonitorInfo : info1) {
            System.out.println(headquartersMonitorInfo);

        }*/
    }

    public List<Map>  getList(){
        String apiUrl = "/api/resource/v1/cameras";
        String result1 = doPostArtemis(apiUrl, 1, 1000);
        List<Map> info1 = getInfo(result1);
        String result2 = doPostArtemis(apiUrl, 2, 1000);
        List<Map> info2 = getInfo(result2);
        info1.addAll(info2);
        return info1;
    }

    private static  List<Map> getInfo(String result){
        JSONObject json = JSON.parseObject(result);
        MonitorInfo monitorInfo;
        List<MonitorInfo> monitorInfos = new ArrayList<>();
        if (json == null){
            return new ArrayList<>();
        }
        JSONObject data = (JSONObject)json.get("data");
        Integer total = (Integer) data.get("total");
        JSONArray jsonArray = (JSONArray) data.get("list");
        return jsonArray.toJavaList(Map.class);
       /* for(int i=0; i < jsonArray.size(); i++){
            headquartersMonitorInfo = new HeadquartersMonitorInfo();
            headquartersMonitorInfo.setCameraIndexCode(((JSONObject)jsonArray.get(i)).getString("cameraIndexCode"));
            headquartersMonitorInfo.setCameraName(((JSONObject)jsonArray.get(i)).getString("cameraName"));
            headquartersMonitorInfo.setRegionIndexCode(((JSONObject)jsonArray.get(i)).getString("regionIndexCode"));
            headquartersMonitorInfo.setCameraTypeName(((JSONObject)jsonArray.get(i)).getString("cameraTypeName"));
            headquartersMonitorInfos.add(headquartersMonitorInfo);
        }
        return  headquartersMonitorInfos;*/
    }
    private static  String doPostArtemis(String apiUrl, int pageNo, int pageSize) {
        JSONObject jsonBody1 = new JSONObject();
        jsonBody1.put("pageNo", pageNo);
        jsonBody1.put("pageSize", pageSize);
        String params = jsonBody1.toJSONString();
        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        // artemis网关服务器ip端口
        ArtemisConfig.host = host;
        // 秘钥appkey
        ArtemisConfig.appKey = appKey;
        // 秘钥appSecret
        ArtemisConfig.appSecret = appSecret;
        ArtemisConfig config = new ArtemisConfig();
        config.setHost(host);
        config.setAppKey(appKey);
        config.setAppSecret(appSecret);
        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        String artemisPath = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String apiPath = artemisPath + apiUrl;

        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("http://", apiPath);
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        return ArtemisHttpUtil.doPostStringArtemis(path, params, null, null, contentType, null);
    }
}
