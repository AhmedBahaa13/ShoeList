package com.udacity.shoestore.presentation.viewModels

import android.text.Editable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.models.Shoe

class ShoesViewModel:ViewModel() {
    private var _shoesListLiveData = MutableLiveData<List<Shoe>>()
    val shoesListLiveData:LiveData<List<Shoe>> get() =  _shoesListLiveData

    private val shoeList = ArrayList<Shoe>()

   private fun addShoe(newShoe: Shoe) {
        shoeList.add(newShoe)
        _shoesListLiveData.postValue(shoeList)
    }

    fun saveShoe(view:View,name:Editable,company:Editable,size:Editable,description:Editable){
        val shoeSize = if (size.isEmpty()) 0.0
        else size.toString().toDouble()

        val newShoe = Shoe(
            name.toString(),
            shoeSize,
            company.toString(),
            description.toString())

        addShoe(newShoe)
        view.findNavController().popBackStack()
    }
    fun cancel(view:View){
        view.findNavController().popBackStack()
    }



}