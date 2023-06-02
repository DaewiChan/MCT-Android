package com.example.mct.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mct.R
import com.example.mct.base.BaseFragment
import com.example.mct.data.model.TransactionHistoryData
import com.example.mct.data.pref.AppPrefHelper
import com.example.mct.data.vo.LoginUserDataVO
import com.example.mct.presentation.adapter.TransactionHistoryListsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<HomePresenter>(),HomeView {
    var rvTransactionHistory: RecyclerView? = null
    var ivProfile: ImageView? = null
    var tvName: TextView? = null
    var lblCredit: TextView? = null
    var cvTransactionHistory: CardView? = null

    var transactionHistoryAdapter: TransactionHistoryListsAdapter? = null

    var transactionHistoryLists: MutableList<TransactionHistoryData>? = ArrayList()

    var pref: AppPrefHelper? = null
    var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = AppPrefHelper.getInstance(requireContext())
        if (pref?.getStringValue(AppPrefHelper.USER_TYPE) != null){
            userType = pref?.getStringValue(AppPrefHelper.USER_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvName = view.findViewById(R.id.tvName)
        ivProfile = view.findViewById(R.id.ivProfile)
        rvTransactionHistory = view.findViewById(R.id.rvTransactionHistory)
        cvTransactionHistory = view.findViewById(R.id.cvTransactionHistory)
        lblCredit = view.findViewById(R.id.lblCredit)

        bindAdapter()
        loadData()
        mockData()

        if (userType.equals("user")){
            tvName?.text = "Hello, Sir"
            lblCredit?.text = "Account credit amount"

            cvTransactionHistory?.visibility = View.VISIBLE
        }else{
            tvName?.text = "Admin"
            lblCredit?.text = "Admin Dashboard"

            cvTransactionHistory?.visibility = View.GONE
        }
    }

    fun loadData(){
        presenter.getUserInfo()
    }
    private fun bindAdapter(){
        transactionHistoryAdapter = TransactionHistoryListsAdapter(requireContext())
        var llSubUserLists = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        rvTransactionHistory?.layoutManager = llSubUserLists
        rvTransactionHistory?.adapter = transactionHistoryAdapter
    }

    fun mockData(){
        var data = TransactionHistoryData("01/02/2023","1289000","120.8","Paid")
        transactionHistoryLists?.add(data)
        var data1 = TransactionHistoryData("12/03/2023","349999","100.0","Paid")
        transactionHistoryLists?.add(data1)
        var data2 = TransactionHistoryData("18/03/2023","900300","120.0","Paid")
        transactionHistoryLists?.add(data2)
        var data3 = TransactionHistoryData("16/04/2023","904899","790.0","Paid")
        transactionHistoryLists?.add(data3)
        var data4 = TransactionHistoryData("25/05/2023","399044","109.8","Paid")
        transactionHistoryLists?.add(data4)
        var data5 = TransactionHistoryData("31/05/2023","899444","103.8","Paid")
        transactionHistoryLists?.add(data5)
        transactionHistoryAdapter?.setNewData(transactionHistoryLists as MutableList<TransactionHistoryData>)
    }

    override fun instantiatePresenter(): HomePresenter {
        return HomePresenter(this, AppPrefHelper.getInstance(requireContext()))
    }

    override fun showHome() {

    }

    override fun showProfileData(data: LoginUserDataVO) {
        if (data.nickName != null){
            tvName?.text = data.nickName
        }

        if (data.imgUrl != null){
            Glide.with(this)
                .load(data.imgUrl)
                .into(ivProfile!!)
        }
    }

    override fun showLoading() {
        mProgressDialog?.show()
    }

    override fun hideLoading() {
        mProgressDialog?.hide()
    }

    override fun showSuccess(message: String) {

    }

    override fun showError(error: String) {

    }
}