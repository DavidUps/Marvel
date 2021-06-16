package com.davidups.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineTestRule @ExperimentalCoroutinesApi constructor(
    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {
    @ExperimentalCoroutinesApi
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}
