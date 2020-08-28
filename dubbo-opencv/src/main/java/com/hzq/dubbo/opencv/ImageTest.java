/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.opencv;

import com.sun.media.sound.FFT;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Calendar;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/27 12:54
 */
public class ImageTest {

    /*static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
    }*/

    public static void main(String[] args) {
        /*try {
            audioFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


    public static void medianBlur() {
        /*Mat img = Imgcodecs.imread("F:\\test\\test.png");

        //中值滤波将图像的每个像素用邻域 (以当前像素为中心的正方形区域)像素的 中值 代替
        //图像平滑处理：中值滤波：输入、输出、基数
        Imgproc.medianBlur(img, img, 7);

        Imgcodecs.imwrite("F:\\test\\test1.png",img);

        img.release();*/
    }

    public static void highGUI() {
        /*String fileName = "F:\\test\\test.png"; //设置图片的路径
        if (!new File(fileName).exists()){
            System.out.println("文件不存在");
        }else{

            Mat srcImg = imread(fileName);  //opencv读取
            if (srcImg.empty()){
                System.out.println("加载图片失败！");
            }else{
                HighGui.imshow("image",srcImg); //显示
                HighGui.waitKey(0);
            }
        }*/
    }

    /*public static void getAudio2() {
        // 指定的文件类型
        AudioFileFormat.Type fileType = null;
// 设置文件类型和文件扩展名
        fileType = AudioFileFormat.Type.WAVE;
        try {
            AudioFormat format = getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
// format - 所需音频格式
            line.open(format);
// 当开始音频捕获或回放时，生成 START 事件。
            line.start();
// new AudioInputStream(TargetDataLine line):构造从指示的目标数据行读取数据的音频输入流。该流的格式与目标数据行的格式相同,line - 此流从中获得数据的目标数据行。
// stream - 包含要写入文件的音频数据的音频输入流
// fileType - 要写入的音频文件的种类
// out - 应将文件数据写入其中的外部文件
            AudioSystem.write(new AudioInputStream(line), fileType, new File("D://ss.wav"));
//AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(bt),audioFormat,bt.length / audioFormat.getFrameSize()),fileType,"new File("D://ss.wav")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
