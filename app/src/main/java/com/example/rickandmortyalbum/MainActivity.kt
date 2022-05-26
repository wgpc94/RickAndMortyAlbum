package com.example.rickandmortyalbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rickandmortyalbum.view.CharactersListFragment
import com.example.rickandmortyalbum.view.FragmentAbout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fill, CharactersListFragment())
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_more, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m_first -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.fill, FragmentAbout())
                addToBackStack(null)
                commit()
            }
        }
        return true
    }


}