package com.rakezb.gitdemo.utils.extensions

fun String?.isNotEmptyorNull(): Boolean {
    return this?.isNullOrEmpty() == false
}