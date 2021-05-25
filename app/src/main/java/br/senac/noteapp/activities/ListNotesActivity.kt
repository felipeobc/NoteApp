package br.senac.noteapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.R
import br.senac.noteapp.databinding.ActivityListNotesBinding
import br.senac.noteapp.databinding.CardNoteBinding
import br.senac.noteapp.db.AppDataBase
import br.senac.noteapp.model.Note
import br.senac.noteapp.model.Notes

class ListNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.user -> {
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            }

        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()

        refreshNotes()
    }

    fun refreshNotes(){
        Thread {
            val db = Room.databaseBuilder(this, AppDataBase::class.java,"Appdb").build()

            val noteList = db.noteDao().list()

            runOnUiThread {
                updateUI(noteList)
            }


        }.start()
    }


    fun updateUI(notes: List<Note>){

        binding.notesContainer.removeAllViews()

        notes.forEach{
            val cardBinding = CardNoteBinding.inflate(layoutInflater)

            cardBinding.txtTitle.text = it.title
            cardBinding.txtDesc.text = it.desc
            cardBinding.creator.text = it.user

            binding.notesContainer.addView(cardBinding.root)
        }
    }


//    fun refreshNotes(){
//
//        binding.notesContainer.removeAllViews()
//
//        Notes.noteList.forEach{
//            val cardBinding = CardNoteBinding.inflate(layoutInflater)
//
//            cardBinding.txtTitle.text = it.title
//            cardBinding.txtDesc.text = it.desc
//            cardBinding.creator.text = it.user
//
//            binding.notesContainer.addView(cardBinding.root)
//        }
//    }


}