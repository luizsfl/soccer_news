package pedroluiz.projeto.soccernews.presenter.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pedroluiz.projeto.soccernews.databinding.FragmentFavoritosBinding
import pedroluiz.projeto.soccernews.presenter.adapter.NewsAdapter

class FavoritosFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null

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
            binding.rcNews.adapter = NewsAdapter(it, NewsAdapter.NewsFavoriteListener { news ->

                favoritosViewModel.saveNews(news)
                loadFavoriteNews(favoritosViewModel)

            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}