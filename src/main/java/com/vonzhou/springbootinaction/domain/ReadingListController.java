package com.vonzhou.springbootinaction.domain;

import java.util.List;

import com.vonzhou.springbootinaction.config.AmazonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/reading")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private AmazonProperties amazonProperties;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @PostConstruct
    public void init() {
        Reader r = new Reader();
        r.setUsername("vonz");
        r.setPassword("pwd");
        r.setFullname("vonzhou");
        readerRepository.save(r);
    }

    @GetMapping(value = "/{reader}")
    public String readersBooks(@PathVariable String reader, Model model) {

        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("amazonId", amazonProperties.getAssociateId());
            model.addAttribute("reader", reader);

        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/reading/{reader}";
    }
}
