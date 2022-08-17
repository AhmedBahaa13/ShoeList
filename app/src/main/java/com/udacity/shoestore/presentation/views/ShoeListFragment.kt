package com.udacity.shoestore.presentation.views

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.presentation.viewModels.ShoesViewModel

@SuppressLint("SetTextI18n")
class ShoeListFragment : Fragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_shoe_list, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        if (binding.linearLayout.childCount == 0) {
            val noItemsText = TextView(requireContext())
            noItemsText.textSize = 32f
            noItemsText.text = "There is no Items \n Add One"
            noItemsText.gravity = Gravity.CENTER
            noItemsText.setTextColor(Color.WHITE)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(36)

            noItemsText.layoutParams = params
            binding.linearLayout.addView(noItemsText)
            binding.linearLayout.gravity = Gravity.CENTER
        }
    }


    private fun initListeners() {
        binding.add.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }
        viewModel.shoesListLiveData.observe(viewLifecycleOwner) {
            // remove NoItems TextView If there at least one item
            if (it.isNotEmpty()) binding.linearLayout.removeAllViews()
            for (i in it) {
                val newShoeView = TextView(requireContext())
                newShoeView.text = "${i.name} / ${i.size} / ${i.company}/ ${i.description}"
                newShoeView.textSize = 21f
                newShoeView.setTextColor(Color.WHITE)
                newShoeView.background =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_bck)
                val shoeViewParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                shoeViewParams.setMargins(0, 0, 0, 50)
                newShoeView.layoutParams = shoeViewParams
                newShoeView.setPadding(30)

                binding.linearLayout.addView(newShoeView)
            }

        }
    }


}