import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.example.d2lmobile.activities.DashboardActivity
import com.example.d2lmobile.activities.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isLoggedIn()) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1500)
    }

    private fun isLoggedIn(): Boolean {
        val prefs = getSharedPreferences("USER_PREFS", MODE_PRIVATE)
        return prefs.getBoolean("LOGGED_IN", false)
    }
}