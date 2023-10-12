package com.example

import com.example.testRepositories.WatchListsTest
import com.example.testRouting.WatchRoutingTest
import com.example.testServices.WatchServiceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WatchListsTest::class,
    WatchRoutingTest::class,
    WatchServiceTest::class
)
class TestSuite