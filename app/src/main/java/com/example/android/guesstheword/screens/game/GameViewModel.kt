package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    //var word = ""

    // The current score
    //var score = 0

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinish

    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel","GameViewModel created!")
        resetList()
        nextWord()

        _word.value =""
        _score.value=0
    }

    fun onGameFinish(){
        _eventGameFinish.value = true
    }
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()
        }else{
            _word.value = wordList.removeAt(0)
        }
    }

    public fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    public fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    override fun onCleared(){
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }
}