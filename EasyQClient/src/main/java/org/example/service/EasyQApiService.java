package org.example.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EasyQApiService implements EasyQApiServiceI {

    private final RestTemplate restTemplate;

    public EasyQApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T, Q> ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Map<String, Object> queryParams, Class<T> responseType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if(queryParams != null) {
//            queryParams.forEach((k, v) -> builder.queryParam(k, v)); // lambda expression
            queryParams.forEach(builder::queryParam); // method reference
        }
        return get(builder.toUriString(), httpHeaders, responseType);
    }

    @Override
    public <T> ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), responseType);
    }

    @Override
    public <T, P> ResponseEntity<T> post(String url, HttpHeaders httpHeaders, P requestBody, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestBody, httpHeaders), responseType);
    }

    @Override
    public <T, P> ResponseEntity<T> put(String url, HttpHeaders httpHeaders, P requestBody, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(requestBody, httpHeaders), responseType);
    }

    @Override
    public <T> ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), responseType);
    }
}
