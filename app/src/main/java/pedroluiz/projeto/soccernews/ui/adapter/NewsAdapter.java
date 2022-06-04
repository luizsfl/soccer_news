package pedroluiz.projeto.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pedroluiz.projeto.soccernews.databinding.NewsItemBinding;
import pedroluiz.projeto.soccernews.domain.News;
import retrofit2.http.Url;

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
        holder.binding.tvDescicao.setText(this.news.get(position).getDescription());

        //Incluir imagem
        Picasso.get().load(this.news.get(position).getImage()).fit().into(holder.binding.ivNoticia);

        holder.binding.btOpenLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(this.news.get(position).getLink()));

            holder.itemView.getContext().startActivity(intent);

        });

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
