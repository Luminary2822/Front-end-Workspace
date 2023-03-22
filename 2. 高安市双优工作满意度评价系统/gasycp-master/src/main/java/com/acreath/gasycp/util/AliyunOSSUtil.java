package com.acreath.gasycp.util;

/**
 * @author: longteng
 * @date: 2018/11/17 22:27
 * @description:
 */

import com.acreath.gasycp.config.ConstantProperties;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class AliyunOSSUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);


    public static List<String> upload(File file){
        logger.info("=========>OSS文件上传开始："+file.getName());
        String endpoint=ConstantProperties.JAVA4ALL_END_POINT;
        String accessKeyId= ConstantProperties.JAVA4ALL_ACCESS_KEY_ID;
        String accessKeySecret=ConstantProperties.JAVA4ALL_ACCESS_KEY_SECRET;
        String bucketName=ConstantProperties.JAVA4ALL_BUCKET_NAME1;
        String fileHost=ConstantProperties.JAVA4ALL_FILE_HOST;
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        List<String> outType  = new ArrayList<>();
        outType.add(fileName);
        outType.add(suffix);
        if(null == file){
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if(null != result){
                logger.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                if ("png".equals(suffix)||"jpg".equals(suffix)||"gif".equals(suffix)||"pdf".equals(suffix))
                {
                   outType.add("https://gassycp.oss-cn-beijing.aliyuncs.com/"+fileUrl);
                   return outType;
                }
                outType.add(fileUrl);
                return outType;
            }
        }catch (OSSException oe){
            logger.error(oe.getMessage());
        }catch (ClientException ce){
            logger.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
}
