package com.udacity.shoestore.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel:ViewModel() {
    private var _shoesListLiveData = MutableLiveData<List<Shoe>>()
    val shoesListLiveData:LiveData<List<Shoe>> get() =  _shoesListLiveData
    private val shoeList = ArrayList<Shoe>()

    fun addShoe(newShoe: Shoe) {
        shoeList.add(newShoe)
        _shoesListLiveData.postValue(shoeList)
    }

}