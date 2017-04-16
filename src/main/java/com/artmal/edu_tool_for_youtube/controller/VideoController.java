package com.artmal.edu_tool_for_youtube.controller;

import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoNoteService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String showVideoPage(Model model, @RequestParam("id") long videoId) {
        Video video = videoService.findById(videoId);
        String videoCode = videoService.getVideoCode(video);

        List<VideoNote> videoNotes = videoNoteService.findAllByVideo(video);
        model.addAttribute("videoCode", videoCode);
        model.addAttribute("video_id", videoId);
        model.addAttribute("video_notes", videoNotes);
        model.addAttribute("level_of_understanding", video.getLevelOfUnderstanding());
        return "videoPage";
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    public String addNotes(Model model, @RequestParam("note") String note, @RequestParam("video_id") long videoId) {

        videoService.addNote(model, videoId, note);

        Video videoOnThePage = videoService.findById(videoId);
        String videoCode = videoOnThePage.getVideoCode();
        List<VideoNote> videoNotes = videoNoteService.findAllByVideo(videoOnThePage);

        model.addAttribute("videoCode", videoCode);
        model.addAttribute("video_id", videoId);
        model.addAttribute("video_notes", videoNotes);
        model.addAttribute("level_of_understanding", videoOnThePage.getLevelOfUnderstanding());
        return "videoPage";
    }

    @RequestMapping(value = "/setVideoAsCompleted", method = RequestMethod.GET)
    public String setVideoAsCompleted(Model model, @RequestParam("id") long videoId) {
        videoService.changeValueOfCompleteness(model, videoId);

        Playlist playlistWithTheVideo = playlistService.findByVideoId(videoId);
        List<Video> listOfVideos = videoService.findAllByPlaylistId(playlistWithTheVideo.getId());
        model.addAttribute("listOfVideos", listOfVideos);
        return "playlistPage";
    }

    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model, @RequestParam("note_id") long noteId, @RequestParam("video_id") long videoId) {
        videoNoteService.removeById(noteId);

        Video video = videoService.findById(videoId);
        String videoCode = video.getVideoCode();

        List<VideoNote> videoNotes = videoNoteService.findAllByVideo(video);
        model.addAttribute("videoCode", videoCode);
        model.addAttribute("video_id", videoId);
        model.addAttribute("video_notes", videoNotes);
        model.addAttribute("level_of_understanding", video.getLevelOfUnderstanding());
        return "videoPage";
    }

    @RequestMapping(value = "/video/changeUnderstandingLevel", method = RequestMethod.POST)
    public String changeUnderstandingLevel(Model model, @RequestParam("video_id") long videoId,
            @RequestParam("level_of_understanding") int levelOfUnderstanding) {
        videoService.changeLevelOfUnderstanding(model, videoId, levelOfUnderstanding);

        Video video = videoService.findById(videoId);

        Playlist playlistContainingTheVideo = playlistService.findByVideoId(video.getId());
        video.setCompleted(true);
        playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() + 1);

        List<Video> listOfVideos = videoService.findAllByPlaylistId(playlistContainingTheVideo.getId());
        model.addAttribute("listOfVideos", listOfVideos);
        model.addAttribute("playlistId", playlistContainingTheVideo.getId());
        return "playlistPage";
    }
}