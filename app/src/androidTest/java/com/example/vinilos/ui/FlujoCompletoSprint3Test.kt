package com.example.vinilos.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FlujoCompletoSprint3Test {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun flujoCompletoSprint3Test() {
            Thread.sleep(1000)
        //Sprint 3
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.coleccionistasFragment), withContentDescription("Tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
            Thread.sleep(1000)

        val textView = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Tracks"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.coleccionistasFragment),
                                withContentDescription("Tracks")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Tracks")))

        val frameLayout = onView(
            allOf(
                withId(R.id.coleccionistasFragment), withContentDescription("Tracks"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withText("Tracks"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Tracks")))

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.collectorFragment), withContentDescription("Coleccionistas"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())
            Thread.sleep(1000)

        val textView3 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Coleccionistas"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.collectorFragment),
                                withContentDescription("Coleccionistas")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Coleccionistas")))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.collectorFragment), withContentDescription("Coleccionistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withText("Listado de coleccionistas"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Listado de coleccionistas")))

        val recyclerView = onView(
            allOf(
                withId(R.id.fragments_rv),
                childAtPosition(
                    withClassName(`is`("android.widget.FrameLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
            Thread.sleep(1000)

        val textView5 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Coleccionistas"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.collectorFragment),
                                withContentDescription("Coleccionistas")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Coleccionistas")))

        val frameLayout3 = onView(
            allOf(
                withId(R.id.collectorFragment), withContentDescription("Coleccionistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout3.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withText("Detalle Coleccionista"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Detalle Coleccionista")))

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

        val bottomNavigationItemView3 = onView(
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
        bottomNavigationItemView3.perform(click())
            Thread.sleep(1000)

        val textView7 = onView(
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
        textView7.check(matches(withText("Artistas")))

        val frameLayout4 = onView(
            allOf(
                withId(R.id.artistasFragment), withContentDescription("Artistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout4.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withText("Listado de artistas"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("Listado de artistas")))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.artistasRv),
                childAtPosition(
                    withClassName(`is`("android.widget.FrameLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
            Thread.sleep(1000)

        val textView9 = onView(
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
        textView9.check(matches(withText("Artistas")))

        val frameLayout5 = onView(
            allOf(
                withId(R.id.artistasFragment), withContentDescription("Artistas"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout5.check(matches(isDisplayed()))

        val textView10 = onView(
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
        textView10.check(matches(withText("Detalle Artista")))

        val imageButton2 = onView(
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
        imageButton2.check(matches(isDisplayed()))

        val bottomNavigationItemView4 = onView(
            allOf(
                withId(R.id.albumFragment), withContentDescription("Albums"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView4.perform(click())
            Thread.sleep(1000)

        val textView11 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Albums"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.albumFragment),
                                withContentDescription("Albums")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView11.check(matches(withText("Albums")))

        val frameLayout6 = onView(
            allOf(
                withId(R.id.albumFragment), withContentDescription("Albums"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout6.check(matches(isDisplayed()))

        val textView12 = onView(
            allOf(
                withText("Listado de albumes"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView12.check(matches(withText("Listado de albumes")))

        val imageButton3 = onView(
            allOf(
                withId(R.id.crearAlbumFloatingButton), withContentDescription("Crear album"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        imageButton3.check(matches(isDisplayed()))

        val imageButton4 = onView(
            allOf(
                withId(R.id.asociar_track_button), withContentDescription("Asociar tracks"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        imageButton4.check(matches(isDisplayed()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.albumsRv),
                childAtPosition(
                    withClassName(`is`("android.widget.FrameLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
            Thread.sleep(1000)

        val textView13 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Albums"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.albumFragment),
                                withContentDescription("Albums")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView13.check(matches(withText("Albums")))

        val frameLayout7 = onView(
            allOf(
                withId(R.id.albumFragment), withContentDescription("Albums"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout7.check(matches(isDisplayed()))

        val textView14 = onView(
            allOf(
                withText("Detalle Album"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView14.check(matches(withText("Detalle Album")))

        val imageButton5 = onView(
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
        imageButton5.check(matches(isDisplayed()))

        val appCompatImageButton2 = onView(
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
        appCompatImageButton2.perform(click())
            Thread.sleep(1000)

        val floatingActionButton = onView(
            allOf(
                withId(R.id.crearAlbumFloatingButton), withContentDescription("Crear album"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())
        Thread.sleep(1000)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.album_nombre),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Prueba album 1 flujo completo"), closeSoftKeyboard())
            Thread.sleep(1000)

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.album_cover),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("\uD83D\uDE00"), closeSoftKeyboard())
            Thread.sleep(1000)

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.album_fecha_lanzamiento),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(click())
            Thread.sleep(1000)

        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())
            Thread.sleep(1000)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinner_genre),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())
            Thread.sleep(1000)

            val materialTextView = onData(anything())
                    .inRoot(
                            RootMatchers.isPlatformPopup()
                    ).
                    atPosition(3)
            materialTextView.perform(click())
            Thread.sleep(1000)

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.spinner_recordLabel),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        appCompatSpinner2.perform(click())
            Thread.sleep(1000)

            val materialTextView2 = onData(anything())
                    .inRoot(
                            RootMatchers.isPlatformPopup()
                    ).
                    atPosition(1)
            materialTextView2.perform(click())
            Thread.sleep(1000)

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.album_descripcion),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(
            replaceText("Prueba creacion album flujo completo"),
            closeSoftKeyboard()
        )
            Thread.sleep(1000)

        val button = onView(
            allOf(
                withId(R.id.cancel_album_button), withText("VOLVER"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.post_album_button), withText("CREAR"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val textView15 = onView(
            allOf(
                withText("Crear album"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView15.check(matches(withText("Crear album")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.post_album_button), withText("Crear"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        3
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
            Thread.sleep(1000)

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.crearAlbumFloatingButton), withContentDescription("Crear album"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())
            Thread.sleep(1000)

        val textView16 = onView(
            allOf(
                withText("Crear album"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView16.check(matches(withText("Crear album")))

        val button3 = onView(
            allOf(
                withId(R.id.cancel_album_button), withText("VOLVER"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))

        val button4 = onView(
            allOf(
                withId(R.id.post_album_button), withText("CREAR"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button4.check(matches(isDisplayed()))

        val materialButton3 = onView(
            allOf(
                withId(R.id.cancel_album_button), withText("Volver"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
            Thread.sleep(1000)

        val textView17 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Albums"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_labels_group),
                        withParent(
                            allOf(
                                withId(R.id.albumFragment),
                                withContentDescription("Albums")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView17.check(matches(withText("Albums")))

        val frameLayout8 = onView(
            allOf(
                withId(R.id.albumFragment), withContentDescription("Albums"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout8.check(matches(isDisplayed()))

        val textView18 = onView(
            allOf(
                withText("Listado de albumes"),
                withParent(
                    allOf(
                        withId(R.id.my_toolbar),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView18.check(matches(withText("Listado de albumes")))

        val imageButton6 = onView(
            allOf(
                withId(R.id.crearAlbumFloatingButton), withContentDescription("Crear album"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        imageButton6.check(matches(isDisplayed()))

        val imageButton7 = onView(
            allOf(
                withId(R.id.asociar_track_button), withContentDescription("Asociar tracks"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        imageButton7.check(matches(isDisplayed()))

        val floatingActionButton3 = onView(
            allOf(
                withId(R.id.asociar_track_button), withContentDescription("Asociar tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton3.perform(click())
            Thread.sleep(2000)

        val button5 = onView(
            allOf(
                withId(R.id.cancel_track_button), withText("VOLVER"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button5.check(matches(isDisplayed()))

        val button6 = onView(
            allOf(
                withId(R.id.post_track_button), withText("ASOCIAR TRACKS"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button6.check(matches(isDisplayed()))

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.track_nombre),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(
            replaceText("Asociar track prueba flujo completo"),
            closeSoftKeyboard()
        )
            Thread.sleep(1000)

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.track_duration),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("5:05"), closeSoftKeyboard())
            Thread.sleep(1000)

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.album_id_asociar_text),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("100"), closeSoftKeyboard())
            Thread.sleep(1000)

        val materialButton4 = onView(
            allOf(
                withId(R.id.post_track_button), withText("Asociar tracks"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
            Thread.sleep(1000)

        val floatingActionButton4 = onView(
            allOf(
                withId(R.id.asociar_track_button), withContentDescription("Asociar tracks"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton4.perform(click())
            Thread.sleep(1000)

        val materialButton5 = onView(
            allOf(
                withId(R.id.cancel_track_button), withText("Volver"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())
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
