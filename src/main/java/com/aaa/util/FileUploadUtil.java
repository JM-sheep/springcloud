package com.aaa.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtil {
    /**
     * 根据时间获取新文件名
     * @return
     */
    private static String toData(String filename){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmms");
        String newName = simpleDateFormat.format(new Date());
        String newFileName = newName+ filename.substring(filename.lastIndexOf("."));
        return newFileName;
    }

    /**
     * 根据UUID获取新文件名
     * @return
     */
    private static String toUUID(String filename){
        //通过UUID获取到一个唯一标识
        String name = UUID.randomUUID().toString().toLowerCase();
        //拼接上文件名的后缀名获取到一个新的文件名
        String newFileName = name + filename.substring(filename.lastIndexOf("."));
        return newFileName;
    }

    /**
     *
     * @param multipartFile 要上传的文件对象
     * @param targetPath    要存放文件的目标目录的绝对路径
     * @return  上传后新的文件名字
     * @throws IOException
     */
    public static String upload(MultipartFile multipartFile,String targetPath) throws IOException {
        //如果文档不为空
        if (!multipartFile.isEmpty()){
            //获取到存放文件目标目录的绝对路径
            String path = targetPath;
            //获取目标文件名
            String filename = multipartFile.getOriginalFilename();
            //获取新的文件名
            String newFileName = FileUploadUtil.toUUID(filename);
            //创建目标文件的对象
            File file = new File(path,newFileName);
            //如果是第一次上传，可能没有目标目录
            if (!file.getParentFile().exists()){
                //创建对应的目录
                file.mkdirs();
            }
            //上传数据
            multipartFile.transferTo(file);
            //返回新文件的名称
            return newFileName;
        }
        return null;
    }
}
