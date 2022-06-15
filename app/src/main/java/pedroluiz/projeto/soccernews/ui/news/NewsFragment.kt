package pedroluiz.projeto.soccernews.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.ls.LSException
import pedroluiz.projeto.soccernews.databinding.FragmentNewsBinding
import pedroluiz.projeto.soccernews.domain.News
import pedroluiz.projeto.soccernews.ui.adapter.NewsAdapter


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.NewsViewModel()

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcNews.layoutManager = LinearLayoutManager(context)

        observNews()

        observeStates()

        binding.srfNews.setOnRefreshListener {
            newsViewModel.findNews()
            binding.searchNews.setQuery("",false)
        }

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

        return root
    }

    private fun observeStates() {
        newsViewModel.getState.observe(viewLifecycleOwner) {
            when (it) {
                NewsViewModel.State.DOING -> binding.srfNews.isRefreshing = true
                NewsViewModel.State.DONE -> binding.srfNews.isRefreshing = false
                NewsViewModel.State.ERROR -> {
                    binding.srfNews.isRefreshing = false
                    Snackbar.make(binding.srfNews, "Network error", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    private fun observNews() {
        newsViewModel.listNews.observe(viewLifecycleOwner) {
                binding.rcNews.adapter = NewsAdapter(it, NewsAdapter.NewsFavoriteListener {
                    newsViewModel.saveNews(it)
                })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}