package pedroluiz.projeto.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pedroluiz.projeto.soccernews.MainActivity
import pedroluiz.projeto.soccernews.data.local.AppDataBase
import pedroluiz.projeto.soccernews.databinding.FragmentNewsBinding
import pedroluiz.projeto.soccernews.ui.adapter.NewsAdapter


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.NewsViewModel()

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcNews.layoutManager = LinearLayoutManager(context)


        newsViewModel.listNews.observe(viewLifecycleOwner) {
            binding.rcNews.adapter = NewsAdapter(it, NewsAdapter.NewsFavoriteListener{
                val activity = activity as MainActivity?
                if (activity != null) {
                   var db = activity.getDb()
                    db.newsDao().insert(it)

                }
            })
        }

        newsViewModel.getState.observe(viewLifecycleOwner) {
            when(it){
                NewsViewModel.State.DOING -> print("todo incluis INICIAR wsiperefreshlayout LOADING")
                NewsViewModel.State.DONE -> print("todo incluis FINALIZAR wsiperefreshlayout LOADING")
                NewsViewModel.State.ERROR -> print("todo incluis FINALIZAR wsiperefreshlayout LOADING") //TODO MOSTRAR um erro generico
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}