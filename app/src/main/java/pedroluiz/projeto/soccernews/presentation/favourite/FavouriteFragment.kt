package pedroluiz.projeto.soccernews.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.databinding.FragmentFavouriteBinding
import pedroluiz.projeto.soccernews.presentation.ViewState
import pedroluiz.projeto.soccernews.presentation.adapter.NewsAdapter

class FavouriteFragment : Fragment() {

    private lateinit var _binding: FragmentFavouriteBinding
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        binding.rcNews.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserv()

        loadFavoriteNews()
    }

    private fun setupObserv() {
        favouriteViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.SetNewsListLoaded -> { setNewsListAdapter(state.listNews) }
            }
        }
    }

    private fun loadFavoriteNews() {
        favouriteViewModel.loadFavouriteNews()
    }

    private fun setNewsListAdapter(state: List<News>) {
        binding.rcNews.adapter = NewsAdapter(state) {
            favouriteViewModel.saveNews(it)
            loadFavoriteNews()
        }
    }
}