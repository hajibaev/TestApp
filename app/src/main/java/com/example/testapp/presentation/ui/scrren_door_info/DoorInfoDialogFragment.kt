package com.example.testapp.presentation.ui.scrren_door_info

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.testapp.databinding.FragmentDoorInfoDialogBinding
import com.example.testapp.presentation.models.DoorUiModel
import com.example.testapp.presentation.utils.bindingLifecycleError
import com.example.testapp.presentation.utils.makeToast
import com.example.testapp.presentation.utils.runWithArgumentsOrSkip
import com.example.testapp.presentation.utils.tuneLyricsDialog
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DoorInfoDialogFragment : DialogFragment() {
    private var _binding: FragmentDoorInfoDialogBinding? = null
    private val binding get() = _binding ?: bindingLifecycleError()

    private val dishesId: String by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getString(DOORS_KEY, String()) ?: String()
    }

    private val viewModel: DoorInfoDialogViewModel by viewModel {
        parametersOf(dishesId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoorInfoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        tuneLyricsDialog()
        initAndApplyArguments()
    }

    private fun observeData() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            doors.collectLatest {
                observeResource(it)
                setOnClickListeners()
            }
        }

    }

    private fun setOnClickListeners() = with(binding) {
        save.setOnClickListener {
            requireContext().makeToast("Эта функция еще не реализована.")
            dismiss()
        }
        closed.setOnClickListener {
            dismiss()
        }
    }

    private fun initAndApplyArguments() = runWithArgumentsOrSkip { arguments ->
        val title = arguments.getString(MediaStore.Audio.AudioColumns.TITLE_KEY)
        if (title.isNullOrEmpty()) return@runWithArgumentsOrSkip
    }

    private fun observeResource(doorUiModel: DoorUiModel) = with(binding) {
        title.setText(doorUiModel.name)
    }

    companion object {
        private const val DOORS_KEY = "DOORS_KEY"

        fun getInstance(
            id: String
        ): DoorInfoDialogFragment {
            val dialog = DoorInfoDialogFragment()
            val bundle = Bundle()
            bundle.putString(DOORS_KEY, id)
            dialog.arguments = bundle
            return dialog
        }
    }
}

