package br.senac.noteapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.senac.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insert (note: Note)

    @Delete
    fun delete (note: Note)

    @Query(value = "select * from Note")
    fun list(): List<Note>



}