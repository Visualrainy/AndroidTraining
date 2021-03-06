package com.tw.training.catkeeper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.EditText
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.presenter.LoginContract
import com.tw.training.catkeeper.presenter.LoginPresenter
import com.tw.training.catkeeper.utils.AnimationUtil
import com.tw.training.catkeeper.utils.KeyboardWatcher

class LoginActivity : AppCompatActivity(), KeyboardWatcher.SoftKeyboardStateListener, LoginContract.View {
    private val TAG = "LoginActivity"
    private var screenHeight = 0

    private var presenter: LoginPresenter = LoginPresenter(this)
    private lateinit var mBtnLogin: View

    private lateinit var mLogoLayout: View
    private lateinit var mContent: View
    private lateinit var mUserName: EditText
    private lateinit var mPassword: EditText
    private lateinit var keyboardWatcher: KeyboardWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d(TAG, "onCreate")

        mLogoLayout = findViewById(R.id.logo_layout)
        mBtnLogin = findViewById(R.id.btn_login)
        mContent = findViewById(R.id.content)
        mUserName = findViewById(R.id.input_username)
        mPassword = findViewById(R.id.input_pwd)

        screenHeight = resources.displayMetrics.heightPixels
        keyboardWatcher = KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT))
        keyboardWatcher.addSoftKeyboardStateListener(this)

        mBtnLogin.setOnClickListener {
            presenter.startLogin(mUserName.text.toString(), mPassword.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun loginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        keyboardWatcher.removeSoftKeyboardStateListener(this)
    }

    override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
        val location = IntArray(2)
        mContent.getLocationOnScreen(location)
        val bottom = screenHeight - (location[1] + mContent.height)

        if (keyboardHeightInPx > bottom) {
            val dist = (keyboardHeightInPx - bottom).toFloat()

            AnimationUtil.moveContentUp(mContent, dist)
            AnimationUtil.zoomIn(mLogoLayout, dist)
        }
    }

    override fun onSoftKeyboardClosed() {
        AnimationUtil.moveContentDown(mContent)
        AnimationUtil.zoomOut(mLogoLayout)
    }
}
