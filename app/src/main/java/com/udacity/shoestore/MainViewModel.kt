package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel: ViewModel(){
    private var _shoeItemList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeItemList: LiveData<MutableList<Shoe>>
        get() = _shoeItemList

    fun addShoe(shoe: Shoe){
        _shoeItemList.value?.add(shoe)
        _saveButtonPress.value = true
    }

    fun saveShoeFinished(){
        _saveButtonPress.value = false
    }

    private val _saveButtonPress = MutableLiveData(false)
    val saveButtonPress: LiveData<Boolean>
        get() = _saveButtonPress

}