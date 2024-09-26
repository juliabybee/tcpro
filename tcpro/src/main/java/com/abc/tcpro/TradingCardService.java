package com.abc.tcpro;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abc.tcpro.TradingCard;
import com.abc.tcpro.TradingCardRepository;
import com.abc.tcpro.TradingCardController;

@Service
public class TradingCardService {

    private final TradingCardRepository repository;

    @Autowired
    public TradingCardService(final TradingCardRepository repository) {
        this.repository = repository;
    }

    public Page<TradingCard> getTradingCards(final int pageNumber, final int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<TradingCard> getTradingCard(final UUID cardNo) {
        return repository.findById(cardNo);
    }

    public TradingCard saveTradingCard(final TradingCard tradingCard) {
        return repository.save(tradingCard);
    }

    public void deleteTradingCard(final UUID cardNo) {
        repository.deleteById(cardNo);
    }
}

