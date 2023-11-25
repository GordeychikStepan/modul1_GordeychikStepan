package com.bignerdranch.android.cinemaapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
        val overviewTextView: TextView = itemView.findViewById(R.id.movieOverviewTextView)
        val countriesTextView: TextView = itemView.findViewById(R.id.movieCountriesTextView)
        val genresTextView: TextView = itemView.findViewById(R.id.movieGenresTextView)
        val premiereRuTextView: TextView = itemView.findViewById(R.id.moviePremiereRuTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .placeholder(R.drawable.movie)
            .into(holder.posterImageView)

        holder.titleTextView.text = movie.nameRu

        if (movie.nameEn.isEmpty()) {
            holder.overviewTextView.visibility = View.GONE
        } else {
            holder.overviewTextView.visibility = View.VISIBLE
            holder.overviewTextView.text = movie.nameEn
        }


        val countries = movie.countries.joinToString { it.country }

        val hours = movie.duration / 60
        val minutes = movie.duration % 60
        val durationText = if (hours > 0) {
            "$hours ${pluralize(hours, "час", "часа", "часов")} $minutes ${pluralize(minutes, "минута", "минуты", "минут")}"
        } else if (movie.duration < 0) {
            "$minutes ${pluralize(minutes, "минута", "минуты", "минут")}"
        } else {
            null
        }

        if (countries.isNotEmpty()) {
            holder.countriesTextView.text = if (durationText != null) {
                "$countries ● $durationText"
            } else {
                countries
            }
        } else {
            holder.countriesTextView.text = durationText ?: ""
        }



        val genres = movie.genres.joinToString { it.genre }
        holder.genresTextView.text = "Жанр: $genres"

        holder.premiereRuTextView.text = "Премьера (Россия): ${movie.premiereRu}"
    }

    private fun pluralize(value: Int, one: String, few: String, many: String): String {
        return when {
            value % 10 == 1 && value % 100 != 11 -> one
            value % 10 in 2..4 && value % 100 !in 12..14 -> few
            else -> many
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}