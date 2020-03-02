/*
 * Copyright 2017-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.io.json

import kotlinx.serialization.*
import kotlin.test.*

class JsonUnionEnumTest : JsonTestBase() {

    enum class SomeEnum { ALPHA, BETA, GAMMA }

    @Serializable
    data class WithUnions(
        val s: String,
        val e: SomeEnum = SomeEnum.ALPHA,
        val i: Int = 42
    )

    @Test
    fun testEnum() = parametrizedTest { useStreaming ->
        val data = WithUnions("foo", SomeEnum.BETA)
        val json = strict.stringify(WithUnions.serializer(), data, useStreaming)
        val restored = strict.parse(WithUnions.serializer(), json, useStreaming)
        assertEquals(data, restored)
    }
}
