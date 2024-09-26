package com.abc.tcpro;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.tcpro.TradingCard;
import com.abc.tcpro.TradingCardService;


@Controller
@RequestMapping("/tradingCards")
public class TradingCardController {
    static final int DEFAULT_PAGE_SIZE = 5;

    private final TradingCardService tradingCardService;

    @Autowired
    public TradingCardController(final TradingCardService tradingCardService) {
        this.tradingCardService = tradingCardService;
    }

    @GetMapping(value={"/", ""})
    public String index() {
        return "redirect:/tradingCards/list";
    }

    @GetMapping(value={"/list", "/list/"})
    public String list(final Model model, @RequestParam(value = "page", defaultValue = "0") final int pageNumber, 
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {
        final Page<TradingCard> page = tradingCardService.getTradingCards(pageNumber, pageSize);
        
        final int currentPageNumber = page.getNumber();
        final int previousPageNumber = page.hasPrevious() ? currentPageNumber - 1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;

        model.addAttribute("tradingCards", page.getContent());
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("pageSize", pageSize);

        return "list";
    }

    @GetMapping(value={"/view", "/view/"})
    public String view(final Model model, @RequestParam final UUID cardNo) {
        final Optional<TradingCard> record = tradingCardService.getTradingCard(cardNo);

        model.addAttribute("tradingCard", record.isPresent() ? record.get() : new TradingCard());
        model.addAttribute("cardNo", cardNo);

        return "view";

    }

    @GetMapping(value={"/add", "/add/"})
    public String add(final Model model) {
        model.addAttribute("tradingCard", new TradingCard());
        return "add";
    }

    @GetMapping(value={"/edit", "/edit/"})
    public String edit(final Model model, @RequestParam final UUID cardNo) {
        final Optional<TradingCard> record = tradingCardService.getTradingCard(cardNo);

        model.addAttribute("tradingCard", record.isPresent() ? record.get() : new TradingCard());
        model.addAttribute("cardNo", cardNo);

        return "edit";
    }

    @PostMapping(value={"/save", "/save/"})
    public String save(final Model model, @ModelAttribute final TradingCard tradingCard, final BindingResult errors) {
        // Save the trading card entity to the database
        tradingCardService.saveTradingCard(tradingCard);
        return "redirect:list";
    }

    @GetMapping(value={"/delete", "/delete/"})
    public String delete(final Model model, @RequestParam final UUID cardNo) {
        final Optional<TradingCard> record = tradingCardService.getTradingCard(cardNo);

        model.addAttribute("tradingCard", record.isPresent() ? record.get() : new TradingCard());
        model.addAttribute("cardNo", cardNo);

        return "delete";
    }

    @PostMapping(value={"/delete", "/delete/"})
    public String deletion(final Model model, @RequestParam final UUID cardNo) {
        tradingCardService.deleteTradingCard(cardNo);
        return "redirect:list";
    }

}
