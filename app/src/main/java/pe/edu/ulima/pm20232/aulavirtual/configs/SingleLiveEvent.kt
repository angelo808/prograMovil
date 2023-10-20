package pe.edu.ulima.pm20232.aulavirtual.configs

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = MutableLiveData<Boolean>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t ->
            if (pending.value == true) {
                pending.value = false
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(t: T?) {
        pending.value = true
        super.setValue(t)
    }
}