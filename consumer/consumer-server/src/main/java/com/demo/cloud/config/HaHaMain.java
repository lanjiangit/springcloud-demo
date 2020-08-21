package com.demo.cloud.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.cloud.utils.LoadPropertiesFile;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiniu
 * @create 2020-07-30 14:42
 */
public class HaHaMain {
    public static void main(String[] args) {
        String str1 = "{\"table\":\"bms_st.employees\",\"op_type\":\"I\",\"op_ts\":\"2012-04-12 14:23:13.177344\",\"current_ts\":\"2012-04-12T14:23:19.796000\",\"pos\":\"00000000010000036968\",\"after\":{\"EID\":\"101\",\"ENAME\":\"changyin\",\"ESAL\":6666.66}}";
        String str2 = "{\"table\":\"bms_st.employees\",\"op_type\":\"U\",\"op_ts\":\"2012-04-12 14:23:13.177344\",\"current_ts\":\"2012-04-12T14:23:19.796000\",\"pos\":\"00000000010000036968\",\"before\":{\"EID\":\"101\",\"ENAME\":\"siling\",\"ESAL\":1000},\"after\":{\"EID\":\"101\",\"ENAME\":\"changyin\",\"ESAL\":6666.66}}";
        String str3 = "{\"table\":\"bms_st.department\",\"op_type\":\"I\",\"op_ts\":\"2012-04-12 14:23:13.177344\",\"current_ts\":\"2012-04-12T14:23:19.796000\",\"pos\":\"00000000010000036968\",\"after\":{\"DNO\":\"1\",\"DNAME\":\"HR\",\"DAGE\":123}}";
        JSONObject jsonObject = JSON.parseObject(str1);
        String result = parse(jsonObject);
        System.out.println(result);

    }

    /**
     * 重构方法
     * @param map
     * @return
     */
    private static String parse(JSONObject map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        String opType = map.getString("op_type");
        String table = map.getString("table");
        if (!StringUtils.hasText(opType) || !StringUtils.hasText(table)) {
            return null;
        }
        List<String> colunms = LoadPropertiesFile.getPropertyList(table);
        Map<String, String> ret = new HashMap<>();
        ret.put("op_type",opType);
        ret.put("tb_name",table);
        ret.put("ts",map.getString("op_ts"));
        JSONObject after = JSON.parseObject(map.getString("after"));
        JSONObject before = JSON.parseObject(map.getString("before"));
        
        if ("I".equals(opType)) {
            for (String colunm : colunms) {
                ret.put(colunm,after.getString(colunm));
            }
        }else if ("U".equals(opType)) {
            for (String colunm : colunms) {
                if ("ESAL".equals(colunm)) {
                    double after_money = Double.valueOf(after.getString(colunm));
                    double before_money = Double.valueOf(before.getString(colunm));
                    double eSal = after_money - before_money;
                    ret.put(colunm,String.valueOf(eSal));
                    continue;
                }
                ret.put(colunm,after.getString(colunm));
            }
        }else if ("D".equals(opType)) {
            for (String colunm : colunms) {
                if ("ESAL".equals(colunm)) {
                    ret.put(colunm,String.valueOf(Double.parseDouble("-"+ before.getString("ESAL"))));
                    continue;
                }
                ret.put(colunm,after.getString(colunm));
            }
            
        }
        return toSimpleJson(ret);
    }

    private static String toSimpleJson(Map<String,String> map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        map.entrySet().forEach(es -> {
            builder.append(es.getKey()).append(":").append(es.getValue()).append(",");
        });
        builder.delete(builder.lastIndexOf(","),builder.length());
        builder.append("}");
        return builder.toString();
    }

}
