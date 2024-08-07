package com.celaenoapps.flavorquest.testutils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A [TestRule] that initializes the main dispatcher to [dispatcher], which defaults to a
 * [StandardTestDispatcher].
 */
class TestDispatcherRule(
    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

) : TestRule {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(dispatcher)
                base.evaluate()
                Dispatchers.resetMain()
            }
        }
}