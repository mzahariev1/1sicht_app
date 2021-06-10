package controller

import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals


class AdminControllerTest {

    @Test
    fun testPingCreate() = testServer {
        handleRequest(HttpMethod.Post, "/admins").apply {
            assertEquals(500, response.status()?.value)
            assertEquals(null, response.content)
        }
    }

    @Test
    fun testPingGet() = withTestApplication({  }) {
        handleRequest(HttpMethod.Get, "/admins/get").apply {
            //assertEquals(404, response.status()?.value)
            assertEquals(null, response.content)
        }
    }

    private fun testServer(callback: TestApplicationEngine.() -> Unit): Unit {

    }
}