package com.br.natanfc.filmesflix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.natanfc.filmesflix.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listOfMovie = arrayListOf<Movie>(
        Movie(
            id = 0,
            titulo = "Titanic",
            descricao = null,
            imagem = null,
            dataLancamento = null,
        ),
        Movie(
            id = 1,
            titulo = "Centra do Brasil",
            descricao = null,
            imagem = null,
            dataLancamento = null,
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateList()
    }

    private fun populateList() {
        moviesList.apply {
            hasFixedSize()
            adapter = MoviesAdapter(listOfMovie)
        }
    }
}