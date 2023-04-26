package com.example.catalogservice.service;

import com.example.catalogservice.domain.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
