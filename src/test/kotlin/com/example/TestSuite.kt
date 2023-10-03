package com.example

import com.example.repositories.WatchListsTest
import com.example.routingTest.WatchRoutingTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WatchListsTest::class,
    WatchRoutingTest::class
)
class TestSuite