package com.acreath.gasycp.mapper;

import com.acreath.gasycp.dto.OrgFilesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/18 19:34
 * @description:
 */

@Mapper
public interface OrgFilesMapper {
    void createFile (OrgFilesDTO orgFilesDTO);
   List<OrgFilesDTO> getOrgFilesByOrgId(@Param("orgId") int orgId );
   void deletedFile(@Param("id") int id);
   Integer contFilesByOrgIdAndType(@Param("orgId") int orgId,@Param("oneType") String oneType);
   Integer contFilesByOrgId(@Param("orgId") int orgId);
  OrgFilesDTO  getFileById (@Param("id") int id);
}
