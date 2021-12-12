package com.khedr.rickandmorty.common

object Utils {

    fun getLastIndexesFromUrls(words: List<String>): List<String> {
        val outList: ArrayList<String> = arrayListOf()
        for (word in words) {
            if (words.isNullOrEmpty()) {
                return emptyList()
            }
            val urlIndexes = word.split("/")
            outList.add(urlIndexes[urlIndexes.size - 1])
        }
        return outList.toList()
    }
}