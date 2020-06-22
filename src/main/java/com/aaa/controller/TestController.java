package com.aaa.controller;

import com.aaa.entity.Dept;
import com.aaa.entity.Files;
import com.aaa.service.DeptService;
import com.aaa.util.FileUploadUtil;
import netscape.security.UserTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("addFile")
    public String addFile(Map map, Files files, @RequestParam("head") MultipartFile head, HttpServletRequest request) throws IOException {
        //获取文件的绝对路径
        String path = request.getServletContext().getRealPath("/upload/");
        //上传文件
        String filename = FileUploadUtil.upload(head,path);
        //将修改后的文件名作为字符串存入files对象
        files.setFname(filename);
        //将files对象添加到数据库
        deptService.insertFile(files);
        //返回给前端的修改后的文件名
        map.put("filename",filename);
        return "false";
    }

    @RequestMapping("/addFiles")
    public String addFiles(Files files, @RequestParam("head")MultipartFile[] head, HttpServletRequest request) throws IOException {
        for (int i=0;i<head.length;i++){
            String path = request.getServletContext().getRealPath("/upload/");
            String filename = FileUploadUtil.upload(head[i],path);
            files.setFname(filename);
            deptService.insertFile(files);
        }
        return "false";
    }
}
