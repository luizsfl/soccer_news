package pedroluiz.projeto.soccernews.presenter

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import pedroluiz.projeto.soccernews.R
import pedroluiz.projeto.soccernews.databinding.ActivityMainBinding
import pedroluiz.projeto.soccernews.presenter.news.NewsViewModel

class MainActivity : AppCompatActivity(){
    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        binding.navView.setupWithNavController(navController)

    }

}