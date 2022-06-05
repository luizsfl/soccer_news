package pedroluiz.projeto.soccernews.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        val favoritosViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadFavoriteNews(favoritosViewModel)

        return root
    }

    private fun loadFavoriteNews(favoritosViewModel: FavoritosViewModel) {
        favoritosViewModel.loadFavoritoNews().observe(viewLifecycleOwner, {
            binding.rcNews.layoutManager = LinearLayoutManager(context)
            binding.rcNews.adapter = NewsAdapter(it, NewsAdapter.NewsFavoriteListener {

                favoritosViewModel.saveNews(it)
                loadFavoriteNews(favoritosViewModel)

            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}