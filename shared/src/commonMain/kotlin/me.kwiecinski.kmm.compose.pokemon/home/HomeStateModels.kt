package me.kwiecinski.kmm.compose.pokemon.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
@Parcelize
sealed class StoryHomeScreen: Parcelable {
  object List: StoryHomeScreen()

  data class Details(val pokemonId: Int): StoryHomeScreen()
}
