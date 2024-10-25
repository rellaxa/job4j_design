package ru.job4j.ood.isp;

public interface Player {

    /**
     * Нарушение ISP: не все плееры могут проигрывать и видео, и аудио.
     * Лучше разделить на 2 интерфейса.
     */
    void playVideo();
    void playAudio();

}
