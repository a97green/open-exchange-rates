package ru.aGreen.openexchangerates.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aGreen.openexchangerates.client.GifLessClient;
import ru.aGreen.openexchangerates.client.GifMoreClient;

@RequestMapping
@Controller
public class GiphyController {
    @Autowired
    private GifMoreClient moreClient;
    @Autowired
    private GifLessClient lessClient;
    
    @GetMapping(path = "/more")
    public String getGifMore(Model model) {
        String moreGifResponse = moreClient.getGifMore().getBody();
        parseEmbedUrl(model, moreGifResponse, "Курс повысился!");
        return "giphy_more";
    }

    @PostMapping(path = "/more")
    public String getGifMorePost(Model model) {
        return "redirect:/";
    }

    @GetMapping(path = "/less")
    public String getGifLess(Model model) {
        String lessGifResponse = lessClient.getGifLess().getBody();
        parseEmbedUrl(model, lessGifResponse, "Курс опустился.");
        return "giphy_less";
    }

    @PostMapping(path = "/less")
    public String getGifLessPost(Model model) {
        return "redirect:/";
    }

    private void parseEmbedUrl(Model model, String gifResponse, String title) {
        String embedUrl = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject gifData = (JSONObject) parser.parse(gifResponse);
            JSONObject gifMore = (JSONObject) gifData.get("data");
            embedUrl = (String) gifMore.get("embed_url");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("embed_url", embedUrl);
        model.addAttribute("title", title);
    }
}
