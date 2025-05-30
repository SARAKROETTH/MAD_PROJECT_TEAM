package com.example.mad_project.repositories;

public interface IApiCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}

