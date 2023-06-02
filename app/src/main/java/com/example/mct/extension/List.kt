package com.example.mct.extension

import kotlin.reflect.KProperty1


inline fun <reified T, Y> List<T>.listOfField(property: KProperty1<T, Y?>): List<Y> {
    val yy = ArrayList<Y>()
    this.forEach { t: T ->
        yy.add(property.get(t) as Y)
    }
    return yy
}