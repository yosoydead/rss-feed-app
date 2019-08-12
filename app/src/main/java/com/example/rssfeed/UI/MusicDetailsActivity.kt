package com.example.rssfeed.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rssfeed.R
import kotlinx.android.synthetic.main.music_details_activity.*

class MusicDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_details_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)

        val values = intent
        val title = intent.getStringExtra("TITLE")
        val artist = intent.getStringExtra("ARTIST")

        music_details_title.text = title
        music_details_artist.text = artist
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_share -> shareResult()
            android.R.id.home -> {
                Log.d("DETAILS", "pressed on home")
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareResult(): Boolean{
        //create a new intent to share data and set some properties for the intent
        //i use this to share a simple string with other apps
        val intent = Intent().apply{
            action = Intent.ACTION_SEND

            //i have to use this key to make the data be receivable by other apps
            putExtra(Intent.EXTRA_TEXT, "Text trimis prin implicit intent din aplicatia mea de rss feed. Iti recomand ${music_details_title.text} de ${music_details_artist.text}.")
            type="text/plain"
        }
        startActivity(intent)

        return true
    }
}
