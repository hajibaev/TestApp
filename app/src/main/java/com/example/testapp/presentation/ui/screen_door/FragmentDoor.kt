package com.example.testapp.presentation.ui.screen_door

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.testapp.databinding.FragmentDoorBinding
import com.example.testapp.presentation.adapter.DoorAdapter
import com.example.testapp.presentation.adapter.DoorItemClickListener
import com.example.testapp.presentation.ui.scrren_door_info.DoorInfoDialogFragment
import com.example.testapp.presentation.utils.hide
import com.example.testapp.presentation.utils.show
import com.example.testapp.presentation.utils.showOnlyOne
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDoor : Fragment(), DoorItemClickListener {

    private val binding by lazy {
        FragmentDoorBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        DoorAdapter(this)
    }

    private val viewModel: FragmentDoorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            doors.collectLatest {
                adapter.submitList(it)
                observeScreen()
            }
        }
        showDialog.onEach {
            showDoorInfoDialog(it.toString())
        }.launchIn(viewModelScope)

    }

    private fun showDoorInfoDialog(id: String) {
        DoorInfoDialogFragment.getInstance(id = id).showOnlyOne(parentFragmentManager)
    }


    private fun observeScreen() = with(binding) {
        loading.pauseAnimation()
        loading.hide()
        recyclerView.show()
    }

    override fun doorItemOnClick(id: Int) {
        viewModel.doorItemOnClick(id)
    }
}