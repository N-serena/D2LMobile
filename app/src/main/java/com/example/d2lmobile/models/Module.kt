package com.example.d2lmobile.models

import java.io.Serializable

data class ModuleLink(
    val name: String = "",
    val type: String = "",
    val url: String = ""
) : Serializable

data class Module(
    val links: List<ModuleLink> = emptyList()
)

