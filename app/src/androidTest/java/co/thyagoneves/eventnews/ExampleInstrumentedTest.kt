package co.thyagoneves.eventnews

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.thyagoneves.eventnews.adapters.EventsAdapter
import co.thyagoneves.eventnews.views.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule
    var activityTestRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnRecyclerViewItem(){
        Thread.sleep(2000)
        onView(withId(R.id.rv_events)).perform(scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)
        onView(withId(R.id.rv_events)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder> (1, click()))
        Thread.sleep(2000)
        onView(withId(R.id.et_nome)).perform(typeText("Thyago Neves"))
        closeSoftKeyboard()
        onView(withId(R.id.et_email)).perform(typeText("thyagoneves.sa@gmail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_checkin)).perform(click())
    }
}