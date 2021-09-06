package ru.aGreen.openexchangerates.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aGreen.openexchangerates.client.GifLessClient;
import ru.aGreen.openexchangerates.client.GifMoreClient;

@RequestMapping
@Controller
public class GifController {
    @Autowired
    private GifMoreClient moreClient;
    @Autowired
    private GifLessClient lessClient;
    
    @GetMapping(path = "/more")
    public String gifMore(@ModelAttribute("baseSelect") String baseSelect,
                          @ModelAttribute("rateSelect") String rateSelect,
                          @ModelAttribute("courseLatest") String courseLatest,
                          @ModelAttribute("courseHistory") String courseHistory,
                          @ModelAttribute("rateDate") String rateDate,
                          Model model) {
        String moreGifResponse = moreClient.getGifMore().getBody();

        parseEmbedUrl(model, moreGifResponse, "Курс повысился!", baseSelect, rateSelect, courseLatest, courseHistory, rateDate);
        return "gif_more";
    }

    @GetMapping(path = "/less")
    public String gifLess(@ModelAttribute("baseSelect") String baseSelect,
                          @ModelAttribute("rateSelect") String rateSelect,
                          @ModelAttribute("courseLatest") String courseLatest,
                          @ModelAttribute("courseHistory") String courseHistory,
                          @ModelAttribute("rateDate") String rateDate,
                          Model model) {
        String lessGifResponse = lessClient.getGifLess().getBody();

        parseEmbedUrl(model, lessGifResponse, "Курс опустился.", baseSelect, rateSelect, courseLatest, courseHistory, rateDate);
        return "gif_less";
    }

    @PostMapping(path = "/more")
    public String gifMorePost(Model model) {
        return "redirect:/";
    }

    @PostMapping(path = "/less")
    public String gifLessPost(Model model) {
        return "redirect:/";
    }

    private void parseEmbedUrl(Model model, String gifResponse, String title, String baseSelect, String rateSelect, String courseLatest, String courseHistory, String rateDate) {
        String embedUrl = null;
        try {
            JSONObject gifData = (JSONObject) new JSONParser().parse(gifResponse);
            JSONObject gifMore = (JSONObject) gifData.get("data");
            embedUrl = (String) gifMore.get("embed_url");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("embed_url", embedUrl);
        model.addAttribute("title", title);
        model.addAttribute("baseSelect", baseSelect);
        model.addAttribute("rateSelect", rateSelect);
        model.addAttribute("courseLatest", courseLatest);
        model.addAttribute("courseHistory", courseHistory);
        model.addAttribute("rateDate", rateDate);
    }
}
