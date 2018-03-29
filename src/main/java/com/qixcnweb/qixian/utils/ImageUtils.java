package com.qixcnweb.qixian.utils;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.Random;

/**
 * 图片工具类
 * Created by dingxiaochi on 2018/3/27.
 */
@Component
public class ImageUtils {

    /**
     * 裁剪图片
     * @param imageInputStream   目标图片出入流
     * @param fileName    目标图片名称后缀
     * @param x     裁剪点X坐标
     * @param y     裁剪点Y坐标
     * @param width 裁剪宽
     * @param height 裁剪高
     */
    public File cutImage(InputStream imageInputStream,String fileName,Integer x,Integer y,Integer width,Integer height){

        FileInputStream is =  null;
        ImageInputStream iis = null;


        File resultFile = new File("F://"+fileName);

        try {
            // 读取图片文件
            is = (FileInputStream) imageInputStream;

            /*
            * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
            * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
            *（例如 "jpeg" 或 "tiff"）等 。
            */
            //得到文件后缀
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
            Iterator<ImageReader> it= ImageIO.getImageReadersByFormatName(fileSuffix);
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);

            /*
            * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索'。
            * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
            * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
            */
            reader.setInput(iis, true ) ;

            /*
            * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
            * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
            * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
            * ImageReadParam 的实例。
            */
            ImageReadParam param = reader.getDefaultReadParam();

            /*
            * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
            * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
            */
            Rectangle rect =  new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /*
            * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
            * 它作为一个完整的 BufferedImage 返回。
             */
            BufferedImage bi=reader.read(0,param);

            // 保存新图片
            //todo:有没有更好的办法不需要写到临时文件.直接写到数据流中
            ImageIO.write(bi,fileSuffix,resultFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null )
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (iis != null )
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return resultFile;
    }

    /**
     * 得到文件的后缀
     * @return
     */
    public String getFileSuffix(String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return fileSuffix;
    }

    /**
     * 得到一个随机的数字字符串作为新的图片名称
     * @return
     */
    public String randomImageName(){
        Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(1000));
        String timeStap = String.valueOf(System.currentTimeMillis());
        return randomNumber+timeStap;
    }

    /**
     * 将原来的文件名替换为一个随机的数字字符串
     * @param originFileName        //原始文件名
     * @return
     */
    public String createImgName(String originFileName){
        //获得文件后缀
        String fileSuffix = getFileSuffix(originFileName);
        //获得一个随机的数字字符串
        String randomNumber = randomImageName();
        //返回新的文件名
        return randomNumber+fileSuffix;

    }

}
