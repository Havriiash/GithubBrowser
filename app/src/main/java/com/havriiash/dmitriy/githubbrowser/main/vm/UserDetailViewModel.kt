package com.havriiash.dmitriy.githubbrowser.main.vm

import android.arch.lifecycle.MutableLiveData
import com.havriiash.dmitriy.githubbrowser.data.remote.RemoteResource
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Organization
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.data.repositories.interfaces.UserRepository
import com.havriiash.dmitriy.githubbrowser.main.vm.base.BaseViewModel
import javax.inject.Inject

class UserDetailViewModel
@Inject constructor(
        model: UserRepository,
        private val userName: String
) : BaseViewModel<UserRepository>(model) {

    val userObservable: MutableLiveData<RemoteResource<User>> = MutableLiveData()
    val organizationObservable: MutableLiveData<RemoteResource<List<Organization>>> = MutableLiveData()

    fun getUserInfo() {
        disposables.add(
                model.getUserByName(userName)
                        .doOnSubscribe { userObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { user -> userObservable.value = RemoteResource.success(user) },
                                { throwable -> userObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

    fun getOrganizations() {
        disposables.add(
                model.getUserOrganizations(userName)
                        .doOnSubscribe { organizationObservable.value = RemoteResource.loading() }
                        .subscribe(
                                { organizations -> organizationObservable.value = RemoteResource.success(organizations) },
                                { throwable -> organizationObservable.value = RemoteResource.error(throwable) }
                        )
        )
    }

}