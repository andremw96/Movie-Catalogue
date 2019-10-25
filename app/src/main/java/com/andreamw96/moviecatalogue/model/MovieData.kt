package com.andreamw96.moviecatalogue.model


object MovieData {

    fun listData(): List<Movies> {
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

        movies.add(
                Movies(
                        6,
                        "The Old Man & the Gun",
                        "September 28, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
                        "63",
                        "David Lowery",
                        "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession."
                )
        )

        movies.add(
                Movies(
                        7,
                        "Deadpool 2",
                        "May 10, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg",
                        "75",
                        "David Leitch",
                        "Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life."

                )
        )

        movies.add(
                Movies(
                        8,
                        "Bohemian Rhapsody",
                        "November 2, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/3TQRC4WFjtr5PylWYpo81TlqPNU.jpg",
                        "80",
                        "Bryan Singer",
                        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
                )
        )

        movies.add(
                Movies(
                        9,
                        "Mission: Impossible - Fallout",
                        "July 27, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/AkJQpZp9WoNdj7pLYSj1L0RcMMN.jpg",
                        "73",
                        "Christopher McQuarrie",
                        "When an IMF mission ends badly, the world is faced with dire consequences. As Ethan Hunt takes it upon himself to fulfill his original briefing, the CIA begin to question his loyalty and his motives. The IMF team find themselves in a race against time, hunted by assassins while trying to prevent a global catastrophe."
                )
        )

        movies.add(
                Movies(
                        10,
                        "Bumblebee",
                        "December 21, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fw02ONlDhrYjTSZV8XO6hhU3ds3.jpg",
                        "65",
                        "Travis Knight",
                        "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug."
                )
        )

        movies.add(
                Movies(
                        11,
                        "Robin Hood",
                        "November 21, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
                        "58",
                        "Otto Bathurst",
                        "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown."
                )
        )

        movies.add(
                Movies(
                        12,
                        "Jurassic World: Fallen Kingdom",
                        "June 22, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7dAh80ydoWvUaBE8uFYkp9WsoSC.jpg",
                        "65",
                        "J. A. Bayona",
                        "Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again."
                )
        )

        movies.add(
                Movies(
                        13,
                        "Aquaman",
                        "December 21, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                        "68",
                        "James Wan",
                        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
                )
        )

        movies.add(
                Movies(
                        14,
                        "Solo: A Star Wars Story",
                        "May 25, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/3IGbjc5ZC5yxim5W0sFING2kdcz.jpg",
                        "66",
                        "Ron Howard",
                        "Through a series of daring escapades deep within a dark and dangerous criminal underworld, Han Solo meets his mighty future copilot Chewbacca and encounters the notorious gambler Lando Calrissian."
                )
        )

        movies.add(
                Movies(
                        15,
                        "A Star Is Born",
                        "October 5, 2018",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "75",
                        "Bradley Cooper",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
                )
        )

        return movies
    }
}