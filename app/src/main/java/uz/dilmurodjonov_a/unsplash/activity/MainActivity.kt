package uz.dilmurodjonov_a.unsplash.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.dilmurodjonov_a.unsplash.R
import uz.dilmurodjonov_a.unsplash.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = MainFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}