package com.tomasm.nytimesapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomasm.articles.data.models.view.ArticleView
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.databinding.ArticlesListItemBinding

class ArticlesAdapter(
    var articles: MutableList<ArticleView>,
    private val onItemSelected: (ArticleView) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(
            layoutInflater.inflate(
                R.layout.articles_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ArticlesListItemBinding.bind(view)
        fun bind(article: ArticleView) {
            binding.articleTitle.text = article.title
            binding.articleAuthor.text = article.byline
            binding.articleSection.text = article.section
            binding.articleDate.text = article.publishedDate
            Glide.with(binding.articleImage.context)
                .load(article.imageURL)
                .placeholder(R.drawable.ic_nytimeslogo)
                .into(binding.articleImage)
            binding.itemRecyclerview.setOnClickListener {
                onItemSelected(article)
            }
        }
    }
}



