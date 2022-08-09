package pedroluiz.projeto.soccernews.presentation.favoritos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.databinding.FragmentFavoriteBinding
import pedroluiz.projeto.soccernews.presentation.ViewState
import pedroluiz.projeto.soccernews.presentation.adapter.NewsAdapter

class FavoriteFragment : Fragment() {

    private lateinit var _binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserv()

        loadFavoriteNews()

    }

    private fun setupObserv() {
        favoriteViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.SetNewsListLoaded -> {
                    binding.rcNews.layoutManager = LinearLayoutManager(context)
                    binding.rcNews.adapter = NewsAdapter(state.listNews) {
                        favoriteViewModel.saveNews(it)
                        loadFavoriteNews()
                    }
                }
            }
        }
    }

    private fun loadFavoriteNews() {
        favoriteViewModel.loadFavoriteNews()
    }

}