package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoNoteService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller for {@link com.artmal.edu_tool_for_youtube.model.Video}'s pages.
 *
 * @author Artem Malchenko
 * @version 1.0
 */


@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private VideoNoteService videoNoteService;

    @Transactional
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String showVideoPage(Model model, @RequestParam("id") long videoId) {
        Video video = videoService.findById(videoId);
        String videoCode = video.getVideoCode();

        List<VideoNote> videoNotes = videoNoteService.findAllByVideo(video);
        model.addAttribute("videoCode", videoCode);
        model.addAttribute("video_id", videoId);
        model.addAttribute("video_notes", videoNotes);
        return "videoPage";
    }

    @Transactional
    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    public String addNotes(Model model, @RequestParam("note") String note, @RequestParam("video_id") long videoId) {
        Video videoOnThePage = videoService.findById(videoId);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateInString = formatter.format(new Date());
        VideoNote videoNote = new VideoNote(note, dateInString, videoOnThePage);
        videoOnThePage.getNotes().add(videoNote);

        videoNoteService.save(videoNote);

        String videoCode = videoOnThePage.getVideoCode();

        List<VideoNote> videoNotes = videoNoteService.findAllByVideo(videoOnThePage);

        model.addAttribute("videoCode", videoCode);
        model.addAttribute("video_id", videoId);
        model.addAttribute("video_notes", videoNotes);
        return "videoPage";
    }

    @Transactional
    @RequestMapping(value = "/setVideoAsCompleted", method = RequestMethod.GET)
    public String setVideoAsCompleted(Model model, @RequestParam("id") long videoId) {
        Video video = videoService.findById(videoId);
        Playlist playlistContainingTheVideo = playlistService.findByVideoId(video.getId());



        boolean currentValueOfCompleteness = video.isCompleted();
        if(currentValueOfCompleteness) {
            video.setCompleted(!video.isCompleted());
            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() - 1);

            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        } else {
            video.setCompleted(!video.isCompleted());;
            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() + 1);

            List<Video> listOfVideos = videoService.getAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        }

        Playlist playlistWithTheVideo = playlistService.findByVideoId(video.getId());
        List<Video> listOfVideos = videoService.getAllByPlaylistId(playlistWithTheVideo.getId());
        model.addAttribute("listOfVideos", listOfVideos);
        return "playlistPage";
    }
}
