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
import pedroluiz.projeto.soccernews.data.model.entity.News
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

        binding.rcNews.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserv()
        searchNews()
        getAllNews()
    }

    private fun setupObserv() {
        newsViewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.SetLoading ->  showLoading(state.isLoading)
                is ViewState.SetNewsListLoaded -> setNewsListAdapter(state.listNews)
                is ViewState.LoadFailure -> showErro(state.messageError)
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
                filterNews(newText)
                return false
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun showErro(text: String) {
        Log.e("showErro", text)
    }

    private fun setNewsListAdapter(state: List<News>) {
        binding.rcNews.adapter = NewsAdapter(state) {
            newsViewModel.saveNews(it)
        }
    }

    private fun getAllNews(){
        newsViewModel.getAllNews()
    }

    private fun filterNews(text: String){
        newsViewModel.filterList(text)
    }
}