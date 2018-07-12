package cn.edu.swpu.kotlintest.tools;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import cn.edu.swpu.kotlintest.entity.Webdata;

public class MyService extends Service {
    private IntentFilter intentFilter;
    private SendMessage sendMessage;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //广播监听器，监听来自MainActivity的广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("SENDMESSAGE");//获得实例，并添加内容
        sendMessage = new SendMessage();
        registerReceiver(sendMessage,intentFilter);
        //开启线程，获取网页数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent receivemsg;
                    ArrayList<Webdata> webdatas = new ArrayList<Webdata>();
                    String url = "http://www.swpu.edu.cn/dean/xwbd.htm";
                    Document document = Jsoup.connect(url).get();
                    Elements titleElements = document.select(".r_list li");
                    for (Element element : titleElements) {
                        String title = element.select("a").first().text();
                        String time = element.select(".time").first().text();
                        String URL = element.select("a").first().attr("abs:href").toString();
                        Document document1 = Jsoup.connect(URL).get();
                        String imageurl = document1.select("img").attr("abs:src").toString();
                        String nexturl = document.select(".Next").first().attr("abs:href").toString();
                        Webdata webdata = new Webdata(URL, imageurl, nexturl, title, time);
                        webdatas.add(webdata);
                    }
//                    list.add(webdatas);
                    receivemsg = new Intent("RECEIVEMSG");
                    receivemsg.putExtra("data",webdatas);
                    sendBroadcast(receivemsg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sendMessage);
    }
    class SendMessage extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, final Intent intent) {
                //开启线程，获取网页数据
                new Thread(new Runnable() {
                    String url = intent.getExtras().getString("nextpage");
                    @Override
                    public void run() {
                        try {
                            Intent receivemsg;
                            ArrayList<Webdata> webdatas = new ArrayList<Webdata>();
                            Document document = Jsoup.connect(url).get();
                            Elements titleElements = document.select(".r_list li");
                            for (int i = 4;i < 29;i++) {
                                Element element = titleElements.get(i);
                                String title = element.select("a").first().text();
                                String time = element.select(".time").first().text();
                                String URL = element.select("a").first().attr("abs:href").toString();
                                Document document1 = Jsoup.connect(URL).get();
                                String imageurl = document1.select("img").attr("abs:src").toString();
                                String nexturl = document.select(".Next").first().attr("abs:href").toString();
                                Webdata webdata = new Webdata(URL, imageurl, nexturl, title, time);
                                webdatas.add(webdata);
                            }
                            receivemsg = new Intent("RECEIVEMSG");
                            receivemsg.putExtra("data",webdatas);
                            sendBroadcast(receivemsg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
        }
    }

}
