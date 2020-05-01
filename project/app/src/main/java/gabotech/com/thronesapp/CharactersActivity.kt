package gabotech.com.thronesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity(), CharactersFragment.OnItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        if (savedInstanceState == null){
            val fragmentCharacter = CharactersFragment()

            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.listContainer, fragmentCharacter)
                .commit()
        }

    }

    override fun onItemClicked(character: Character) {
        showDetails(character.id)
    }

    private fun showDetails(characterID: String){
        Log.d("CharactersActivity", "Click is working!")
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterID)

        startActivity(intent)
    }

}