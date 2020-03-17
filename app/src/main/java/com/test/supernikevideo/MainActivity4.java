package com.test.supernikevideo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lws.supernikevideo.IjkOption;
import com.lws.supernikevideo.NiceVideoPlayer;
import com.lws.supernikevideo.NiceVideoPlayerManager;


import java.util.ArrayList;
import java.util.List;


import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity4 extends AppCompatActivity {

    NiceVideoPlayer mNiceVideoPlayer;


    String picUrl = "http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%AB%98%E6%B8%85&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2334973458,137261168&os=3870822471,57480039&simid=4208509416,754054375&pn=3&rn=1&di=59510&ln=1699&fr=&fmq=1584327571494_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fwww.sydiaocha.cn%2Fimg.php%3Fwww.005.tv%2Fuploads%2Fallimg%2F160502%2F12-160502212301611.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined";
    String test = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
    String rtsp = "rtsp://122.226.233.206:655/EUrl/TXFJ2c8";
    String testRtmp = "rtmp://58.200.131.2:1935/livetv/hunantv";
    String rtmp = "rtmp://192.168.6.120:10085/hls/gbs013402000000132000000234020000001310000002?sign=OolGbvuZg";
    String videoUrl = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        init();
    }

    private void init() {

        //加载native库
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        mNiceVideoPlayer = (NiceVideoPlayer) findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // or NiceVideoPlayer.TYPE_NATIVE
        mNiceVideoPlayer.setMediaType(NiceVideoPlayer.MEDIA_TYPE_LIVE);
        mNiceVideoPlayer.setUp(testRtmp, null);

        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("这是一个标题");
//        controller.setImage(mImageUrl);
//        controller.setClarity(getClarites(), 1);    // 设置清晰度以及默认播放的清晰度
//        Glide.with(this)
//                .load(picUrl)
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(controller.imageView());
        setOption();
        mNiceVideoPlayer.setController(controller);
    }

    private void setOption() {

        mNiceVideoPlayer.setOpenDebugLog(true);//打开日志
        List<IjkOption> list = new ArrayList<>();
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"mediacodec",(long)1));
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", (long)1)); //开启mediacodec硬解
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", (long)5)); //丢帧  是在视频帧处理不过来的时候丢弃一些帧达到同步的效果
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", (long)0)); //设置是否开启环路过滤: 0开启，画面质量高，解码开销大，48关闭，画面质量差点，解码开销小
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "tcp")); //如果是rtsp协议，可以优先用tcp(默认是用udp)
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", (long)100L));
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", (long)1L));
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", (long)1)); //需要准备好后自动播放
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "fast", (long)1));//不额外优化
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", (long)0)); //是否开启预缓冲，一般直播项目会开启，达到秒开的效果，不过带来了播放丢帧卡顿的体验
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", (long)0));//自动旋屏
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", (long)0));  //处理分辨率变化
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "max-buffer-size", (long)0)); //最大缓冲大小,单位kb
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", (long)2));//默认最小帧数2
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max_cached_duration", (long)3));//最大缓存时长
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "infbuf", (long)1));//是否限制输入缓存数
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer"));
        list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "reconnect", (long)5));//播放重连次数
//                     list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", (long)1));//设置播放前的探测时间 1,达到首屏秒开效果
//                     list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", (long)20));//播放前的探测Size，默认是1M, 改小一点会出画面更快
//                     list.add(new IjkOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", (long)0));//关闭mediacodec硬解，使用软解
        mNiceVideoPlayer.setIjkOptionArrayList(list);
    }

    public List<Clarity> getClarites() {
        List<Clarity> clarities = new ArrayList<>();
        clarities.add(new Clarity("标清", "270P", "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("高清", "480P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("超清", "720P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        clarities.add(new Clarity("蓝光", "1080P", "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"));
        return clarities;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        IjkMediaPlayer.native_profileEnd();
    }
}
