package com.trivagonytimes.ui.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trivagonytimes.R;
import com.trivagonytimes.model.Results;
import com.trivagonytimes.util.DateUtil;

import java.util.List;


public class MostViewedArticleAdapter extends RecyclerView.Adapter<MostViewedArticleAdapter.MostViewedViewHolder> {

    private List<Results> itemList;
    private Context context;

    public MostViewedArticleAdapter(Context context, List<Results> itemList) {
        this.itemList = itemList;
        this.context = context;

    }

    public void notifyData(List<Results> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public MostViewedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view_row, null);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(layoutView);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(MostViewedViewHolder holder, int position) {
        Results doc = getItem(position);
        holder.snippet.setText(doc.getAbstractArticle());
        String author = doc.getByline();
        if (TextUtils.isEmpty(author)) {
            author = "";
        }
        String date = "";
        if (!TextUtils.isEmpty(doc.getPublished_date())) {
            date =  DateUtil.parseDate(doc.getPublished_date(),DateUtil.FORMAT2);
        }
        holder.author.setText(date+" "+author);
        holder.headline.setText(doc.getTitle());
        if(doc.getMedia()!=null) {
            Picasso.with(context).load(doc.getMedia().getUrl()).placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon).into(holder.thumbnailIv);
        }else{
            //Load a default url placeholder icon because sometimes piccasso loads wrong
            // images while scrolling back and forth
            Picasso.with(context).load("default").placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon).into(holder.thumbnailIv);
        }


    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public Results getItem(int position) {
        return itemList.get(position);
    }


    class MostViewedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView thumbnailIv;
        public TextView snippet;
        public TextView headline;
        public TextView author;


        public View divider;

        public MostViewedViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            thumbnailIv = (ImageView) itemView.findViewById(R.id.article_photo);
            snippet = (TextView) itemView.findViewById(R.id.snippet);
            headline = (TextView) itemView.findViewById(R.id.name);
            author = (TextView) itemView.findViewById(R.id.author);


        }

        @Override
        public void onClick(View view) {


        }


    }
}