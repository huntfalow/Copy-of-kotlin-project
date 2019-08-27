package com.example.vincent.wikipediaapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.fragments.ExploreFragment
import com.example.vincent.wikipediaapp.fragments.FavoritesFragment
import com.example.vincent.wikipediaapp.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * This fragment its purpose is to display all the pages (fragments) and the navigation of it
 *
 * @property exploreFragment this is the explore page
 * @property favoritesFragment this is the favorites page
 * @property historyFragment this is the history page



 */
class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment
    private val favoritesFragment: FavoritesFragment
    private val historyFragment: HistoryFragment

    /**
     * This function initialises the properties
     */
    init {

        exploreFragment = ExploreFragment()
        favoritesFragment = FavoritesFragment()
        historyFragment = HistoryFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when(item.itemId){
            R.id.navigation_explore -> transaction.replace(R.id.fragment_container, exploreFragment)
            R.id.navigation_favorties -> transaction.replace(R.id.fragment_container, favoritesFragment)

            R.id.navigation_history -> transaction.replace(R.id.fragment_container, historyFragment)
        }

        transaction.commit()

        true
    }
    /**
     * During the onCreate of the activity, we set set up the navigation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }
}
