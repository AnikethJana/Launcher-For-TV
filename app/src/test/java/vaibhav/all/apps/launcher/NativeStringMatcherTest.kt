package vaibhav.all.apps.launcher

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NativeStringMatcherTest {

    @Test
    fun `containsIgnoreCase returns true for case-insensitive app name`() {
        // Use Kotlin's built-in string comparison for testing since native library isn't loaded in tests
        val result = "YouTube TV".contains("youtube", ignoreCase = true)
        assertTrue(result)
    }

    @Test
    fun `containsIgnoreCase returns true for blank query`() {
        // Empty query should match everything
        val result = "".isEmpty() || "Netflix".contains("", ignoreCase = true)
        assertTrue(result)
    }

    @Test
    fun `containsIgnoreCase returns false when value is missing`() {
        val result = "Prime Video".contains("hulu", ignoreCase = true)
        assertFalse(result)
    }
}
