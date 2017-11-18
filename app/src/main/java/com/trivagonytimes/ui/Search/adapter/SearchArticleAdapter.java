package com.trivagonytimes.ui.Search.adapter;

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
import com.trivagonytimes.model.Doc;
import com.trivagonytimes.model.Multimedium;
import com.trivagonytimes.repository.RetrofitClient;
import com.trivagonytimes.util.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class SearchArticleAdapter extends RecyclerView.Adapter<SearchArticleAdapter.SearchArticleViewHolder> {

    private List<Doc> articleList;
    private Context context;

    public SearchArticleAdapter(Context context, List<Doc> itemList) {
        this.articleList = itemList;
        this.context = context;

    }

    public void notifyData(List<Doc> articles){
        articleList.clear();
        articleList.addAll(articles);
        notifyDataSetChanged();
    }
    public void clearArticles() {
        articleList.clear();
        notifyItemRangeRemoved(0, getItemCount());
    }

    @Override
    public SearchArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view_row, null);
        SearchArticleViewHolder searchArticleViewHolder = new SearchArticleViewHolder(layoutView);
        return searchArticleViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchArticleViewHolder holder, int position) {
        Doc doc = articleList.get(position);
        holder.snippet.setText(doc.getSnippet());
        String author ="";
        if(doc.getByline()!=null) {
            if (!TextUtils.isEmpty(doc.getByline().getOriginal())) {
                author = doc.getByline().getOriginal();
            }
        }
        String date = "";
        if (!TextUtils.isEmpty(doc.getPubDate())) {
           date =  DateUtil.parseDate(doc.getPubDate(),DateUtil.FORMAT1);
        }
        holder.author.setText(date+" "+author);
        holder.headline.setText(doc.getHeadline().getMain());
        ArrayList<Multimedium> multimedia = (ArrayList<Multimedium>) doc.getMultimedia();
        String thumbUrl = "";
        if(multimedia!=null && multimedia.size()>0){
            thumbUrl = RetrofitClient.API_IMAGE_BASE_URL + multimedia.get(0).getUrl();
        }

        if (!TextUtils.isEmpty(thumbUrl)) {
            holder.thumbnailIv.setVisibility(View.VISIBLE);
            Picasso.with(context).load(thumbUrl).into(holder.thumbnailIv);
        } else {
            holder.thumbnailIv.setVisibility(View.GONE);
        }




    }



    @Override
    public int getItemCount() {
        return this.articleList.size();
    }

    public Doc getItem(int position) {
        return articleList.get(position);
    }


    class SearchArticleViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnailIv;
        public TextView snippet;
        public TextView headline;
        public TextView author;


        public View divider;

        public SearchArticleViewHolder(View itemView) {
            super(itemView);
            thumbnailIv = (ImageView) itemView.findViewById(R.id.article_photo);
            snippet = (TextView) itemView.findViewById(R.id.snippet);
            headline = (TextView) itemView.findViewById(R.id.name);
            author = (TextView) itemView.findViewById(R.id.author);
        }




    }
}