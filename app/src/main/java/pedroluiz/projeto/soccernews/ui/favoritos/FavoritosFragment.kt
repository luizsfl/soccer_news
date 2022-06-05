package pedroluiz.projeto.soccernews.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pedroluiz.projeto.soccernews.MainActivity
import pedroluiz.projeto.soccernews.databinding.FragmentFavoritosBinding
import pedroluiz.projeto.soccernews.domain.News
import pedroluiz.projeto.soccernews.ui.adapter.NewsAdapter

class FavoritosFragment : Fragment() {

    private lateinit var favoritoNews : List<News>
    private var _binding: FragmentFavoritosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val FavoritosViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.loadFavoritoNews()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadFavoritoNews(){
        val activity = activity as MainActivity?

        if (activity != null) {
            favoritoNews = activity.getDb().newsDao().loadFavoriteNews(true)
        }

        binding.rcNews.layoutManager = LinearLayoutManager(context)
        binding.rcNews.adapter = NewsAdapter(favoritoNews, NewsAdapter.NewsFavoriteListener{

            val activity = activity as MainActivity?
            if (activity != null) {
                var db = activity.getDb()
                db.newsDao().insert(it)
                loadFavoritoNews()
            }

        })
    }
}