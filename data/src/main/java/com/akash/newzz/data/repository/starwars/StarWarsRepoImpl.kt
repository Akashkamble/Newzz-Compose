package com.akash.newzz.data.repository.starwars

import com.akash.newzz.data.Result
import com.akash.newzz.data.apiservice.StarWarsApiService
import com.akash.newzz.data.response.starwars.Character
import javax.inject.Inject

class StarWarsRepoImpl @Inject constructor(
    val api: StarWarsApiService
) : StarWarsRepo {
    override suspend fun getStarWarsCharacters(): Result<List<Character>> {
        return try {
            val result = api.getStarWarsCharacters()
            if(result.isSuccessful){
                Result.Success(result.body()?.characters!!)
            } else {
                Result.Error("Something went wrong")
            }
        } catch (e : Exception) {
            e.printStackTrace()
            var errorMessage = e.localizedMessage
            if (e.localizedMessage!!.contains("Unable to resolve host")) {
                errorMessage = "No internet connection"
            }
            return Result.Error(errorMessage ?: "Something went wrong")
        }
    }
}