package dev.bug.bwitter.web.models

data class UpdatingRequest(
    override val phone: String
): Request(null, null, null, null, phone)
