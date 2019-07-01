package com.mind.postcallrecyclerview

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_entersal_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    val dataAdapter = DataAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvSalarlyDetail.adapter = dataAdapter

        ivAddData.setOnClickListener {
            openAlertDialog()
        }
    }

    private fun getDialogSalrlyData(empName: String, empSalarly: String, empAge: String) {
        val progressdialog = ProgressDialog(this)
        progressdialog.setMessage("Please Wait....")
        progressdialog.show()
        APIUserClient.apiInterface.getUserData(SalaryDetailModel(empName, empSalarly, empAge))
                .enqueue(object : Callback<UserSalDetailsResponce> {
                    override fun onFailure(call: Call<UserSalDetailsResponce>, t: Throwable) {
                        progressdialog.dismiss()
                        Log.e(TAG, "getUserData", t)
                    }

                    override fun onResponse(call: Call<UserSalDetailsResponce>, response: Response<UserSalDetailsResponce>) {
                        progressdialog.dismiss()
                        openAlertDialog()
                        if (response.body() != null) {
                            if (response.body()!!.error == null) {
                                getSalDataList(response.body()!!)
                            } else {
                                Toast.makeText(this@MainActivity, response.body()!!.error?.text, Toast.LENGTH_LONG).show()
                            }
                        }
//                        response.errorBody()
                    }
                })
    }

    private fun getSalDataList(userDataModelresponce: UserSalDetailsResponce) {
        dataAdapter.addDataList(userDataModelresponce)
    }

    private fun openAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.alert_msg))
        builder.setTitle(getString(R.string.enter_msg))
        val view = layoutInflater.inflate(R.layout.custom_dialog_entersal_detail, null)

        builder.setView(view)
        builder.setPositiveButton(getString(android.R.string.ok)) { dialogInterface, i ->

            val empSalarly: String = view.etSalary?.text.toString()
            val empName: String = view.etName?.text.toString()
            val empAge: String = view.etAge?.text.toString()

            getDialogSalrlyData(empName, empSalarly, empAge)
            dialogInterface?.dismiss()
        }
        builder.setNegativeButton(getString(android.R.string.cancel)) { dialogInterface, i ->

            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog?.show()
    }

    private fun validateDialogInputs(empName: String, empSalarly: String, empAge: String) {
        if (empSalarly != null && empAge != null && empName != null) {
            Toast.makeText(this, "Please Enter Proper Details..", Toast.LENGTH_LONG).show()

        }
    }
}
