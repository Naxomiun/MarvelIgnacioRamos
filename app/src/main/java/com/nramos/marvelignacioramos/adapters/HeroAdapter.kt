package com.nramos.marvelignacioramos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nramos.domain.model.MarvelHero
import com.nramos.marvelignacioramos.databinding.ItemHeroPreviewBinding

class HeroAdapter : ListAdapter<MarvelHero, HeroAdapter.HeroViewHolder>(HeroDiffUtil) {

    class HeroViewHolder(private val binding: ItemHeroPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: MarvelHero) = with(binding) {
            tvHeroName.text = hero.name
            ivHeroPreview.load(hero.thumbnail) {
                crossfade(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(ItemHeroPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero)
    }

    internal object HeroDiffUtil : DiffUtil.ItemCallback<MarvelHero>() {
        override fun areItemsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean = oldItem == newItem
    }

}