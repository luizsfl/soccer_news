package pedroluiz.projeto.soccernews

import android.os.Bundle
import android.view.Menu

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import pedroluiz.projeto.soccernews.databinding.ActivityMainBinding
import pedroluiz.projeto.soccernews.ui.news.NewsViewModel

class MainActivity : AppCompatActivity(){
    private lateinit var newsViewModel: NewsViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main  )

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)


    }

}