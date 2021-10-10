package com.nramos.marvelignacioramos.ui.screens.herolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nramos.domain.model.MarvelHero
import com.nramos.marvelignacioramos.databinding.ItemHeroPreviewBinding

class HeroAdapter(private val callback: (Int) -> Unit) : ListAdapter<MarvelHero, HeroAdapter.HeroViewHolder>(
    HeroDiffUtil
) {

    class HeroViewHolder(private val binding: ItemHeroPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: MarvelHero, callback: (Int) -> Unit) = with(binding) {
            tvHeroName.text = hero.name
            ivHeroPreview.load(hero.thumbnail) {
                crossfade(true)
                transformations(RoundedCornersTransformation(30F))
            }
            ivHeroPreview.contentDescription = "Marvel hero ${hero.name}"
            root.setOnClickListener { callback.invoke(hero.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(ItemHeroPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero, callback)
    }

    internal object HeroDiffUtil : DiffUtil.ItemCallback<MarvelHero>() {
        override fun areItemsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean = oldItem == newItem
    }

}