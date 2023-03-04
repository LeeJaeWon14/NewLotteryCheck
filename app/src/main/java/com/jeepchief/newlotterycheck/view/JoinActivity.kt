package com.jeepchief.newlotterycheck.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jeepchief.newlotterycheck.BaseActivity
import com.jeepchief.newlotterycheck.R
import com.jeepchief.newlotterycheck.databinding.ActivityJoinBinding
import com.jeepchief.newlotterycheck.util.Log
import com.jeepchief.newlotterycheck.view.main.MainActivity

class JoinActivity : BaseActivity() {
    private lateinit var binding: ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        binding.apply {
            btnJoin.setOnClickListener {
                if(edtEmail.text.isEmpty() || edtPassword.text.isEmpty()) {
                    Toast.makeText(this@JoinActivity, getString(R.string.msg_not_allowed_empty), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                auth.createUserWithEmailAndPassword(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                ).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        // create account success
                        Toast.makeText(this@JoinActivity, getString(R.string.msg_account_create_success), Toast.LENGTH_SHORT).show()
                    }
                    if(task.exception?.message.isNullOrEmpty().not()) {
                        // Occurred something exception
                    }
                    else {
                        // Firebase already have auth
                        AlertDialog.Builder(this@JoinActivity)
                            .setTitle("Auth")
                            .setMessage("이미 존재하는 아이디입니다.")
                            .setPositiveButton("확인", null)
                    }
                }
            }
        }
    }

    private fun signInAccount(id: String, pw: String) {
        auth.signInWithEmailAndPassword(id, pw).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    // put bundle
                })
            }
            else {
                Log.e("Firebase auth occurred exception !! >> ${task.exception?.message}")
                Toast.makeText(this@JoinActivity, getString(R.string.msg_account_create_fail), Toast.LENGTH_SHORT).show()
            }
        }
    }
}