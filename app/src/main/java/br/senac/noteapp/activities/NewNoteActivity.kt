package br.senac.noteapp.activities

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import br.senac.noteapp.databinding.ActivityNewNoteBinding
import br.senac.noteapp.model.Note
import br.senac.noteapp.model.Notes

class NewNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val sharedPrefs = getSharedPreferences("Users", Context.MODE_PRIVATE)
            val user = sharedPrefs.getString("user", " ") as String

            val note = Note(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
                user = user
            )

            Notes.noteList.add(note)
            finish()
        }

    }

}