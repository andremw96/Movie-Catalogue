package com.andreamw96.moviecatalogue.model.dummydata

import java.util.ArrayList

object TVShowData {
    private val data = arrayOf(arrayOf("Krypton", "March 21, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg", "67", "David S. Goyer", "Set two generations before the destruction of the legendary Man of Steel’s home planet, Krypton follows Superman’s grandfather — whose House of El was ostracized and shamed — as he fights to redeem his family’s honor and save his beloved world from chaos."), arrayOf("Goblin Slayer", "October 7, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dY2oCQXBGDzoeUu1GxaGynHFs1R.jpg", "79", "Takaharu Ozaki", "A young priestess has formed her first adventuring party, but almost immediately they find themselves in distress. It's the Goblin Slayer who comes to their rescue—a man who's dedicated his life to the extermination of all goblins, by any means necessary. And when rumors of his feats begin to circulate, there's no telling who might come calling next..."), arrayOf("Legacies", "October 25, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/rb64COqdpRRfWOc6gWTfC7WxzXP.jpg", "68", "Julie Plec", "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted."), arrayOf("Final Space", "February 26, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/gPUW5fUKZtLkmlWqZteLBeXPymC.jpg", "83", "Olan Rogers", "An astronaut named Gary and his planet-destroying sidekick Mooncake embark on serialized journeys through space in order to unlock the mystery of “Final Space,” the last point in the universe, if it actually does exist."), arrayOf("Titans", "October 12, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/oCK6fykCZUQjTJG4IDhfWCxcXqG.jpg", "73", "Geoff Johns", "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils."))

    val listData: ArrayList<Movie>
        get() {
            var movie: Movie
            val list = ArrayList<Movie>()
            for (myData in data) {
                movie = Movie()
                movie.title = myData[0]
                movie.date = myData[1]
                movie.photo = myData[2]
                movie.rating = myData[3]
                movie.director = myData[4]
                movie.description = myData[5]

                list.add(movie)
            }

            return list
        }
}