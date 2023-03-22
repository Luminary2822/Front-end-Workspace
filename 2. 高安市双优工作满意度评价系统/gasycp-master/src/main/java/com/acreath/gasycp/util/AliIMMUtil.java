package com.acreath.gasycp.util;

import com.acreath.gasycp.config.ConstantProperties;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.imm.main.IMMClient;
import com.aliyuncs.imm.model.v20170906.CreateOfficeConversionTaskRequest;
import com.aliyuncs.imm.model.v20170906.CreateOfficeConversionTaskResponse;
import com.aliyuncs.imm.model.v20170906.GetOfficeConversionTaskRequest;
import com.aliyuncs.imm.model.v20170906.GetOfficeConversionTaskResponse;

import java.util.UUID;

/**
 * @author: longteng
 * @date: 2018/11/18 19:45
 * @description:
 */
public class AliIMMUtil {
    static IMMClient client = new IMMClient("cn-beijing", ConstantProperties.JAVA4ALL_ACCESS_KEY_ID,ConstantProperties.JAVA4ALL_ACCESS_KEY_SECRET);
    // 项目名称，请确保该项目已经创建
    static String projectName = "gassycpTest";

    public static String docConvertDemo(String fileUrl) throws ClientException {
        // 创建文档转换异步请求任务
        CreateOfficeConversionTaskRequest req = new CreateOfficeConversionTaskRequest();
        req.setProject(projectName);
        // 设置待转换对文件OSS路径
        req.setSrcUri("oss://gassycp/"+fileUrl);
        System.out.println("oss://gassycp/"+fileUrl);
        // 设置文件输出格式为 vector
        req.setTgtType("pdf");
        // 设置转换后的输出路径
        String uuid = UUID.randomUUID().toString().replace("-","");
        req.setTgtUri("oss://gassycp/fileTest"+uuid);
        String outPutUrl = "https://gassycp.oss-cn-beijing.aliyuncs.com/fileTest"+uuid+"/1.pdf";

        CreateOfficeConversionTaskResponse res = client.getResponse(req);
        String taskId = res.getTaskId();
        // 获取文档转换任务结果，最多轮询 30 次
        // 每次轮询的间隔为 1 秒
        GetOfficeConversionTaskRequest getOfficeConversionTaskRequest = new GetOfficeConversionTaskRequest();
        getOfficeConversionTaskRequest.setProject(projectName);
        getOfficeConversionTaskRequest.setTaskId(taskId);
        int maxCount = 30;
        int count = 0;
        try {
            while (true) {
                Thread.sleep(1000); // 1 秒
                GetOfficeConversionTaskResponse getOfficeConversionTaskResponse = client.getResponse(getOfficeConversionTaskRequest);
                if (getOfficeConversionTaskResponse.getStatus() != "Running") {
                    // 输出文档转换任务执行结果
                    System.out.println(getOfficeConversionTaskResponse.getTaskId());
                    System.out.println(getOfficeConversionTaskResponse.getFailDetail().getCode());
                    System.out.println("Done");
                    break;
                }
                count = count + 1;
                if(count >= maxCount) {
                    System.out.println("OfficeConversion Timeout for 30 seconds");
                    break;
                }
                System.out.println("Task is still running.");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return outPutUrl;
    }
}
