package com.example.catalogservice.service;

import com.example.catalogservice.domain.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository calogRepository;

    public CatalogServiceImpl(CatalogRepository calogRepository) {
        this.calogRepository = calogRepository;
    }

    @Override
    public Iterable<Catalog> getAllCatalogs() {
        return calogRepository.findAll();
    }
}
