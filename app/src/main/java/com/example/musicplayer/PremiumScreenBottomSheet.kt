package com.example.musicplayer

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.musicplayer.databinding.PremiumScreenBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PremiumScreenBottomSheet : BottomSheetDialogFragment() {

    private var dialog: BottomSheetDialog? = null
    private lateinit var binding:PremiumScreenBottomSheetBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return dialog!!
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = PremiumScreenBottomSheetBinding.inflate(inflater, container, false)

        binding.btnPremiumScreen.setOnClickListener {
            Toast.makeText(context,"Premium Screen Button is pressed",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from(view.parent as View)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        val layout = dialog!!.findViewById<CoordinatorLayout>(R.id.coordinator)!!
        layout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels
        binding.premiumScreenCloseBtn.visibility = View.INVISIBLE

       Handler(Looper.getMainLooper()).postDelayed({
            binding.premiumScreenCloseBtn.visibility = View.VISIBLE
            binding.premiumScreenCloseBtn.setOnClickListener {
                dialog!!.dismiss()
            } }, 3000)
    }
}
