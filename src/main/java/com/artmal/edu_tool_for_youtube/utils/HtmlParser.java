package com.artmal.edu_tool_for_youtube.utils;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
    public static List<String> getVideoTitles(Document doc) {
        List<String> videoTitles = new ArrayList<>();
        Elements elements= doc.getElementsByTag("tr");
        for(int i = 0; i < elements.size(); i++){
            videoTitles.add(elements.get(i).attr("data-title"));
        }

        return videoTitles;
    }

    public static List<String> getVideoDurations(Document doc) {
        List<String> durations = new ArrayList<>();
        Elements elementsWithDuration = doc.select("span[aria-label]");
        //First 4 values in HTML are not durations
        for(int i = 4; i < elementsWithDuration.size(); i++) {
            durations.add(elementsWithDuration.get(i).text());
        }

        return durations;
    }

    public static Playlist initilizePlaylist(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String playlistTitleWithYoutubeBenchmark = doc.getElementsByTag("title").first().text();
        String playlistTitle = playlistTitleWithYoutubeBenchmark.substring(0, playlistTitleWithYoutubeBenchmark.lastIndexOf("-"));
        String channelTitle = doc.select("a[data-ytid]").first().text();

        return new Playlist(playlistTitle, channelTitle, link);
    }
}
