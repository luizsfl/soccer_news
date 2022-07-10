package pedroluiz.projeto.soccernews.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pedroluiz.projeto.soccernews.databinding.NewsItemBinding;
import pedroluiz.projeto.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> news;
    public List<News> getNews() {
        return news;
    }

    public NewsAdapter(List<News> news) {
        this.news = news;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.news.get(position);
        holder.binding.tvTitle.setText(this.news.get(position).getTitle());
        holder.binding.tvDescicao.setText(this.news.get(position).getDescricao());

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;
        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
