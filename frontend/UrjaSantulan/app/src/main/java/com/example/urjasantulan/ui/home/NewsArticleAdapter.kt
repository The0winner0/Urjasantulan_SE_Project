package com.example.urjasantulan.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.urjasantulan.R
import com.bumptech.glide.Glide


class NewsArticleAdapter : ListAdapter<Article, NewsArticleAdapter.ArticleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.articleTitle)
        private val description: TextView = itemView.findViewById(R.id.articleDescription)
        private val image: ImageView = itemView.findViewById(R.id.articleImage)

        fun bind(article: Article) {
            title.text = article.title
            description.text = article.description
            // Load image with your preferred image loader
            // e.g., Glide or Picasso
            Glide.with(itemView.context)
                .load(article.imageUrl)
                .into(image)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }
}