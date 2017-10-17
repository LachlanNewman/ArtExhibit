package com.lachlannewman.artexhibit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.lachlannewman.artexhibit.adapters.GalleryAdapter;
import com.lachlannewman.artexhibit.models.Artwork;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    GalleryAdapterTest galleryAdapterTest;
    ExampleInstrumentedTest(){
        galleryAdapterTest = new GalleryAdapterTest();
        galleryAdapterTest.setUp();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lachlannewman.artexhibit", appContext.getPackageName());

    }

    @Test
    public void testGetItem(){
        galleryAdapterTest.testGetItem();
    }

    @Test
    public void testGetItemId(){
        galleryAdapterTest.testGetItemId();
    }

    @Test
    public void testGetCount(){
        galleryAdapterTest.testGetCount();
    }

    @Test
    public void testGetView(){
        galleryAdapterTest.testGetView();
    }
}
