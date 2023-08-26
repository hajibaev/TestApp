package com.example.testapp.presentation.ui.screen_camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testapp.databinding.FragmentCameraBinding
import com.example.testapp.presentation.adapter.CameraAdapter
import com.example.testapp.presentation.utils.hide
import com.example.testapp.presentation.utils.show
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCamera : Fragment() {

    private val binding by lazy {
        FragmentCameraBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        CameraAdapter()
    }

    private val viewModel: FragmentCameraViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerViewCamera.adapter = adapter
    }


    private fun observeData() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            camera.collectLatest {
                adapter.submitList(it)
                observeScreen()
            }
        }
    }

    private fun observeScreen() = with(binding) {
        loading.pauseAnimation()
        loading.hide()
        recyclerViewCamera.show()
    }

}