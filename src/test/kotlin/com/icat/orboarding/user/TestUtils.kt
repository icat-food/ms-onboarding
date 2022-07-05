package com.icat.orboarding.user

import org.mockito.Mockito

fun <T> anyObject(type: Class<T>): T = Mockito.any(type)
