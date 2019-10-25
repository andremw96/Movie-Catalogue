package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.model.Movies


object FakeData {

    fun listDataMovie() : List<Movies> {
        val movies = ArrayList<Movies>()

        movies.add(
                Movies(
                        1,
                        "Avengers: Infinity War",
                        "April 27, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                        "83",
                        "Joe Russo & Anthony Russo",
                        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
                )
        )

        movies.add(
                Movies(
                        2,
                        "Venom",
                        "October 5, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg",
                        "66",
                        "Ruben Fleischer",
                        "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own."
                )
        )

        movies.add(
                Movies(
                        3,
                        "Spider-Man: Into the Spider-Verse",
                        "December 14, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                        "84",
                        "Rodney Rothman",
                        "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension."
                )
        )

        movies.add(
                Movies(
                        4,
                        "Ant-Man and the Wasp",
                        "July 6, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/eivQmS3wqzqnQWILHLc4FsEfcXP.jpg",
                        "70",
                        "Peyton Reed",
                        "Just when his time under house arrest is about to end, Scott Lang once again puts his freedom at risk to help Hope van Dyne and Dr. Hank Pym dive into the quantum realm and try to accomplish, against time and any chance of success, a very dangerous rescue mission."
                )
        )

        movies.add(
                Movies(
                        5,
                        "Black Panther",
                        "February 16, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                        "74",
                        "Ryan Coogler",
                        "King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, T'Challa assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan 'special forces') and an American secret agent, to prevent Wakanda from being dragged into a world war."
                )
        )

        return movies
    }

    fun listDataTvShow(): List<Movies> {
        val tvShows = ArrayList<Movies>()

        tvShows.add(
                Movies(
                        1,
                        "Krypton",
                        "March 21, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg",
                        "67",
                        "David S. Goyer",
                        "Set two generations before the destruction of the legendary Man of Steel’s home planet, Krypton follows Superman’s grandfather — whose House of El was ostracized and shamed — as he fights to redeem his family’s honor and save his beloved world from chaos."
                )
        )

        tvShows.add(
                Movies(
                        2,
                        "Goblin Slayer",
                        "October 7, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/dY2oCQXBGDzoeUu1GxaGynHFs1R.jpg",
                        "79",
                        "Takaharu Ozaki",
                        "A young priestess has formed her first adventuring party, but almost immediately they find themselves in distress. It's the Goblin Slayer who comes to their rescue—a man who's dedicated his life to the extermination of all goblins, by any means necessary. And when rumors of his feats begin to circulate, there's no telling who might come calling next..."
                )
        )

        tvShows.add(
                Movies(
                        3,
                        "Legacies",
                        "October 25, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/rb64COqdpRRfWOc6gWTfC7WxzXP.jpg",
                        "68",
                        "Julie Plec",
                        "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted."
                )
        )

        tvShows.add(
                Movies(
                        4,
                        "Final Space",
                        "February 26, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/gPUW5fUKZtLkmlWqZteLBeXPymC.jpg",
                        "83",
                        "Olan Rogers",
                        "An astronaut named Gary and his planet-destroying sidekick Mooncake embark on serialized journeys through space in order to unlock the mystery of “Final Space,” the last point in the universe, if it actually does exist."
                )
        )

        tvShows.add(
                Movies(
                        5,
                        "Titans",
                        "October 12, 2018",
                        "https://image.tmdb.org/t/p/w185_and_h278_bestv2/oCK6fykCZUQjTJG4IDhfWCxcXqG.jpg",
                        "73",
                        "Geoff Johns",
                        "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils."
                )
        )

        return tvShows
    }
}