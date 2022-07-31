package pedroluiz.projeto.soccernews.presenter.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.databinding.FragmentNewsBinding
import pedroluiz.projeto.soccernews.presenter.adapter.NewsAdapter
import pedroluiz.projeto.soccernews.utils.Resource


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

        observNewsInsert()

        observeStates()

        atualizaRefreshNews()

        searcViewFilter()

        getAllNews()
    }

    private fun getAllNews(){
       // newsViewModel.findNews()

    }

    private fun observNewsInsert() {


        newsViewModel.listNews.observe(viewLifecycleOwner, Observer {

            Log.e("test44", it.data.toString())


            when (it.status) {
                Resource.Status.SUCCESS -> {
                    //  binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()){
                        binding.rcNews.adapter = NewsAdapter(it.data) {
                            newsViewModel.saveNews(it)
                        }
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    Log.e("test44", "4444")

                //binding.progressBar.visibility = View.VISIBLE
            }
        })

        /*
        newsViewModel.listNews.observe(viewLifecycleOwner) { listaNews ->
            binding.rcNews.adapter = NewsAdapter(listaNews) {
                newsViewModel.saveNews(it)
            }
        }
         */

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

    private fun atualizaRefreshNews(){
        binding.srfNews.setOnRefreshListener {
            getAllNews()
            binding.searchNews.setQuery("",false)
        }
    }

    private fun searcViewFilter(){
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