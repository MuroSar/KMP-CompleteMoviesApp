package com.murosar.kmp.completemoviesapp.ui.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class MoneyFormatUtilsTest {

    @Test
    fun `should format properly`() {
        assertThat(10.toAmountFormat()).isEqualTo("10")
        assertThat(100.toAmountFormat()).isEqualTo("100")
        assertThat(1000.toAmountFormat()).isEqualTo("1.000")
        assertThat(10000.toAmountFormat()).isEqualTo("10.000")
        assertThat(100000.toAmountFormat()).isEqualTo("100.000")
        assertThat(1000000.toAmountFormat()).isEqualTo("1.000.000")
        assertThat(10000000.toAmountFormat()).isEqualTo("10.000.000")
    }
}