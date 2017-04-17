package com.artmal.edu_tool_for_youtube.service.impl;

import com.artmal.edu_tool_for_youtube.dao.VideoDao;
import com.artmal.edu_tool_for_youtube.model.Playlist;
import com.artmal.edu_tool_for_youtube.model.Video;
import com.artmal.edu_tool_for_youtube.model.VideoNote;
import com.artmal.edu_tool_for_youtube.service.PlaylistService;
import com.artmal.edu_tool_for_youtube.service.VideoNoteService;
import com.artmal.edu_tool_for_youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private VideoNoteService videoNoteService;

    @Override
    @Transactional
    public void save(Video video) {
        videoDao.save(video);
    }

    @Override
    @Transactional
    public void changeLevelOfUnderstanding(Model model, long videoId, int levelOfUnderstanding) {
        Video video = videoDao.findById(videoId);
        video.setLevelOfUnderstanding(levelOfUnderstanding);
        video.setCompleted(true);

        Playlist playlistContainingTheVideo = playlistService.findByVideoId(videoId);
        playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() + 1);

        List<Video> listOfVideos = videoDao.findAllByPlaylistId(playlistContainingTheVideo.getId());
        model.addAttribute("listOfVideos", listOfVideos);
    }

    @Override
    @Transactional
    public String getVideoCode(Video video) {
        return video.getVideoCode();
    }

    @Override
    @Transactional
    public void changeValueOfCompleteness(Model model, long videoId) {
        Video video = videoDao.findById(videoId);
        Playlist playlistContainingTheVideo = playlistService.findByVideoId(videoId);

        boolean currentValueOfCompleteness = video.isCompleted();
        if (currentValueOfCompleteness) {
            video.setCompleted(!video.isCompleted());
            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() - 1);

            List<Video> listOfVideos = videoDao.findAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        } else {
            video.setCompleted(!video.isCompleted());

            playlistContainingTheVideo.setAmountOfCompletedVideos(playlistContainingTheVideo.getAmountOfCompletedVideos() + 1);

            List<Video> listOfVideos = videoDao.findAllByPlaylistId(playlistContainingTheVideo.getId());
            model.addAttribute("listOfVideos", listOfVideos);
        }
    }

    @Override
    @Transactional
    public void addNote(Model model, long videoId, String note) {
        Video videoOnThePage = videoDao.findById(videoId);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateInString = formatter.format(new Date());
        VideoNote videoNote = new VideoNote(note, dateInString, videoOnThePage);
        videoOnThePage.getNotes().add(videoNote);

        videoNoteService.save(videoNote);
    }

    @Override
    public void removeAllByPlaylistId(long playlistId) {

    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> findAllByPlaylistId(long playlistId) {
        return videoDao.findAllByPlaylistId(playlistId);
    }

    @Override
    @Transactional(readOnly = true)
    public Video findById(long id) {
        return videoDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Video> findAllByPlaylist(Playlist playlist) {
        return videoDao.findAllByPlaylist(playlist);
    }

    @Override
    @Transactional
    public void removeAllByPlaylist(Playlist playlist) {
        videoDao.removeAllByPlaylist(playlist);
    }
}
