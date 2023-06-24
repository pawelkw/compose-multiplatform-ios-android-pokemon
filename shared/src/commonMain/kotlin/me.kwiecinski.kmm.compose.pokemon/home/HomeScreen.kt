package me.kwiecinski.kmm.compose.pokemon.home

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import me.kwiecinski.kmm.compose.pokemon.home.StoryHomeScreen.Details
import me.kwiecinski.kmm.compose.pokemon.home.StoryHomeScreen.List
import me.kwiecinski.kmm.compose.pokemon.pokemondetail.PokemonDetail
import me.kwiecinski.kmm.compose.pokemon.pokemonlist.PokemonList

@Composable
fun HomeScreen() {
  val router: Router<StoryHomeScreen> = rememberRouter(StoryHomeScreen::class, listOf(List))

  RoutedContent(
    router = router,
    animation = stackAnimation(slide()),
  ) { screen ->
    when (screen) {
      List -> PokemonList(onPokemonClicked = { pokemonId -> router.push(Details(pokemonId)) })
      is Details -> PokemonDetail(pokemonId = screen.pokemonId) { router.pop() }
    }
  }
}
