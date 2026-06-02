package vaibhav.all.apps.launcher

import org.junit.Test
import kotlin.system.measureTimeMillis

// Mock NativeStringMatcher just for benchmark.
object MockNativeStringMatcher {
    fun containsIgnoreCase(text: String, query: String): Boolean {
        if (query.isBlank()) return true
        return text.contains(query, ignoreCase = true)
    }
}

class PerformanceBenchmarkTest {

    @Test
    fun benchmarkFiltering() {
        val testApps = (1..1000).map { i ->
            AppItem(
                id = "id_$i",
                name = "App Name $i",
                packageName = "com.example.app$i",
                isSystem = true,
                brandColor = androidx.compose.ui.graphics.Color.Black,
                description = "",
                category = "",
                rating = "",
                specs = emptyList(),
                mockShow = "",
                launchIntent = null,
                iconDrawable = null
            )
        }

        val searchQuery = ""

        // Warmup
        for (i in 1..100) {
            testApps.filter {
                MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery) ||
                        MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery)
            }
        }

        val oldTime = measureTimeMillis {
            for (i in 1..1000) {
                testApps.filter {
                    MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery) ||
                            MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery)
                }
            }
        }
        println("Old approach time (searchQuery blank): $oldTime ms")

        val newTime = measureTimeMillis {
            for (i in 1..1000) {
                if (searchQuery.isBlank()) {
                    testApps
                } else {
                    testApps.filter {
                        MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery) ||
                                MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery)
                    }
                }
            }
        }
        println("New approach time (searchQuery blank): $newTime ms")


        val searchQuery2 = "123"

        // Warmup
        for (i in 1..100) {
            testApps.filter {
                MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery2) ||
                        MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery2)
            }
        }

        val oldTime2 = measureTimeMillis {
            for (i in 1..1000) {
                testApps.filter {
                    MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery2) ||
                            MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery2)
                }
            }
        }
        println("Old approach time (searchQuery present): $oldTime2 ms")

        val newTime2 = measureTimeMillis {
            for (i in 1..1000) {
                if (searchQuery2.isBlank()) {
                    testApps
                } else {
                    testApps.filter {
                        MockNativeStringMatcher.containsIgnoreCase(it.name, searchQuery2) ||
                                MockNativeStringMatcher.containsIgnoreCase(it.packageName, searchQuery2)
                    }
                }
            }
        }
        println("New approach time (searchQuery present): $newTime2 ms")
    }
}
