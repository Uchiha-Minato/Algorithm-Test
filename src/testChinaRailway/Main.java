package testChinaRailway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Main {
    public static GetHttpJSON httpJSON = new GetHttpJSON("2023-04-10", "BJP", "GBZ", 1);

    public static void main(String[] args) {
        String result = httpJSON.Get();
        System.out.println(result);

/*
        JSONObject object = JSONObject.parseObject(result);
        String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(pretty);
*/

        int a = 01234;
        System.out.println(a);
    }
}
