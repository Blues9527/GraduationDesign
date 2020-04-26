
import android.app.Activity
import android.content.Intent
import com.blues.mvvmdemo.util.putExtras

/**
 * Created by xiaojianjun on 2019-10-17.
 */
object ActivityUtil {

    val activities = mutableListOf<Activity>()

    fun start(clazz: Class<out Activity>, params: Map<String, Any> = emptyMap()) {
        val currentActivity = activities[activities.lastIndex]
        val intent = Intent(currentActivity, clazz)
        params.forEach {
            intent.putExtras(it.key to it.value)
        }
        currentActivity.startActivity(intent)
    }

    /**
     * finish指定的一个或多个Activity
     */
    fun finish(vararg clazz: Class<out Activity>) {
        activities.forEach { activity ->
            if (clazz.contains(activity::class.java)) {
                activity.finish()
            }
        }
    }

}
