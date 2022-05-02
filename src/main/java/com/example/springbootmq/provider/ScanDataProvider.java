package com.example.springbootmq.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmq.model.ScanRecord;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Component //能被工厂扫描到
public class ScanDataProvider {
    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BatchingRabbitTemplate batchQueueRabbitTemplate;

    @Value("classpath:personInfo/personInfo.json")
    private Resource personInfo;
    @Value("classpath:placeInfo/placeInfo.json")
    private Resource placeInfo;

    private JSONArray personArray = new JSONArray();//1万个人
    private JSONArray placeArray = new JSONArray();//29个场所
    String[] scanmethods = {"6","7","8"};//扫码方式
    String[] scantypes = {"SSM_JYZTM_1","FY_QRCODE_02","SSM_JYZTM_80"};//扫码类型

    //获取人和地点
    public void getPersonPlace(){
        try {
            String personData =  IOUtils.toString(personInfo.getInputStream(), Charset.forName("UTF-8"));
            String placeData =  IOUtils.toString(placeInfo.getInputStream(), Charset.forName("UTF-8"));
            personArray= JSONArray.parseArray(personData);
            placeArray = JSONArray.parseArray(placeData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //随机产生时间
   // @PostConstruct
    public String RadomDate(){
        Random random = new Random();
        int minDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2022, 5, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        System.out.println(randomBirthDate.toString());
        return randomBirthDate.toString();

    }
   // @PostConstruct
    public static String RadomTime() {
        int randomTime = ThreadLocalRandom
                .current()
                .nextInt(21600, 86399);
        System.out.println(LocalTime.ofSecondOfDay(randomTime).toString());
        return LocalTime.ofSecondOfDay(randomTime).toString();
    }
    @PostConstruct
    public void generateScanRecord(){
        //获取人和地点
        getPersonPlace();
        int i = 0;
        while (i<10000000){
            ScanRecord scanRecord = new ScanRecord();
            //随机产生一个人和一个地点
            Random r = new Random();
            int personIdx = r.nextInt(9999);
            int placeIdx = r.nextInt(19);
            JSONObject personObj = personArray.getJSONObject(personIdx);
            JSONObject placeObj = placeArray.getJSONObject(placeIdx);
            scanRecord.setName_sm4(personObj.getString("name_sm4"));
            scanRecord.setColor(personObj.getString("color"));
            scanRecord.setUserid(personObj.getString("userid"));
            scanRecord.setCard_sm4(personObj.getString("card_sm4"));
            scanRecord.setIsforeigner(personObj.getString("isforeigner"));
            scanRecord.setScanaddress(placeObj.getString("scanaddress"));
            scanRecord.setCompanycode(placeObj.getString("companycode"));
            scanRecord.setCompanyremark(placeObj.getString("companyremark"));
            scanRecord.setCompanytype(placeObj.getString("companytype"));
            scanRecord.setCompanyname(placeObj.getString("companyname"));
            scanRecord.setFu_qhdm(placeObj.getString("fu_qhdm"));

            //随机产生一个时间
            String date = RadomDate();
            String time = RadomTime();

            //生成标识主键
            String smqy_id = UUID.randomUUID().toString();
            //生成id
            String id = personObj.getString("sfz")+date+" "+ time;
            //生成扫码时间
            String scantime = date+" "+time;
            //随机生成扫码方式和方法
            String scanmethod = scanmethods[r.nextInt(2)];
            String scantype = scantypes[r.nextInt(2)];

            scanRecord.setSmqy_id(smqy_id);
            scanRecord.setId(id);
            scanRecord.setScantime(scantime);
            scanRecord.setScanjwd("");
            scanRecord.setScanmethod(scanmethod);
            scanRecord.setScantype(scantype);
            scanRecord.setFu_qhdm(date);
            batchQueueRabbitTemplate.convertAndSend("placeData",scanRecord);

            i++;
        }
    }

}
