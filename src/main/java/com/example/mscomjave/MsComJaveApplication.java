package com.example.mscomjave;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;

import java.io.File;


public class MsComJaveApplication {

    public static void main(String[] args) {
        // 压缩前文件路径
        File source = new File("F:/others/jave/original/001.mp4");
        // 压缩后的文件路径
        File target = new File("F:/others/jave/target/001.mp4");

        try {
            long start = System.currentTimeMillis();
            System.out.println("begin");

            // 音频编码属性配置
            AudioAttributes audio= new AudioAttributes();
            audio.setCodec("libmp3lame");
            // 比特率越高，清晰度/音质越好
            audio.setBitRate(new Integer(56000));//设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 56000 = 56kb)
            audio.setChannels(new Integer(1));// 设置重新编码的音频流中使用的声道数（1 =单声道，2 = 双声道（立体声））。如果未设置任何声道值，则编码器将选择默认值 0。
            // 采样率越高声音的还原度越好，文件越大
            audio.setSamplingRate(new Integer(44100));//设置音频采样率，单位：赫兹 hz
            // 设置编码时候的音量值，未设置为0,如果256，则音量值不会改变
            //  audio.setVolume();

            // 视频编码属性配置
            VideoAttributes video=new VideoAttributes();
            video.setCodec("mpeg4");
            // 比特率越高，清晰度/音质越好
            video.setBitRate(new Integer(56000));//设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 5600000 = 5600kb)
            // 视频帧率：15 f / s  帧率越低，效果越差
            video.setFrameRate(new Integer(15));// 设置视频帧率（帧率越低，视频会出现断层，越高让人感觉越连续），视频帧率（Frame rate）是用于测量显示帧数的量度。所谓的测量单位为每秒显示帧数(Frames per Second，简：FPS）或“赫兹”（Hz）。

            // 编码设置
            EncodingAttributes attr=new EncodingAttributes();
            attr.setFormat("mp4");
            attr.setAudioAttributes(audio);
            attr.setVideoAttributes(video);

            // 设置值编码
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attr);


            System.out.println("end");
            long end = System.currentTimeMillis();

            System.out.println("压缩前大小： "+source.length() + " 压缩后大小：" + target.length());
            System.out.println("压缩耗时： " + (end -start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
