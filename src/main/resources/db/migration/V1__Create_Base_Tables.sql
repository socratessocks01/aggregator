CREATE TABLE onboarded_videos
(
    id SERIAL PRIMARY KEY,
    video_id VARCHAR(255) NOT NULL
);

CREATE INDEX on onboarded_videos(video_id);

CREATE TABLE word_frequencies
(
    id SERIAL PRIMARY KEY,
    video_id VARCHAR(255) NOT NULL,
    word VARCHAR(255) NOT NULL,
    count INT NOT NULL
);

CREATE INDEX on word_frequencies(video_id);