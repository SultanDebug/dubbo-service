/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.opencv;

import com.sun.media.sound.FFT;

import javax.sound.sampled.*;
import java.io.*;

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

    public static void main(String[] args){
        try {
            audioFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fft(byte audio[]){

        /*final int totalSize = audio.length;

        int amountPossible = totalSize/Harvester.CHUNK_SIZE;

        //When turning into frequency domain we'll need complex numbers:
        Complex[][] results = new Complex[amountPossible][];

        //For all the chunks:
        for(int times = 0;times < amountPossible; times++) {
            Complex[] complex = new Complex[Harvester.CHUNK_SIZE];
            for(int i = 0;i < Harvester.CHUNK_SIZE;i++) {
                //Put the time domain data into a complex number with imaginary part as 0:
                complex[i] = new Complex(audio[(times*Harvester.CHUNK_SIZE)+i], 0);
            }
            //Perform FFT analysis on the chunk:
            results[times] = FFT.fft(complex);
        }*/
    }

    public static void audioFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\software\\1-20K.mp3");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean running = true;
        byte[] buffer = new byte[1024];

        while (running) {
            int count = fileInputStream.read(buffer, 0, buffer.length);
            if (count > 0) {
                out.write(buffer, 0, count);
            }else{
                running = false;
            }
        }
        byte[] bytes = out.toByteArray();
        System.out.println(bytes);
    }

    public static void medianBlur(){
        /*Mat img = Imgcodecs.imread("F:\\test\\test.png");

        //中值滤波将图像的每个像素用邻域 (以当前像素为中心的正方形区域)像素的 中值 代替
        //图像平滑处理：中值滤波：输入、输出、基数
        Imgproc.medianBlur(img, img, 7);

        Imgcodecs.imwrite("F:\\test\\test1.png",img);

        img.release();*/
    }

    public static void highGUI(){
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

    public static void getAudio(){
        try {
            AudioFormat format = getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean running = true;
            byte[] buffer = new byte[1024];

            while (running) {
                int count = line.read(buffer, 0, buffer.length);
                if (count > 0) {
                    out.write(buffer, 0, count);
                }else{
                    running = false;
                }
            }
            byte[] bytes = out.toByteArray();

            FileOutputStream fileOutputStream = new FileOutputStream("F:\\test\\myaudio.mp3");
            fileOutputStream.write(bytes);

            out.close();
            fileOutputStream.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static AudioFormat getFormat() {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1; //mono
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
}
