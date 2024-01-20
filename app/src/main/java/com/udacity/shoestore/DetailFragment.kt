package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.shoe = Shoe("", 0.0, "", "")

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.saveButtonPress.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.saveShoeFinished()
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}