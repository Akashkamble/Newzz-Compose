package com.akash.newzz.data.repository.starwars

import com.akash.newzz.data.Result
import com.akash.newzz.data.response.starwars.Character

interface StarWarsRepo {
    suspend fun getStarWarsCharacters(): Result<List<Character>>
}