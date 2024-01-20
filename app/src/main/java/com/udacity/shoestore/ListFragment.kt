package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.databinding.FragmentListBinding
import com.udacity.shoestore.databinding.ListItemBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeItemList.observe(viewLifecycleOwner) { list ->
            listShoes(list)
        }

        setHasOptionsMenu(true)
        binding.addNewButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listFragment_to_detailFragment)
        )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addNewMenuItem -> {
                view?.findNavController()
                    ?.navigate(R.id.action_listFragment_to_detailFragment)
                true
            }

            R.id.loginFragment -> {
                view?.findNavController()
                    ?.navigate(R.id.loginFragment)
                true
            }

            else -> NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
        }
    }
    private fun listShoes(listItem: MutableList<Shoe>) {
        listItem.forEach { shoe ->
            val itemBinding: ListItemBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                binding.shoeListView,
                false
            )

            itemBinding.apply {
                this.shoe = shoe
            }
            itemBinding.shoeNameItemLabel.text = shoe.name
            itemBinding.companyShoeItemText.text = shoe.company
            itemBinding.sizeShoeItemText.text = shoe.size.toString()
            itemBinding.descriptionText.text = shoe.description

            binding.shoeListView.addView(itemBinding.root)
        }
    }
}