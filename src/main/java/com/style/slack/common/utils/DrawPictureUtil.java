package com.style.slack.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;

/**
 * 图片工具类
 * @author  Gaofei  2018-07-31
 */
public class DrawPictureUtil {

    public static File changePic(String nickName,String headImage, File fileZipQrCode){
        File file = null;
        try {
            file = File.createTempFile("weixinbg", ".jpg");
            //系统路径
            String systemPath = System.getProperty("java.io.tmpdir");
            //读取背景图
            ClassPathResource classPathResource = new ClassPathResource("/static/images/myticketbg.jpg");
            InputStream inputStream = classPathResource.getInputStream();
            //生成图片临时文件
            FileUtils.copyInputStreamToFile(inputStream, file);

            //添加二维码
            //压缩图片大小
            NewImageUtils.ImgCompress(fileZipQrCode, 250, 250, 0.7f);
            NewImageUtils.watermark(file, fileZipQrCode, 250, 780, 1.0f);


            //添加文字
            String text = "我是"+nickName;
            NewImageUtils.waterMarkByText(text,file,330,125,30,0.8f);


           // String saveWxPath = "F://wxhead.jpg";
            File headImgFile = FileDownloadUtil.downloadNet(headImage);

            //压缩图片大小
            //String zipWxhead = NewImageUtils.zipImageFile(saveWxPath, 65, 65, 1f, "x2");
            NewImageUtils.ImgCompress(headImgFile, 65, 65, 0.7f);
            NewImageUtils.watermark(file, headImgFile, 570, 130, 1.0f);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return file;
    }
}
