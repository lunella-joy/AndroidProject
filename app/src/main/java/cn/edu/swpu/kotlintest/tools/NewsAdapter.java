package cn.edu.swpu.kotlintest.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.swpu.kotlintest.R;
import cn.edu.swpu.kotlintest.activity.NewsPageActivity;
import cn.edu.swpu.kotlintest.entity.Webdata;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    private List<Webdata> mwebdata;
    private int load_more_status;
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    //没有更多的信息了
    public static final int No_More_Message=2;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView newstitle;
        TextView newstime;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            newstitle = (TextView) itemView.findViewById(R.id.newstitle);
            newstime = (TextView) itemView.findViewById(R.id.newstime);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public NewsAdapter(List<Webdata> webdata){
        mwebdata = webdata;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext == null){
            mcontext = parent.getContext();
        }
        if (viewType == 0){
            View footerview = LayoutInflater.from(mcontext).inflate(R.layout.footer_view,parent,false);
            return new FooterHolder(footerview);
        }else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_item_view, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterHolder) {
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    ((FooterHolder) holder).textView.setText("上拉加载更多");
                    break;
                case LOADING_MORE:
                    ((FooterHolder) holder).textView.setText("正在加载更多");
                    break;
                case No_More_Message:
                    ((FooterHolder) holder).textView.setText("全部加载完成");
                    break;
            }
        }else if (holder instanceof ViewHolder) {
            final Webdata webdata = mwebdata.get(position);
            ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.newstitle.setText(webdata.getTitle());
            viewHolder.newstime.setText(webdata.getDate());
            if (webdata.getPictureurl().isEmpty()) {
                Glide.with(mcontext).load("http://www.ahlushang.com/myweb/case_pic/050428215706.jpg").into(viewHolder.imageView);
            } else {
                Glide.with(mcontext).load(webdata.getPictureurl()).into(viewHolder.imageView);
            }
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tiaozhuan = new Intent(view.getContext(),NewsPageActivity.class);
                    tiaozhuan.putExtra("url",webdata.getThistitleurl());
                    view.getContext().startActivity(tiaozhuan);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mwebdata.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1 == getItemCount()){
            return 0;
        }
        return 1;
    }

    public class FooterHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public FooterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.footertext);
        }
    }

    public void changeMoreStatus(int status){
        load_more_status=status;
    }
}
