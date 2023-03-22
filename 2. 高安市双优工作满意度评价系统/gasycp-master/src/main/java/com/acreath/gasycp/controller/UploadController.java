package com.acreath.gasycp.controller;

import com.acreath.gasycp.dto.OrgFilesDTO;
import com.acreath.gasycp.mapper.OrgFilesMapper;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.util.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/17 22:30
 * @description:
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 文件上传
     * @param file
     */
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private OrgFilesMapper orgFilesMapper;
    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/uploadBlog",method = RequestMethod.POST)
    @ResponseBody
    public String uploadBlog(MultipartFile file, HttpServletRequest request){
        HttpSession session = request.getSession();
        logger.info("============>文件上传");
        try {

            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
                    List<String> outType = AliyunOSSUtil.upload(newFile);
                    if (!("png".equals(outType.get(1))||"jpg".equals(outType.get(1))||"gif".equals(outType.get(1))||"pdf".equals(outType.get(1)))){
                        String outputUrl = AliIMMUtil.docConvertDemo(outType.get(2));
                        redisClient.set("url"+session.getAttribute("org_id"),outputUrl);
                        redisClient.set("fileName"+session.getAttribute("org_id"),outType.get(0));

                    }
                    else {
                        redisClient.set("url"+session.getAttribute("org_id"), outType.get(2));
                        redisClient.set("fileName"+session.getAttribute("org_id"),outType.get(0));
                    }

                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResultBuilderUtils.buildSuccess("上传成功");
    }

    @RequestMapping(value = "toUploadBlog",method = RequestMethod.GET)
    public String toUploadBlog(){
        return "upload";
    }


    @RequestMapping(value = "/uploadData" ,method = RequestMethod.POST)
    @ResponseBody
    public String  upload(@RequestBody String payload,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        System.out.println(FastJsonUtil.toJSONString(payload));
        OrgFilesDTO orgFilesDTO = FastJsonUtil.toBean(payload,OrgFilesDTO.class);
        int typeOneFile = orgFilesMapper.contFilesByOrgIdAndType((int)session.getAttribute("org_id"),orgFilesDTO.getOneType());
        if (typeOneFile!=0){
            return ResultBuilderUtils.buildError("此模块已有文件");
        }

        orgFilesDTO.setOrgId((int)session.getAttribute("org_id"));
        orgFilesDTO.setCreateAt(new Date());
        orgFilesDTO.setFileUrl(redisClient.get("url"+session.getAttribute("org_id")));
        orgFilesDTO.setFileName(redisClient.get("fileName"+session.getAttribute("org_id")));
        orgFilesMapper.createFile(orgFilesDTO);
        switch (orgFilesDTO.getOneType()) {
            case "TYPE_ONE_MODULE1":
                orgMapper.upDataModule1FileStatus(1,orgFilesDTO.getOrgId());
                break;
            case "TYPE_ONE_MODULE2":
                orgMapper.upDataModule2FileStatus(1,orgFilesDTO.getOrgId());
                break;
            case "TYPE_ONE_MODULE3":
                orgMapper.upDataModule3FileStatus(1,orgFilesDTO.getOrgId());
                break;
            case "TYPE_ONE_MODULE4":
                orgMapper.upDataModule4FileStatus(1,orgFilesDTO.getOrgId());
                break;
            case "TYPE_ONE_MODULE5":
                orgMapper.upDataModule5FileStatus(1,orgFilesDTO.getOrgId());
                break;
            case "TYPE_ONE_MODULE6":
                orgMapper.upDataModule6FileStatus(1,orgFilesDTO.getOrgId());
                break;
            default:
                break;
        }
        if (orgFilesMapper.contFilesByOrgId((int)session.getAttribute("org_id"))==6)
        {
            orgMapper.upDataFileStatus(1,orgFilesDTO.getOrgId());
        }
        return ResultBuilderUtils.buildSuccess("上传成功");
    }
}
