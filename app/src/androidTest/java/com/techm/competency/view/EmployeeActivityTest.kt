package com.techm.competency.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techm.competency.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EmployeeActivityTest1 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(EmployeeActivity::class.java)

    @Test
    fun employeeActivityTest() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_add), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val textInputEditText = onView(
            childAtPosition(
                childAtPosition(
                    withId(R.id.textFieldName),
                    0
                ),
                0
            )
        )
        textInputEditText.perform(scrollTo(), replaceText("himanshu doshi"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            childAtPosition(
                childAtPosition(
                    withId(R.id.textFieldBand),
                    0
                ),
                0
            )
        )
        textInputEditText2.perform(scrollTo(), replaceText("U4"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            childAtPosition(
                childAtPosition(
                    withId(R.id.textFieldDesignation),
                    0
                ),
                0
            )
        )
        textInputEditText3.perform(scrollTo(), replaceText("Tech Lead"), closeSoftKeyboard())

             val appCompatRadioButton = onView(
            allOf(
                withId(R.id.rBtnAndroid), withText("Android"),
                childAtPosition(
                    allOf(
                        withId(R.id.rBtnGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    0
                )
            )
        )
        appCompatRadioButton.perform(scrollTo(), click())

        val editText = onView(
            allOf(
                withText("himanshu doshi"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("himanshu doshi")))

        val editText2 = onView(
            allOf(
                withText("U4"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldBand),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("U4")))

        val editText3 = onView(
            allOf(
                withText("Tech Lead"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldDesignation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText3.check(matches(withText("Tech Lead")))

        val radioButton = onView(
            allOf(
                withId(R.id.rBtnAndroid),
                childAtPosition(
                    allOf(
                        withId(R.id.rBtnGroup),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            6
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        radioButton.check(matches(isDisplayed()))



        val editText6 = onView(
            allOf(
                withText("Tech Lead"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldDesignation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText6.check(matches(withText("null")))

        val editText7 = onView(
            allOf(
                withText("U4"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldBand),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText7.check(matches(withText("null")))

        val editText8 = onView(
            allOf(
                withText("himanshu doshi"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textFieldName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText8.check(matches(withText("null")))

        val spinner = onView(
            allOf(
                withId(R.id.spinnerProject),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        7
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        spinner.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.addProject),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        7
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.buttonSave),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
