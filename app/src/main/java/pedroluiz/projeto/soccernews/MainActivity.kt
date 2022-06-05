package pedroluiz.projeto.soccernews

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import pedroluiz.projeto.soccernews.data.local.AppDataBase
import pedroluiz.projeto.soccernews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDataBase

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val navView: BottomNavigationView = binding.navView



        val navController = findNavController(R.id.nav_host_fragment_activity_main  )
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)


       this.setupDb()

    }


    fun setupDb(){
        db = Room.databaseBuilder(
            this,
            AppDataBase::class.java, "soccer-news"
        )
            .allowMainThreadQueries()
            .build()
    }

    fun getDb (): AppDataBase
    {return this.db}

}