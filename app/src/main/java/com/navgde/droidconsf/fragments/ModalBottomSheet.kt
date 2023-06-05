package com.navgde.droidconsf.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.navgde.droidconsf.composables.BottomSheetContent
import com.navgde.droidconsf.databinding.ModalBottomSheetContentBinding

/**
 * @author Nav Singh
 */
class ModalBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ModalBottomSheetContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBottomSheetContentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvHolder.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                BottomSheetContent()
            }
        }
        // custom back handling in fragment
        /*    val backPressedCallback = object : OnBackPressedCallback(enabled = true) {

                override fun handleOnBackPressed() {
                    //...  back logic
                    remove()
                }

            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)*/

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

