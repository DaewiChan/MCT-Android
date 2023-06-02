package com.example.mct.presentation.account.profile

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mct.R
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.presentation.account.login.LoginActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    var btnLogout: Button? = null
    var pref: AppPrefHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = AppPrefHelper.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogout = view.findViewById(R.id.btnLogout)

        catchEvent()
    }

    fun catchEvent(){
        btnLogout?.setOnClickListener {
            dialog()
        }
    }

    fun dialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        val dialogOk = dialog.findViewById<Button>(R.id.btn_yes)
        dialogOk?.setOnClickListener {
            pref?.clearPref()
            startActivity(LoginActivity.newIntent(requireContext()))
            activity?.supportFragmentManager?.popBackStack()
            dialog?.dismiss()

            //loadFragment(HomeFragment())
        }
        val dialogNo = dialog.findViewById<Button>(R.id.btn_no)
        dialogNo?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}