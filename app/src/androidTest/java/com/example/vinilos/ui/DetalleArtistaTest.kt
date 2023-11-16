package com.example.vinilos.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetalleArtistaTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun detalleArtistaTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.artistasFragment), withContentDescription("Artistas"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(
                withText("Artistas"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Artistas")))

        val textView2 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Artistas"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.artistasFragment),
                                withContentDescription("Artistas")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Artistas")))

        val frameLayout = onView(
            allOf(
                withId(R.id.artistasFragment), withContentDescription("Artistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        val recyclerView = onView(
            allOf(
                withId(R.id.artistasRv),
                childAtPosition(
                    withClassName(`is`("android.widget.FrameLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(1000)

        val textView3 = onView(
            allOf(
                withText("Detalle Artista"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Detalle Artista")))

        val textView4 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Artistas"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.artistasFragment),
                                withContentDescription("Artistas")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Artistas")))

        val imageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.my_toolbar),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
        Thread.sleep(1000)

        val textView5 = onView(
            allOf(
                withText("Artistas"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Artistas")))

        val textView6 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Artistas"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.artistasFragment),
                                withContentDescription("Artistas")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Artistas")))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.artistasFragment), withContentDescription("Artistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))
        Thread.sleep(1000)
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
