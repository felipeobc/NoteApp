package br.senac.noteapp.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.senac.noteapp.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPrefs = getSharedPreferences("Users", Context.MODE_PRIVATE)
        binding.etUsername.setText(sharedPrefs.getString("Users", ""))


        binding.btnSave.setOnClickListener {

            val editor = sharedPrefs.edit()

            editor.putString("nome", binding.etUsername.text.toString())

            editor.commit()
        //val nome = sharedPrefs.getString("nome", "Nao tem")

        }

    }

}