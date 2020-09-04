
package com.hzq.dubbo.audio;


import javax.sound.sampled.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/28 13:56
 */
public class AudioMatch {
    private static int sampleRateInHz = 41000;

    public static void main(String[] args) {
        try {
            getFftData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAudio() throws LineUnavailableException {
        AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        AudioFormat format = getFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);

        Thread thread = new Thread(() -> {
            FileOutputStream fo = null;
            long totalAudioLen = 0;
            long totalDataLen = totalAudioLen + 36;
            long longSampleRate = sampleRateInHz;
            int channels = 1;
            long byteRate = 16 * sampleRateInHz * channels / 8;
//            byte[] data = new byte[mRecordBufferSize];
            try {
                line.open(format);
                line.start();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                boolean running = true;
                byte[] buffer = new byte[1024];

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 10);
                while (running) {
                    int count = line.read(buffer, 0, buffer.length);
                    if (count > 0) {
                        out.write(buffer, 0, count);
                    }
                    Calendar calendar1 = Calendar.getInstance();
                    System.out.println(calendar.getTimeInMillis() - calendar1.getTimeInMillis());
                    if (calendar1.compareTo(calendar) > 0) {
                        break;
                    }

                }
                byte[] bytes = out.toByteArray();

//            FileOutputStream fileOutputStream = new FileOutputStream("D:\\tmp\\myaudio.wav");
//            AudioSystem.write(new AudioInputStream(line), fileType, fileOutputStream);
//            fileOutputStream.write(bytes);

                totalAudioLen = bytes.length;
                totalDataLen = totalAudioLen + 36;
                fo = new FileOutputStream("D:\\tmp\\myaudio.mp3");

                //添加头部信息
                writeWaveFileHeader(fo, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
                fo.write(bytes);

                out.close();
//            fileOutputStream.close();
                fo.close();
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        /*Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        while(true){
            Calendar calendar1 = Calendar.getInstance();
            System.out.println(calendar.getTimeInMillis() - calendar1.getTimeInMillis());
            if (calendar1.compareTo(calendar) > 0) {
                line.stop();
                line.close();
                break;
            }
        }*/

    }

    public static Complex[][] getFftData() throws IOException {
        byte[] audio = audioFile();
        final int totalSize = audio.length;

        int amountPossible = totalSize / 4;

        //When turning into frequency domain we'll need complex numbers:
        Complex[][] results = new Complex[amountPossible][];

        //For all the chunks:
        for (int times = 0; times < amountPossible; times++) {
            Complex[] complex = new Complex[4];
            for (int i = 0; i < 4; i++) {
                //Put the time domain data into a complex number with imaginary part as 0:
                complex[i] = new Complex(audio[(times * 4) + i], 0);
            }
            //Perform FFT analysis on the chunk:
            results[times] = FFT.fft(complex);
        }

        return results;
    }

    public static void writeWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                           long totalDataLen, long longSampleRate,
                                           int channels, long byteRate) throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16;
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1;
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

    public static AudioFormat getFormat() {
        float sampleRate = 41000;
        int sampleSizeInBits = 16;
        int channels = 1; //mono
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static byte[] audioFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\test\\myaudio_copy.mp3");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean running = true;
        byte[] buffer = new byte[1024];

        while (running) {
            int count = fileInputStream.read(buffer, 0, buffer.length);
            if (count > 0) {
                out.write(buffer, 0, count);
            } else {
                running = false;
            }
        }
        byte[] bytes = out.toByteArray();
        return bytes;
    }
}
