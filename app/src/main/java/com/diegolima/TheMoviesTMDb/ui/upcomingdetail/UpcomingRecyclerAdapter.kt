package com.diegolima.TheMoviesTMDb.ui.upcomingdetail

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.diegolima.TheMoviesTMDb.R
import com.diegolima.TheMoviesTMDb.domain.model.Result
import dominando.android.mobile2you.util.Constants
import javax.inject.Inject

class UpcomingRecyclerAdapter @Inject constructor(
    val glide: RequestManager
): RecyclerView.Adapter<UpcomingRecyclerAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Result) -> Unit) ? = null

    private val diffUtil = object: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem == newItem
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies: List<Result>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val posterPath = holder.itemView.findViewById<ImageView>(R.id.ivPosterPath)
        val cardView = holder.itemView.findViewById<CardView>(R.id.cv_movie)

        val movie = movies[position]

        holder.itemView.apply {
            val moviePoster: Uri = Uri.parse(Constants.BASE_IMAGE + movie.poster_path)
            glide.load(moviePoster)
                .into(posterPath)

            cardView.setOnClickListener{
                onItemClickListener?.let { click ->
                    click(movie)
                }
            }
        }
    }

    fun setOnMovieItemClickListener(listener: (Result) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = movies.size
}