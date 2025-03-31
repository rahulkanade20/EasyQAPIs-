package org.example.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface EasyQApiServiceI {
    <T, Q> ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Map<String, Object> queryParams, Class<T> responseType);
    <T> ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> responseType);
    <T, P> ResponseEntity<T> post(String url, HttpHeaders httpHeaders, P requestBody, Class<T> responseType);
    <T, P> ResponseEntity<T> put(String url, HttpHeaders httpHeaders, P requestBody, Class<T> responseType);
    <T> ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Class<T> responseType);
}
