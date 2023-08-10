# topsale_streamer
Application for Android TV that continuously streams latest videos and stories of an Instagram page

# Setup

### instagram-story
Install instagram-story and follow [instructions](https://pypi.org/project/instagram-story/) on how to configure login session

### ffmpeg
Download and install [ffmpeg](https://ffmpeg.org/download.html)

### instaloader
```sh
pip install instaloader
```

# Run 
`scraper/main.sh` scrapes videos and merges them into a rotated vertical video. 

# Notes
- The looped video address is hardcoded into the Android TV application
