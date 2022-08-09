package pedroluiz.projeto.soccernews.presentation.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.databinding.FragmentNewsBinding
import pedroluiz.projeto.soccernews.presentation.ViewState
import pedroluiz.projeto.soccernews.presentation.adapter.NewsAdapter

class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcNews.layoutManager = LinearLayoutManager(context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserv()

        searchNews()

        newsViewModel.getAllNews()

    }

    private fun setupObserv() {

        newsViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.SetLoading -> {
                //    binding.progressBar.isVisible = state.isLoading
                }
                is ViewState.SetNewsListLoaded -> {
                        binding.rcNews.adapter = NewsAdapter(state.listNews) {
                            newsViewModel.saveNews(it)
                        }
                }
                is ViewState.LoadFailure -> Log.e("Teste", state.messageError)
            }
        }
    }

    private fun searchNews() {
        binding.searchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newsViewModel.filterList(newText)
                return false
            }
        })
    }
}