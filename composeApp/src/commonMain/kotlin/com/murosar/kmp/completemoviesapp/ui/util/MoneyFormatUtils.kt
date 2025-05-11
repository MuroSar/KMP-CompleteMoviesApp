package com.murosar.kmp.completemoviesapp.ui.util

import com.murosar.kmp.completemoviesapp.domain.utils.DOT_STRING
import com.murosar.kmp.completemoviesapp.domain.utils.THREE_INT

fun Int.toAmountFormat(): String =
    toString()
        .reversed()
        .chunked(THREE_INT)
        .joinToString(DOT_STRING)
        .reversed()
