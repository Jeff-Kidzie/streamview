package com.kidzie.streamview.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * Base ViewModel implementing MVI pattern with Redux-like state management.
 *
 * @param E Event type for user interactions
 * @param VS ViewState type representing UI state
 * @param EF Effect type for one-time side effects
 * @param initialState Initial state of the ViewModel
 */
abstract class BaseViewModel<E : Event, VS : ViewState, EF : Effect>(
    initialState: VS
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<VS> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EF>(
        extraBufferCapacity = 1 // Prevent blocking when emitting effects
    )
    val effect: SharedFlow<EF> = _effect.asSharedFlow()

    protected abstract fun reduce(event: E, currentState: VS): VS

    protected open suspend fun handleSideEffect(event: E, newState: VS) {
        // Default implementation does nothing
    }

    fun onEvent(event: E) {
        (viewModelScope + exceptionHandler).launch {
            val oldState = _state.value
            val newState = reduce(event, oldState)

            _state.update { newState }

            // Handle side effects after state update
            handleSideEffect(event, newState)
        }
    }

    protected fun updateState(transform: (VS) -> VS) {
        _state.update(transform)
    }

    /**
     * Emits a one-time effect to the UI.
     * Effects are consumed once and don't persist across configuration changes.
     */
    protected fun emitEffect(effect: EF) {
        _effect.tryEmit(effect) // Non-blocking emission
    }

    protected open fun handleError(exception: Throwable) {
        exception.printStackTrace()
    }

    protected inline fun VS.copy(block: VS.() -> VS): VS = this.block()
}

interface Event
interface Effect
interface ViewState