package net.atos.beerapi.service;

import net.atos.beerapi.model.Beer;

import java.util.List;

public interface IBeerService {

    List<Beer> getAllBeers();

    Beer getBeerById(Long id);

    Beer saveBeer(Beer beer);

    void deleteBeer(Long id);
}
