package com.stellarworker.movies.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stellarworker.movies.R
import com.stellarworker.movies.domain.Top250MovieInt

class MainFragmentAdapter(
    private val onItemClicked: ((movie: Top250MovieInt) -> Unit)? = null
) : RecyclerView.Adapter<MainFragmentAdapter.RecyclerItemViewHolder>() {
    private var movies: List<Top250MovieInt> = listOf()

    fun setData(movies: List<Top250MovieInt>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val rank: AppCompatTextView =
            itemView.findViewById(R.id.movie_item_rank)
        private val poster: AppCompatImageView =
            itemView.findViewById(R.id.movie_item_poster)
        private val title: AppCompatTextView =
            itemView.findViewById(R.id.movie_item_title)
        private val year: AppCompatTextView =
            itemView.findViewById(R.id.movie_item_year)

        init {
            itemView.setOnClickListener {
                onItemClicked?.invoke(movies[adapterPosition])
            }
        }

        fun bind(movie: Top250MovieInt) {
            rank.text = movie.rank
            poster.load(movie.image)
            title.text = movie.title
            year.text = movie.year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}