package pedroluiz.projeto.soccernews.presenter.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.databinding.FragmentFavoritosBinding
import pedroluiz.projeto.soccernews.presenter.adapter.NewsAdapter

class FavoritosFragment : Fragment() {

    private lateinit var _binding: FragmentFavoritosBinding
    private val favoritosViewModel: FavoritosViewModel by viewModel()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFavoriteNews()
    }

    private fun loadFavoriteNews() {
        favoritosViewModel.loadFavoritoNews().observe(viewLifecycleOwner) {
            binding.rcNews.layoutManager = LinearLayoutManager(context)
            binding.rcNews.adapter = NewsAdapter(it){ news ->
                favoritosViewModel.saveNews(news)
                loadFavoriteNews()
            }
        }
    }
}