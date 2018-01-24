package me.ernestzamelczyk.espeotask.ui.main

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.ui.utils.PicassoCircleTransfromation
import javax.inject.Inject
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.annotation.SuppressLint
import me.ernestzamelczyk.espeotask.ui.map.MapActivity


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val PERMISSION_REQUEST_CALL_PHONE = 1
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        Picasso.with(this).load(R.drawable.profile).transform(PicassoCircleTransfromation()).into(profilePicture)
        phoneButton.setOnClickListener(this::onPhoneClick)
        mapButton.setOnClickListener(this::onMapClick)
    }

    private fun onMapClick(v: View) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    private fun onPhoneClick(v: View) {
        when {
            checkPermission(Manifest.permission.CALL_PHONE) -> callMe()
            ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,
                    Manifest.permission.CALL_PHONE) -> {
                showRequestPhoneCallPermissionDialog()
            }
            else -> requestPhoneCallPermissions()
        }

    }

    @SuppressLint("MissingPermission")
    private fun callMe() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:+48731032530")
        startActivity(intent)
    }

    private fun showRequestPhoneCallPermissionDialog() {
        val dialog = AlertDialog
                .Builder(this)
                .setCancelable(false)
                .setTitle("Permissions request")
                .setMessage("If you want to make a call you must give calling permissions to the application")

        dialog.setPositiveButton("Ok", { _, _ -> requestPhoneCallPermissions() })
        dialog.setNegativeButton("Cancel", { _, _ -> })
        dialog.show()
    }



    private fun requestPhoneCallPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSION_REQUEST_CALL_PHONE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CALL_PHONE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    callMe()
                }
                return
            }
            else -> return
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

}
