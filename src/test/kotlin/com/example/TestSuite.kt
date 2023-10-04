package com.example

import com.example.testRepositories.WatchListsTest
import com.example.testRouting.WatchRoutingTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WatchListsTest::class,
    WatchRoutingTest::class
)
class TestSuite