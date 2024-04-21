package com.example.appmusic

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmusic.databinding.ActivityAdminLoginBinding
import com.example.appmusic.databinding.ActivityLoginBinding

class AdminLogin : AppCompatActivity() {
    lateinit var binding: ActivityAdminLoginBinding
    lateinit var databaseAdmin: DatabaseAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        databaseAdmin = DatabaseAdmin(this)

        binding.loginBtn.setOnClickListener {
            val loginName = binding.loginName.text.toString()
            val loginPass = binding.loginPass.text.toString()

            if (loginName.isEmpty() || loginPass.isEmpty()) {
                showErrorDialog("Vui lòng nhập dữ liệu!")
            } else {
                val admin = databaseAdmin.checkPass(loginName, loginPass)
                if (admin != null) {
                    showResultDialog("Đăng nhập thành công!")
                    val intent = Intent(this, AdminActivity::class.java)

                    //lưu dữ liệu acc
                    intent.putExtra("admin_name", admin.getAdminName());

                    startActivity(intent)
                    finish()
                } else {
                    showErrorDialog("Đăng nhập thất bại!")
                }
            }
        }


        binding.showPasswordCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                // Hiển thị mật khẩu
                binding.loginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

            } else {
                // Ẩn mật khẩu
                binding.loginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }


        binding.loginToSignup.setOnClickListener {
            val intent = Intent(this, AdminSignUp::class.java)
            startActivity(intent)
        }
    }

    private fun showResultDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
}
