package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.model.Movies



object FakeDataMovie {

    fun listData() : List<Movies> {
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
}