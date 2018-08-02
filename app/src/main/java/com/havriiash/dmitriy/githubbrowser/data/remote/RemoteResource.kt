package com.havriiash.dmitriy.githubbrowser.data.remote

class RemoteResource<D> private constructor(
        val state: State,
        val data: D?,
        val throwable: Throwable?
) {

    enum class State {
        LOADING,
        ERROR,
        SUCCESS
    }

    companion object {
        fun <D> loading(): RemoteResource<D> = RemoteResource(State.LOADING, null, null)

        fun <D> error(throwable: Throwable): RemoteResource<D> = RemoteResource(State.ERROR, null, throwable)

        fun <D> success(data: D): RemoteResource<D> = RemoteResource(State.SUCCESS, data, null)
    }
}